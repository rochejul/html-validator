package com.jroche;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;

import com.jroche.filefilter.ExtensionsFileFilter;
import com.rexsl.w3c.Defect;
import com.rexsl.w3c.ValidationResponse;
import com.rexsl.w3c.Validator;
import com.rexsl.w3c.ValidatorBuilder;

/**
 * HtmlValidator class to check the files !
 * 
 * @author jroche
 * @version 1.0
 * @since 1.0
 */
public class HtmlValidator {
	/**
	 * Logger
	 */
	protected static final Logger LOGGER = Logger.getLogger(HtmlValidator.class.getName());
	
	/**
	 * Line separator
	 */
	protected static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * Empty string
	 */
	protected static final String EMPTY_STRING = "";
	
	/**
	 * Regular expression to clean the string
	 */
	protected static final String REGEXP_STRING_CLEANING = "[\n\r]";
	
	/**
	 * Regular expression to check if we have an HTML envelope or not
	 */
	protected static final String REGEXP_HTML_ENVELOPE = "[<html.*>,<HTML.*>]";
	
	// Properties
	private final HtmlValidatorContext context;
	private final ExtensionsFileFilter extensionsFileFilter;
	private final Map<String, ValidationResponse> errorsAndWarnings;
	
	/**
	 * Default constructor
	 * @param context
	 */
	public HtmlValidator(final HtmlValidatorContext context) {
		super();
		
		this.extensionsFileFilter = new ExtensionsFileFilter(context);
		this.errorsAndWarnings = new HashMap<String, ValidationResponse>();
		this.context = context;
	}
	
	/**
	 * Analyze the file
	 * 
	 * @param file
	 * @param validator
	 * @throws IOException 
	 */
	protected void analyzeFile(File file, Validator validator) throws IOException {
		// First, we read the content of the file
		this.log("Read content of the file");
		
		Scanner scanner = new Scanner(file);
		StringBuffer buffer = new StringBuffer();
		String fileContent;
		ValidationResponse response;
		
		while(scanner.hasNext()) {
			buffer.append(scanner.nextLine());
		}
		
		scanner.close();
		fileContent = buffer.toString();
		
		// We analyze the content to see if we have a fragment or not
		if (Pattern.matches(REGEXP_HTML_ENVELOPE, fileContent)) {
			this.log("The file seems to have an HTML tag envelope");
			
		} else {
			this.log("The file seems to be a fragment. We will decorate it !");
			buffer = new StringBuffer();
			buffer.append("<!DOCTYPE html>");
			buffer.append("<html><head><title>html fragment</title><meta charset=\"");
			buffer.append(this.context.getEncoding());
			buffer.append("\" /></head><body>");
			buffer.append(fileContent);
			buffer.append("</body></html>");
			fileContent = buffer.toString();
		}
		
		
		// Now, we validate the content
		this.log("Validate the file");
		response = validator.validate(fileContent);
		
		// What is the results ?
		if (response.valid()) {
			this.log("The file " + file.getAbsolutePath() + " hasn't got warnings, nor errors");
			
		} else {
			this.log("The file " + file.getAbsolutePath() + " has got: " + response.warnings().size() + " warning(s) and " + response.errors().size() + " error(s)");
			this.errorsAndWarnings.put(file.getAbsolutePath(), response);
		}
	}
	
	/**
	 * Clean the string
	 * @param sequence
	 * @return the cleaned string
	 */
	protected String cleaningString(String sequence) {
		return sequence.replaceAll(REGEXP_STRING_CLEANING, EMPTY_STRING);
	}
	
	/**
	 * Convert the HTML to string
	 * @param htmlSequence
	 * @return the String
	 */
	protected String convertHtmlToString(String htmlSequence) {
		return Jsoup.parseBodyFragment(htmlSequence).text();
	}
	
	/**
	 * Escape double quotes
	 * @param sequence
	 * @return
	 */
	protected String escapeDoubleQuote(String sequence) {
		return sequence.replaceAll("\"", "'");
	}
	
	/**
	 * Method to display a log message
	 * @param message
	 */
	protected final void log(String message) {
		if (this.context.isVerbose()) {
			LOGGER.info(message);
		}
	}
	
	/**
	 * Generate the report
	 * @throws IOException 
	 */
	protected void reportGeneration() throws IOException {
		FileWriter writer = new FileWriter(this.context.getOutput());
		writer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		writer.append(LINE_SEPARATOR);
		
		writer.append("<checkstyle version=\"4.3\">");
		writer.append(LINE_SEPARATOR);
		
		for (Entry<String, ValidationResponse> entry : this.errorsAndWarnings.entrySet()) {
			writer.append("\t<file name=\"" + entry.getKey() + "\">");
			writer.append(LINE_SEPARATOR);
			
			if (entry.getValue().errors().size() > 0) {
				for(Defect defect : entry.getValue().errors()) {
					writer.append("\t\t<error line=\""
						+ defect.line()
						+ "\" column=\""
						+ defect.column()
						+ "\" severity=\"error\" message=\""
						+ this.cleaningString(defect.message())
						+ "\" source=\""
						+ this.escapeDoubleQuote(this.convertHtmlToString(this.cleaningString(defect.source()))) + "\" />");
					
					writer.append(LINE_SEPARATOR);
				}
			}
			
			if (entry.getValue().warnings().size() > 0) {
				for(Defect defect : entry.getValue().warnings()) {
					writer.append("\t\t<error line=\""
						+ defect.line()
						+ "\" column=\"" + defect.column()
						+ "\" severity=\"warning\" message=\""
						+ this.cleaningString(defect.message())
						+ "\" source=\""
						+ this.escapeDoubleQuote(this.convertHtmlToString(this.cleaningString(defect.source()))) + "\" />");
					
					writer.append(LINE_SEPARATOR);
				}
			}
			
			writer.append("\t</file");
			writer.append(LINE_SEPARATOR);
		}
		
		writer.append("</checkstyle");
		writer.append(LINE_SEPARATOR);
		
		writer.close();
	}
	
	/**
	 * Method running the validation process
	 * @throws IOException
	 */
	public void runValidation() throws IOException {
		this.log("We start now the validation");
		
		Validator validator = new ValidatorBuilder().html();
		
		for(File directory: this.context.getDirectories()) {
			this.log("Check all files from the directory: " + directory.getAbsolutePath());
			
			for (File file : directory.listFiles(this.extensionsFileFilter)) {
				this.log("Check file: " + file.getAbsolutePath());
				this.analyzeFile(file, validator);
			}
		}
		
		this.log("Generation of the report starts");
		this.reportGeneration();
		this.log("Generation of the report ended");
	}
}

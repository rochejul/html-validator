package com.jroche;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jroche.filefilter.DirectoryFileFilter;

/**
 * This bean will contain arguments for the {@link HtmlValidator} class
 * 
 * @author jroche
 * @version 1.0
 * @since 1.0
 *
 */
public class HtmlValidatorContext {	
	/**
	 * Default values for the extensions option
	 */
	protected static final String[] DEFAULT_EXTENSIONS = { "htm", "html", "tmpl", "tpl" }; // sorted list !
	
	/**
	 * Default report path
	 */
	protected static final String DEFAULT_REPORT_PATH = "./report-html-checkstyle.xml";
	
	/**
	 * Default encoding
	 */
	protected static final String DEFAULT_ENCODING = "UTF-8";
	
	/**
	 * Token to detect an option
	 */
	protected static final String START_TOKEN_OPTION = "--";
	
	/**
	 * Sub-directories option
	 */
	protected static final String SUBDIRECTORIES_OPTION = START_TOKEN_OPTION + "subdirectories";
	
	/**
	 * Verbose option
	 */
	protected static final String VERBOSE_OPTION = START_TOKEN_OPTION + "verbose";
	
	/**
	 * Output option
	 */
	protected static final String OUTPUT_OPTION = START_TOKEN_OPTION + "ouput";
	
	/**
	 * Encoding option
	 */
	protected static final String ENCODING_OPTION = START_TOKEN_OPTION + "encoding";
	
	/**
	 * Extensions option
	 */
	protected static final String EXTENSIONS_OPTION = START_TOKEN_OPTION + "extensions";
	
	/**
	 * Directories option
	 */
	protected static final String DIRECTORIES_OPTION = START_TOKEN_OPTION + "directories";
	
	/**
	 * Argument
	 */

	// Properties
	private boolean subdirectories;
	private boolean verbose;
	private String encoding;
	private String[] extensions;
	private File[] directories;
	private File output;
	
	/**
	 * Default constructor
	 */
	public HtmlValidatorContext() {
		super();
		
		this.verbose = false;
		this.subdirectories = false;
		this.output = new File(DEFAULT_REPORT_PATH);
		this.extensions = Arrays.copyOf(DEFAULT_EXTENSIONS, DEFAULT_EXTENSIONS.length);
		this.directories = null;
		this.encoding = new String(DEFAULT_ENCODING);
	}
	
	/**
	 * Constructor where we can pass user arguments
	 * @param arguments
	 */
	public HtmlValidatorContext(String[] arguments) {
		this();
		
		// Analyze the arguments
		int index = 0;
		int maxLength = arguments.length - 1;
		String argument;
		String value;
		List<String> values;
		
		while (index <= maxLength) {
			argument = arguments[index];
			
			if (argument.startsWith(START_TOKEN_OPTION)) {
				if (VERBOSE_OPTION.equals(argument)) {
					// Manage the verbose option
					this.verbose = true;
					
				} else if (SUBDIRECTORIES_OPTION.equals(argument)) {
					// Manage the sub-directories option
					this.subdirectories = true;
					
				} else {
					// Retrieve all values for the associated option
					value = "";
					values = new ArrayList<String>();
					
					while(index < maxLength && !value.startsWith(START_TOKEN_OPTION)) {
						index++;
						value = arguments[index];
						
						if (!value.startsWith(START_TOKEN_OPTION)) {
							values.add(value);
						}
					}
					
					if (index < maxLength) {
						index--;
					}
					
					// Treat now the values for the associated option
					if (OUTPUT_OPTION.equals(argument)) {
						// Manage the output option
						this.output = values.isEmpty() ? this.output : new File(values.get(0));
						
					} else if (ENCODING_OPTION.equals(argument)) {
						// Manage the encoding option
						this.encoding = values.isEmpty() ? this.encoding : values.get(0);
						
					} else if (EXTENSIONS_OPTION.equals(argument)) {
						// Manage the extensions option
						this.extensions = values.toArray(new String[values.size()]);
						Arrays.sort(this.extensions);
						
					} else if (DIRECTORIES_OPTION.equals(argument)) {
						// Manage the directories option
						Set<File> directories = new HashSet<File>();
						
						for(String directoryPath : values) {
							collectSubDirectories(new File(directoryPath), directories);
						}
						
						this.directories = directories.toArray(new File[directories.size()]);
					}
				}
			}
			
			index++;
		}
	}
	
	/**
	 * Method to collect sub-directories of the user specification
	 * @param directory
	 * @param directories
	 */
	protected void collectSubDirectories(File directory, Set<File> directories) {
		if (directory.exists() && directory.isDirectory()) {
			directories.add(directory);
			
			if (this.subdirectories) {
				// Search sub directories
				for (File subDirectory: directory.listFiles(DirectoryFileFilter.INSTANCE)) {
					collectSubDirectories(subDirectory, directories);
				}
			}
		}
	}
	
	// Getter / setter
	
	/**
	 * @return List of directories (and sub-directories) to analyze
	 */
	public File[] getDirectories() {
		return directories;
	}
	
	/**
	 * @return The encoding
	 */
	public String getEncoding() {
		return encoding;
	}
	
	/**
	 * @return Sorted list of possible extensions
	 */
	public String[] getExtensions() {
		return extensions;
	}
	
	/**
	 * @return Report path
	 */
	public File getOutput() {
		return output;
	}

	/**
	 * @return True if we have to search into sub-directories
	 */
	public boolean isSubdirectories() {
		return subdirectories;
	}
	
	/**
	 * Shall we display verbose informations ?
	 * @return
	 */
	public boolean isVerbose() {
		return verbose;
	}
}

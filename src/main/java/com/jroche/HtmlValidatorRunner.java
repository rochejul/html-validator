package com.jroche;

import java.io.IOException;

/**
 * This class will use the rexsl-w3c API to validate the HTML code !
 * 
 * @author jroche
 * @version 1.0
 * @since 1.0
 */
public class HtmlValidatorRunner {
	
	/**
	 * Method to execute the runner
	 * Possible arguments:
	 * --verbose (display all informations of the runner. Default, no verbose will be done)
	 * --extensions html tpl tmpl (optional, default: htm html tmpl tpl)
	 * --directories . ../templates "C:\Dev\templates"
	 * --subdirectories (optional, default: false)
	 * --output result.xml (optional, default: ./report-html-checkstyle.xml)
	 * --encoding (optional, default: UTF-8)
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		if (args == null || args.length <= 0) {
			throw new RuntimeException("Missing arguments to search into specified directories");
		}

		new HtmlValidator(new HtmlValidatorContext(args)).runValidation();
	}

}

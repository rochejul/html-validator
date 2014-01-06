package com.jroche.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import com.jroche.HtmlValidatorContext;

/**
 * {@link FileFilter} to analyze only expected files
 * 
 * @author jroche
 * @version 1.0
 * @since 1.0
 */
public class ExtensionsFileFilter implements FileFilter {
	/**
	 * Extension separator
	 */
	protected static final char EXTENSION_SEPARATOR = '.';
	
	// Properties
	private final HtmlValidatorContext context;
	
	/**
	 * Default constructor
	 * @param context
	 */
	public ExtensionsFileFilter(final HtmlValidatorContext context) {
		super();
		
		this.context = context;
	}

	/**
	 * {@inheritDoc}
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File pathname) {
		if (pathname != null && pathname.isFile()) {
			String fileName = pathname.getName();
			String extension = null;
			int index = fileName.lastIndexOf(EXTENSION_SEPARATOR);
			
			if (index > 0) {
			    extension = fileName.substring(index + 1);
			}
			
			if (extension != null) {
				return Arrays.binarySearch(this.context.getExtensions(), extension) >= 0;
			}
		}
		
		
		return false;
	}

}

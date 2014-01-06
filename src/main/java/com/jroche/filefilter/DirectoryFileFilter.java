package com.jroche.filefilter;

import java.io.File;
import java.io.FileFilter;

/**
 * {@link FileFilter} to get only directories
 * 
 * @author jroche
 * @version 1.0
 * @since 1.0
 */
public class DirectoryFileFilter implements FileFilter {
	/**
	 * Instance
	 */
	public static DirectoryFileFilter INSTANCE = new DirectoryFileFilter();
	
	/**
	 * {@inheritDoc}
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File pathname) {
		return pathname != null && pathname.isDirectory();
	}

}

package com.jroche;

import java.io.File;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Unit test class for {@link HtmlValidatorContext}
 * @author jroche
 * @version 1.0
 * @since 1.0
 */
public class HtmlValidatorContextUnitTest {
	private HtmlValidatorContext context;
	private File baseDirectory;
	
	private void createTempFiles() {
		String tmpDir = System.getProperty("java.io.tmpdir");
		
		baseDirectory = new File(tmpDir, "html-validator");
		
		if (baseDirectory.exists()) {
			baseDirectory.delete();
		}
		
		baseDirectory.mkdir();
		
		new File(baseDirectory, "folder1").mkdir();
		new File(baseDirectory, "folder2").mkdir();
	}

	/**
	 * Test {@link HtmlValidatorContext#HtmlValidatorContext()}
	 */
	@Test
	public void testHtmlValidatorContext() {
		this.context = new HtmlValidatorContext();
		
		Assert.assertFalse(this.context.isVerbose());
		Assert.assertFalse(this.context.isSubdirectories());
		Assert.assertEquals(new File(HtmlValidatorContext.DEFAULT_REPORT_PATH), this.context.getOutput());
		Assert.assertTrue(Arrays.deepEquals(HtmlValidatorContext.DEFAULT_EXTENSIONS, this.context.getExtensions()));
		Assert.assertEquals(HtmlValidatorContext.DEFAULT_ENCODING, this.context.getEncoding());
		Assert.assertNull(this.context.getDirectories());
	}

	/**
	 * Test {@link HtmlValidatorContext#HtmlValidatorContext(String[])}
	 */
	@Test
	public void testHtmlValidatorContextStringArray1() {
		this.context = new HtmlValidatorContext(new String[]{ });
		
		Assert.assertFalse(this.context.isVerbose());
		Assert.assertFalse(this.context.isSubdirectories());
		Assert.assertEquals(new File(HtmlValidatorContext.DEFAULT_REPORT_PATH), this.context.getOutput());
		Assert.assertTrue(Arrays.deepEquals(HtmlValidatorContext.DEFAULT_EXTENSIONS, this.context.getExtensions()));
		Assert.assertEquals(HtmlValidatorContext.DEFAULT_ENCODING, this.context.getEncoding());
		Assert.assertNull(this.context.getDirectories());
	}

	/**
	 * Test {@link HtmlValidatorContext#HtmlValidatorContext(String[])}
	 */
	@Test
	public void testHtmlValidatorContextStringArray2() {
		this.context = new HtmlValidatorContext(new String[]{ "--bob", "tutu" });
		
		Assert.assertFalse(this.context.isVerbose());
		Assert.assertFalse(this.context.isSubdirectories());
		Assert.assertEquals(new File(HtmlValidatorContext.DEFAULT_REPORT_PATH), this.context.getOutput());
		Assert.assertTrue(Arrays.deepEquals(HtmlValidatorContext.DEFAULT_EXTENSIONS, this.context.getExtensions()));
		Assert.assertEquals(HtmlValidatorContext.DEFAULT_ENCODING, this.context.getEncoding());
		Assert.assertNull(this.context.getDirectories());
	}
	
	/**
	 * Test {@link HtmlValidatorContext#HtmlValidatorContext(String[])}
	 */
	@Test
	public void testHtmlValidatorContextStringArray3() {
		this.context = new HtmlValidatorContext(new String[]{ "--verbose" });
		
		Assert.assertTrue(this.context.isVerbose());
		Assert.assertFalse(this.context.isSubdirectories());
		Assert.assertEquals(new File(HtmlValidatorContext.DEFAULT_REPORT_PATH), this.context.getOutput());
		Assert.assertTrue(Arrays.deepEquals(HtmlValidatorContext.DEFAULT_EXTENSIONS, this.context.getExtensions()));
		Assert.assertEquals(HtmlValidatorContext.DEFAULT_ENCODING, this.context.getEncoding());
		Assert.assertNull(this.context.getDirectories());
	}
	
	/**
	 * Test {@link HtmlValidatorContext#HtmlValidatorContext(String[])}
	 */
	@Test
	public void testHtmlValidatorContextStringArray4() {
		this.createTempFiles();
		this.context = new HtmlValidatorContext(new String[]{ "--directories", baseDirectory.getAbsolutePath() });
		
		Assert.assertFalse(this.context.isVerbose());
		Assert.assertFalse(this.context.isSubdirectories());
		Assert.assertEquals(new File(HtmlValidatorContext.DEFAULT_REPORT_PATH), this.context.getOutput());
		Assert.assertTrue(Arrays.deepEquals(HtmlValidatorContext.DEFAULT_EXTENSIONS, this.context.getExtensions()));
		Assert.assertEquals(HtmlValidatorContext.DEFAULT_ENCODING, this.context.getEncoding());
		Assert.assertNotNull(this.context.getDirectories());
		Assert.assertEquals(1, this.context.getDirectories().length);
		Assert.assertEquals(baseDirectory.getAbsolutePath(), this.context.getDirectories()[0].getAbsolutePath());
	}
	
	/**
	 * Test {@link HtmlValidatorContext#HtmlValidatorContext(String[])}
	 */
	@Test
	public void testHtmlValidatorContextStringArray5() {
		this.createTempFiles();
		this.context = new HtmlValidatorContext(new String[]{ "--directories", "\"" + baseDirectory.getAbsolutePath() + "\"" });
		
		Assert.assertFalse(this.context.isVerbose());
		Assert.assertFalse(this.context.isSubdirectories());
		Assert.assertEquals(new File(HtmlValidatorContext.DEFAULT_REPORT_PATH), this.context.getOutput());
		Assert.assertTrue(Arrays.deepEquals(HtmlValidatorContext.DEFAULT_EXTENSIONS, this.context.getExtensions()));
		Assert.assertEquals(HtmlValidatorContext.DEFAULT_ENCODING, this.context.getEncoding());
		Assert.assertNotNull(this.context.getDirectories());
		Assert.assertEquals(1, this.context.getDirectories().length);
		Assert.assertEquals(baseDirectory.getAbsolutePath(), this.context.getDirectories()[0].getAbsolutePath());
	}
	
	/**
	 * Test {@link HtmlValidatorContext#HtmlValidatorContext(String[])}
	 */
	@Test
	public void testHtmlValidatorContextStringArray6() {
		this.context = new HtmlValidatorContext(new String[]{ "--subdirectories" });
		
		Assert.assertFalse(this.context.isVerbose());
		Assert.assertTrue(this.context.isSubdirectories());
		Assert.assertEquals(new File(HtmlValidatorContext.DEFAULT_REPORT_PATH), this.context.getOutput());
		Assert.assertTrue(Arrays.deepEquals(HtmlValidatorContext.DEFAULT_EXTENSIONS, this.context.getExtensions()));
		Assert.assertEquals(HtmlValidatorContext.DEFAULT_ENCODING, this.context.getEncoding());
		Assert.assertNull(this.context.getDirectories());
	}
	
	/**
	 * Test {@link HtmlValidatorContext#HtmlValidatorContext(String[])}
	 */
	@Test
	public void testHtmlValidatorContextStringArray7() {
		this.createTempFiles();
		this.context = new HtmlValidatorContext(new String[]{ "--subdirectories", "--directories", baseDirectory.getAbsolutePath() });
		
		Assert.assertFalse(this.context.isVerbose());
		Assert.assertTrue(this.context.isSubdirectories());
		Assert.assertEquals(new File(HtmlValidatorContext.DEFAULT_REPORT_PATH), this.context.getOutput());
		Assert.assertTrue(Arrays.deepEquals(HtmlValidatorContext.DEFAULT_EXTENSIONS, this.context.getExtensions()));
		Assert.assertEquals(HtmlValidatorContext.DEFAULT_ENCODING, this.context.getEncoding());
		Assert.assertNotNull(this.context.getDirectories());
		Assert.assertEquals(3, this.context.getDirectories().length);
		Assert.assertEquals(baseDirectory.getAbsolutePath() + "\\folder1", this.context.getDirectories()[0].getAbsolutePath());
		Assert.assertEquals(baseDirectory.getAbsolutePath(), this.context.getDirectories()[1].getAbsolutePath());
		Assert.assertEquals(baseDirectory.getAbsolutePath() + "\\folder2", this.context.getDirectories()[2].getAbsolutePath());
	}
	
	/**
	 * Test {@link HtmlValidatorContext#HtmlValidatorContext(String[])}
	 */
	@Test
	public void testHtmlValidatorContextStringArray8() {
		this.context = new HtmlValidatorContext(new String[]{ "--encoding", "UTF-16" });
		
		Assert.assertFalse(this.context.isVerbose());
		Assert.assertFalse(this.context.isSubdirectories());
		Assert.assertEquals(new File(HtmlValidatorContext.DEFAULT_REPORT_PATH), this.context.getOutput());
		Assert.assertTrue(Arrays.deepEquals(HtmlValidatorContext.DEFAULT_EXTENSIONS, this.context.getExtensions()));
		Assert.assertEquals("UTF-16", this.context.getEncoding());
		Assert.assertNull(this.context.getDirectories());
	}
	
	/**
	 * Test {@link HtmlValidatorContext#HtmlValidatorContext(String[])}
	 */
	@Test
	public void testHtmlValidatorContextStringArray9() {
		this.context = new HtmlValidatorContext(new String[]{ "--encoding" });
		
		Assert.assertFalse(this.context.isVerbose());
		Assert.assertFalse(this.context.isSubdirectories());
		Assert.assertEquals(new File(HtmlValidatorContext.DEFAULT_REPORT_PATH), this.context.getOutput());
		Assert.assertTrue(Arrays.deepEquals(HtmlValidatorContext.DEFAULT_EXTENSIONS, this.context.getExtensions()));
		Assert.assertEquals(HtmlValidatorContext.DEFAULT_ENCODING, this.context.getEncoding());
		Assert.assertNull(this.context.getDirectories());
	}
	
	/**
	 * Test {@link HtmlValidatorContext#HtmlValidatorContext(String[])}
	 */
	@Test
	public void testHtmlValidatorContextStringArray10() {
		this.context = new HtmlValidatorContext(new String[]{ "--extensions", "html" });
		
		Assert.assertFalse(this.context.isVerbose());
		Assert.assertFalse(this.context.isSubdirectories());
		Assert.assertEquals(new File(HtmlValidatorContext.DEFAULT_REPORT_PATH), this.context.getOutput());
		Assert.assertTrue(Arrays.deepEquals(new String[] { "html" }, this.context.getExtensions()));
		Assert.assertEquals(HtmlValidatorContext.DEFAULT_ENCODING, this.context.getEncoding());
		Assert.assertNull(this.context.getDirectories());
	}
	
	/**
	 * Test {@link HtmlValidatorContext#HtmlValidatorContext(String[])}
	 */
	@Test
	public void testHtmlValidatorContextStringArray11() {
		this.context = new HtmlValidatorContext(new String[]{ "--extensions", "html", "htm" });
		
		Assert.assertFalse(this.context.isVerbose());
		Assert.assertFalse(this.context.isSubdirectories());
		Assert.assertEquals(new File(HtmlValidatorContext.DEFAULT_REPORT_PATH), this.context.getOutput());
		Assert.assertTrue(Arrays.deepEquals(new String[] { "htm", "html" }, this.context.getExtensions()));
		Assert.assertEquals(HtmlValidatorContext.DEFAULT_ENCODING, this.context.getEncoding());
		Assert.assertNull(this.context.getDirectories());
	}
	
	/**
	 * Test {@link HtmlValidatorContext#HtmlValidatorContext(String[])}
	 */
	@Test
	public void testHtmlValidatorContextStringArray12() {
		this.context = new HtmlValidatorContext(new String[]{ "--extensions" });
		
		Assert.assertFalse(this.context.isVerbose());
		Assert.assertFalse(this.context.isSubdirectories());
		Assert.assertEquals(new File(HtmlValidatorContext.DEFAULT_REPORT_PATH), this.context.getOutput());
		Assert.assertTrue(Arrays.deepEquals(new String[] { }, this.context.getExtensions()));
		Assert.assertEquals(HtmlValidatorContext.DEFAULT_ENCODING, this.context.getEncoding());
		Assert.assertNull(this.context.getDirectories());
	}
}

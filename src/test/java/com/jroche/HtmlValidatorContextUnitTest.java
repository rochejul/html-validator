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
	public void testHtmlValidatorContextStringArray5() {
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
	public void testHtmlValidatorContextStringArray6() {
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
	public void testHtmlValidatorContextStringArray7() {
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
	public void testHtmlValidatorContextStringArray8() {
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
	public void testHtmlValidatorContextStringArray9() {
		this.context = new HtmlValidatorContext(new String[]{ "--extensions" });
		
		Assert.assertFalse(this.context.isVerbose());
		Assert.assertFalse(this.context.isSubdirectories());
		Assert.assertEquals(new File(HtmlValidatorContext.DEFAULT_REPORT_PATH), this.context.getOutput());
		Assert.assertTrue(Arrays.deepEquals(new String[] { }, this.context.getExtensions()));
		Assert.assertEquals(HtmlValidatorContext.DEFAULT_ENCODING, this.context.getEncoding());
		Assert.assertNull(this.context.getDirectories());
	}
}

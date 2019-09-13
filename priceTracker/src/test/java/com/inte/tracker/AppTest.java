package com.inte.tracker;

import java.io.IOException;

import org.junit.Test;

import com.inte.tracker.utils.URLReader;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void testURLReader() throws IOException {
		String theURL = "https://www.amazon.com.mx/";
		StringBuilder sb = URLReader.getUrlContents(theURL);
		System.out.println(sb.toString());
		
	}
}

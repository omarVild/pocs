package com.inte.tracker.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public class URLReader {
	static final Logger logger = Logger.getLogger(URLReader.class);
	
	private URLReader() {	
	}
	
	public static StringBuilder getUrlContents(String theUrl) throws IOException {
		StringBuilder content = new StringBuilder();
		logger.info("URL Producto: " + theUrl);
		try {
			URL url = new URL(theUrl);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.0.11) ");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n\r");
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return content;
	}
}

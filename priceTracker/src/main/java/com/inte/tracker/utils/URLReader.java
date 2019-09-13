package com.inte.tracker.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLReader {
	private URLReader() {	
	}
	
	public static StringBuilder getUrlContents(String theUrl) throws IOException {
		StringBuilder content = new StringBuilder();
		System.out.println(theUrl);
		try {
			URL url = new URL(theUrl);
			URLConnection urlConnection = url.openConnection();
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

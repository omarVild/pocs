package com.inte.tracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		
		int precioNormal = 328;
		
		StringBuilder output = getUrlContents("https://www.liverpool.com.mx/tienda/pdp/abrillantador-meguiar's-ultimate-quick-detailer-negro/61837434");
		
		Document doc = Jsoup.parse(output.toString());
		doc.select("sup").remove();
		Elements els = doc.getElementsByClass("a-product__paragraphDiscountPrice m-0 d-inline ");
		doc.clearAttributes();
		if(els != null && !els.isEmpty()) {
			Element element = els.get(0);
			System.out.println(element);
			String precioActualStr = element.text();
			System.out.println("Precio: "+precioActualStr);
			
			Integer precioActualInt = null;
			try {
				precioActualInt = Integer.parseInt(precioActualStr.replace("$", ""));
				Double precioObjetivo = precioNormal*.9;
				if(precioActualInt<precioObjetivo) {
					System.out.println("*******************************");
					System.out.println("Precio objetivo:"+ precioActualStr);
				}else {
					System.out.println("***********muy caro*************");
				}
				
			}catch (Exception e) {
				System.out.println("algo fallo");
				System.out.println("intentar mas tarde");
			}
			
		}
		
	}

	private static StringBuilder getUrlContents(String theUrl) {
		StringBuilder content = new StringBuilder();
		try {
			URL url = new URL(theUrl);
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n\r");
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	
	
	
}

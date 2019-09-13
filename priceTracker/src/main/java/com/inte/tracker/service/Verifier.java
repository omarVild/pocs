package com.inte.tracker.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.inte.tracker.model.Product;
import com.inte.tracker.utils.URLReader;

public class Verifier {

	private String priceHTMLClassElement = "a-product__paragraphDiscountPrice m-0 d-inline ";

	
	private boolean verifyLiverpool(Product producto) throws IOException {
		
	}
	
	private boolean verifyAmazon(Product producto) throws IOException {
		
	}
	
	
	public boolean verifyPrice(Product producto) throws IOException, NumberFormatException {
		boolean isPrecioObjetivoAlcanzado = false;
		StringBuilder output = URLReader.getUrlContents(producto.getUrlProducto());
		Document doc = Jsoup.parse(output.toString());
		doc.select("sup").remove();
		Elements els = doc.getElementsByClass(priceHTMLClassElement);
		doc.clearAttributes();
		if (els != null && !els.isEmpty()) {
			Element element = els.get(0);
			String precioActualStr = element.text();
			Integer precioActualInt = null;
			try {
				precioActualInt = Integer.parseInt(precioActualStr.replace("$", ""));
				Float precioObjetivo = null;
				if(producto.getPrecioObjetivo()!= null) {
					precioObjetivo =producto.getPrecioObjetivo().floatValue();
				}else {
					precioObjetivo = producto.getPrecioNormal() * producto.getPorcentajePrecioObjetivo();	
				}
				if (precioActualInt < precioObjetivo) {
					System.out.println("Precio objetivo:" + precioActualStr);
					isPrecioObjetivoAlcanzado = true;
				}else {
					System.out.println("Muy caro:" + precioActualStr);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return isPrecioObjetivoAlcanzado;
	}

	

	public String getPriceHTMLClassElement() {
		return priceHTMLClassElement;
	}

	public void setPriceHTMLClassElement(String priceHTMLClassElement) {
		this.priceHTMLClassElement = priceHTMLClassElement;
	}

}

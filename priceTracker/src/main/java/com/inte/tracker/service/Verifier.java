package com.inte.tracker.service;

import java.io.IOException;

import javax.management.InvalidAttributeValueException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.inte.tracker.model.Product;
import com.inte.tracker.utils.URLReader;

public class Verifier {
	static final Logger logger = Logger.getLogger(Verifier.class);
	
	private String priceHTMLClassElementLiverpool = "a-product__paragraphDiscountPrice m-0 d-inline ";
	private String priceHTMLClassElementAmazon = "a-size-medium a-color-price priceBlockBuyingPriceString";

	private String urlLiverpool = "https://www.liverpool.com.mx/";
	private String urlAmazon = "https://www.amazon.com.mx";
	
	
	private Element verifyLiverpool(Product producto) throws IOException, InvalidAttributeValueException {
		StringBuilder output = URLReader.getUrlContents(producto.getUrlProducto());
		Document doc = Jsoup.parse(output.toString());
		doc.select("sup").remove();
		Elements els = doc.getElementsByClass(priceHTMLClassElementLiverpool);
		doc.clearAttributes();
		if (els == null || els.isEmpty()) {
			throw new InvalidAttributeValueException("No se puede leer el elemento precio");
		}
		return els.get(0);
	}

	private Element verifyAmazon(Product producto) throws IOException, InvalidAttributeValueException {
		StringBuilder output = URLReader.getUrlContents(producto.getUrlProducto());
		Document doc = Jsoup.parse(output.toString());
		Elements els = doc.getElementsByClass(priceHTMLClassElementAmazon);
		doc.clearAttributes();
		if (els == null || els.isEmpty()) {
			throw new InvalidAttributeValueException("No se puede leer el elemento precio");
		}
		return els.get(0);
	}

	public boolean verifyPrice(Product producto) throws IOException, InvalidAttributeValueException {
		boolean isPrecioObjetivoAlcanzado = false;
		Element element=null;
		if(producto.getUrlProducto().contains(urlLiverpool)) {
			element = verifyLiverpool(producto);
		}else {
			element = verifyAmazon(producto);
		}
		String precioActualStr = element.text();
		Float precioActualInt = null;
		try {
			precioActualInt = Float.parseFloat(precioActualStr.replace("$", ""));
			Float precioObjetivo = null;
			if (producto.getPrecioObjetivo() != null) {
				logger.info("buscando por precio objetivo");
				precioObjetivo = producto.getPrecioObjetivo().floatValue();
			} else {
				logger.info("buscando por porcentaje descuento");
				precioObjetivo = producto.getPrecioNormal() * producto.getPorcentajePrecioObjetivo();
			}
			if (precioActualInt.floatValue() < precioObjetivo.floatValue()) {
				logger.info("Precio actual:" + precioActualStr);
				isPrecioObjetivoAlcanzado = true;
			} else {
				logger.info("Muy caro:" + precioActualStr);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw e;
		}
		return isPrecioObjetivoAlcanzado;
	}

	public String getPriceHTMLClassElementLiverpool() {
		return priceHTMLClassElementLiverpool;
	}

	public void setPriceHTMLClassElementLiverpool(String priceHTMLClassElementLiverpool) {
		this.priceHTMLClassElementLiverpool = priceHTMLClassElementLiverpool;
	}

	public String getPriceHTMLClassElementAmazon() {
		return priceHTMLClassElementAmazon;
	}

	public void setPriceHTMLClassElementAmazon(String priceHTMLClassElementAmazon) {
		this.priceHTMLClassElementAmazon = priceHTMLClassElementAmazon;
	}
	
}

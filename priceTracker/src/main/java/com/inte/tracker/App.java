package com.inte.tracker;

import java.util.ArrayList;

import com.inte.tracker.model.Product;
import com.inte.tracker.service.Tracker;
import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
	static final Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) throws InterruptedException {
		ArrayList<Product> productos = new ArrayList<>();
		Product quickDetailer =  new Product("https://www.liverpool.com.mx/tienda/pdp/abrillantador-meguiar's-ultimate-quick-detailer-negro/61837434", 328, 0.8f);
		productos.add(quickDetailer);
		
		String urlmeguiarsCeraCeramica = "https://www.amazon.com.mx/Meguiar%C2%B4s-G190526-Cera-Cer%C3%A1mica-Empaque/dp/B06WVQ6MVR/ref=sr_1_14?__mk_es_MX=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=meguiars&qid=1568339530&s=gateway&sr=8-14";
		Product meguiarsCeraCeramica = new Product(urlmeguiarsCeraCeramica,732,0.9f);
		productos.add(meguiarsCeraCeramica);
		
		
		while(true) {
			logger.info("*********iniciando verificacion de precios**********");
			Tracker.verifyPrices(productos);
			logger.info("Esperando 30s...");
			Thread.sleep(30_000);
		}

	}	
	
}

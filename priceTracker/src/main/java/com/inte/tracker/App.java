package com.inte.tracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import org.apache.log4j.Logger;

import com.inte.tracker.model.Product;
import com.inte.tracker.service.Verifier;

/**
 * Hello world!
 *
 */
public class App {
	static final Logger logger = Logger.getLogger(App.class);

	private static Verifier verificadorPrecio = new Verifier();

	public static void main(String[] args) throws InterruptedException {
		ArrayList<Product> productos = new ArrayList<>();

		String urlQuickDetailer = "https://www.liverpool.com.mx/tienda/pdp/abrillantador-meguiar's-ultimate-quick-detailer-negro/61837434";
		Product quickDetailer = new Product(urlQuickDetailer, 290);
		productos.add(quickDetailer);

		String urlmeguiarsCeraCeramica = "https://www.amazon.com.mx/Meguiar%C2%B4s-G190526-Cera-Cer%C3%A1mica-Empaque/dp/B06WVQ6MVR/ref=sr_1_14?__mk_es_MX=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=meguiars&qid=1568339530&s=gateway&sr=8-14";
		Product meguiarsCeraCeramica = new Product(urlmeguiarsCeraCeramica, 732, 0.9f);
		productos.add(meguiarsCeraCeramica);

		while (true) {
			initTracker(verificadorPrecio, productos);
			logger.info("Esperando 60s...");
			System.out.println("\n\n");
			Thread.sleep(60_000);
		}
	}

	private static void initTracker(Verifier verificadorPrecio, List<Product> productos) {
		logger.info("*********iniciando verificacion de precios**********");
		for (Product producto : productos) {
			try {
				System.out.println("");
				boolean precioObjetivoAlcanzado = verificadorPrecio.verifyPrice(producto);
				logger.info("Precio objetivo alcanzado:" + precioObjetivoAlcanzado);
				if (precioObjetivoAlcanzado) {
					logger.info("*****Enviando correo de notificacion");
				} else {
					logger.info("********Muy caro, no me notifiques");
				}
			} catch (IOException e) {
				logger.info("Servicio no disponible, intenta mas tarde");
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (InvalidAttributeValueException e) {
				logger.info("No se puede leer el elemento precio");
				e.printStackTrace();
			}
		}
	}

}

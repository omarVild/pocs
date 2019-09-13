package com.inte.tracker.service;

import java.io.IOException;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import org.apache.log4j.Logger;

import com.inte.tracker.model.Product;

public class Tracker {
	
	private static Verifier verificadorPrecio = new Verifier();
	static final Logger logger = Logger.getLogger(Tracker.class);
	
	private Tracker() {
	}
	
	public static void verifyPrices(List<Product> productos ) {	
		for(Product producto: productos) {
			try {
				boolean precioObjetivoAlcanzado = verificadorPrecio.verifyPrice(producto);
				logger.info("Precio objetivo alcanzado:" + precioObjetivoAlcanzado);
				if(precioObjetivoAlcanzado) {
					logger.info("*****Enviando correo de notificacion");
				}else {
					logger.info("********Muy caro, no me notifiques");
				}
				
			} catch (IOException e) {
				logger.info("Servicio no disponible, intenta mas tarde");
				e.printStackTrace();
			}catch (NumberFormatException e) {
				e.printStackTrace();
			}catch (InvalidAttributeValueException e) {
				logger.info("No se puede leer el elemento precio");
			}
		}
	}
	
}

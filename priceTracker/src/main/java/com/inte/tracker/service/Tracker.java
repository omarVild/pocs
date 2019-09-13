package com.inte.tracker.service;

import java.io.IOException;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import com.inte.tracker.model.Product;

public class Tracker {
	
	private static Verifier verificadorPrecio = new Verifier();
	
	private Tracker() {
	}
	
	public static void verifyPrices(List<Product> productos ) {	
		for(Product producto: productos) {
			try {
				boolean precioObjetivoAlcanzado = verificadorPrecio.verifyPrice(producto);
				System.out.println(precioObjetivoAlcanzado);
				if(precioObjetivoAlcanzado) {
					System.out.println("*****Enviando correo de notificacion");
				}else {
					System.out.println("********Muy caro, no me notifiques");
				}
				
			} catch (IOException e) {
				System.out.println("Servicio no disponible, intenta mas tarde");
				e.printStackTrace();
			}catch (NumberFormatException e) {
				e.printStackTrace();
			}catch (InvalidAttributeValueException e) {
				System.out.println("No se puede leer el elemento precio");
			}
		}
	}
	
}

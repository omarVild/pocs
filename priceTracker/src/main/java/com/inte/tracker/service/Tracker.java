package com.inte.tracker.service;

import java.io.IOException;
import java.util.ArrayList;

import com.inte.tracker.model.Product;

public class Tracker {
	
	
	public static void main(String[] args) {
		
		ArrayList<Product> productos = new ArrayList<Product>();
		Verifier verificadorPrecio = new Verifier();
		
		Product quickDetailer =  new Product("https://www.liverpool.com.mx/tienda/pdp/abrillantador-meguiar's-ultimate-quick-detailer-negro/61837434", 328, 0.8f);
		productos.add(quickDetailer);
		
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
			}
		}
	}
	
}

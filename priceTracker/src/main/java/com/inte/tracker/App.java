package com.inte.tracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.sound.midi.Track;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.inte.tracker.model.Product;
import com.inte.tracker.service.Tracker;
import com.inte.tracker.service.Verifier;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) throws InterruptedException {
		
		ArrayList<Product> productos = new ArrayList<Product>();
		
		Product quickDetailer =  new Product("https://www.liverpool.com.mx/tienda/pdp/abrillantador-meguiar's-ultimate-quick-detailer-negro/61837434", 328, 0.8f);
		productos.add(quickDetailer);
		
		while(true) {
			System.out.println("*********iniciando verificacion de precios**********");
			Tracker.verifyPrices(productos);
			Thread.sleep(20_000);
		}

	}	
	
}

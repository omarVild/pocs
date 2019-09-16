package com.inte.tracker.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.inte.tracker.model.Product;

public class JsonProductReader {

	public ArrayList<Product> getProductList() {
		ArrayList<Product> productos = new ArrayList<>();
		InputStream productosInputStream = getClass().getClassLoader().getResourceAsStream("productos.json");
		try {
			JsonReader reader = new JsonReader(new InputStreamReader(productosInputStream, "UTF-8"));
			Gson gson = new Gson();

			// Read file in stream mode
			reader.beginArray();
			while (reader.hasNext()) {
				Product producto = gson.fromJson(reader, Product.class);
				if(producto.getPrecioObjetivo()>0) {
					productos.add(producto);
				} else if(producto.getPrecioNormal()>0 && producto.getPorcentajePrecioObjetivo()>0) {
					productos.add(producto);
				}
			}
			reader.endArray();
			reader.close();
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return productos;
	}

}

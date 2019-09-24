package com.inte.tracker.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.inte.tracker.model.Product;

public class JsonProductReader {

	public List<Product> getProductList() {
		ArrayList<Product> productos = new ArrayList<>();
		try {
			InputStream productosInputStream = new FileInputStream(new File("productos.json"));
			JsonReader reader = new JsonReader(new InputStreamReader(productosInputStream, "UTF-8"));
			Gson gson = new Gson();

			// Read file in stream mode
			reader.beginArray();
			while (reader.hasNext()) {
				Product producto = gson.fromJson(reader, Product.class);
				if (producto.getPrecioObjetivo() > 0) {
					productos.add(producto);
				} else if (producto.getPrecioNormal() > 0 && producto.getPorcentajePrecioObjetivo() > 0) {
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

	public void updateStatusProduct(List<Product> productos) throws IOException {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			BufferedWriter outputStream = new BufferedWriter(new FileWriter("productos.json"));
			String productosUpdate = gson.toJson(productos);
			outputStream.write(productosUpdate);
			outputStream.flush();
			outputStream.close();
			System.out.println("*********************saving***************");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

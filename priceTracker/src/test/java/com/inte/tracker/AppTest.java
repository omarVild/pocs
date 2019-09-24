package com.inte.tracker;

import java.io.IOException;
import java.util.List;

import com.inte.tracker.model.Product;
import com.inte.tracker.service.JsonProductReader;
import com.inte.tracker.utils.URLReader;

/**
 * Unit test for simple App.
 */
public class AppTest {

	public static void main(String[] args) throws IOException {
		List<Product> productos  = new AppTest().testGetProductList();
		
		new AppTest().updateProducts(productos);
		
	}
	
	public void updateProducts(List<Product> productos ) throws IOException {
		JsonProductReader productReader = new JsonProductReader();
		productReader.updateStatusProduct(productos);
	}
	
	public List<Product> testGetProductList() {
		JsonProductReader productReader = new JsonProductReader();
		
		List<Product> productos = productReader.getProductList();
		for(Product producto: productos) {
			System.out.println(producto);
			producto.setNotificado(true);
		}
		
		return productos;
	}
	
	public void testURLReader() throws IOException {
		String theURL = "https://www.amazon.com.mx/";
		StringBuilder sb = URLReader.getUrlContents(theURL);
		System.out.println(sb.toString());	
	}
		
}

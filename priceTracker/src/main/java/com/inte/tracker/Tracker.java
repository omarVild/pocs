package com.inte.tracker;

import java.io.IOException;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.inte.tracker.model.Product;
import com.inte.tracker.service.JsonProductReader;
import com.inte.tracker.service.Verifier;
import com.inte.tracker.utils.MailSender;


@Component
public class Tracker {
	static final Logger logger = Logger.getLogger(Tracker.class);

	private static Verifier verificadorPrecio = new Verifier();
	
	@Scheduled(fixedRate = 50000)
	//@Scheduled(cron = "0 0/10 * * * *")
	//@Scheduled(cron = "0 0 0,7,13,22 * * *", zone = "America/Mexico_City")
	public static void initProducts() {
		JsonProductReader jpr = new JsonProductReader();
		initTracker(verificadorPrecio, jpr.getProductList());
		logger.info("****fin****");
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
					MailSender.sendNotification(producto);
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

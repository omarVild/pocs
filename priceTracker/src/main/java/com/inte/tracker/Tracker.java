package com.inte.tracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.inte.tracker.model.Product;
import com.inte.tracker.service.Verifier;
import com.inte.tracker.utils.MailSender;


@Component
public class Tracker {
	static final Logger logger = Logger.getLogger(Tracker.class);

	private static Verifier verificadorPrecio = new Verifier();
	
	@Scheduled(fixedRate = 50000)
	//@Scheduled(cron = "0 0/1 * * * *")
	//@Scheduled(cron = "0 0 6,15,20,1 * * *")
	public static void initProducts() {
		ArrayList<Product> productos = new ArrayList<>();

		String urlQuickDetailer = "https://www.liverpool.com.mx/tienda/pdp/abrillantador-meguiar's-ultimate-quick-detailer-negro/61837434";
		Product quickDetailer = new Product(urlQuickDetailer, 280);
		productos.add(quickDetailer);

		String urlmeguiarsCeraCeramica = "https://www.amazon.com.mx/Meguiar%C2%B4s-G190526-Cera-Cer%C3%A1mica-Empaque/dp/B06WVQ6MVR/ref=sr_1_14?__mk_es_MX=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=meguiars&qid=1568339530&s=gateway&sr=8-14";
		Product meguiarsCeraCeramica = new Product(urlmeguiarsCeraCeramica, 732, 0.9f);
		productos.add(meguiarsCeraCeramica);
		
		String urlCargadorGoPro = "https://www.amazon.com.mx/GoPro-Battery-Charger-Official-Accessory/dp/B01LZHRYJS/ref=sr_1_1?__mk_es_MX=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=GoPro+Dual+Battery+Charger&qid=1568425640&s=electronics&sr=1-1";
		Product cargadorGoPro = new Product(urlCargadorGoPro, 1184, 0.9f);
		productos.add(cargadorGoPro);
		
		String urlMicroFibra ="https://www.amazon.com.mx/Meguiars%C2%B4s-X2000-Toalla-de-Secado/dp/B007VTRBPA/ref=sr_1_34?__mk_es_MX=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=meguiars&qid=1568426728&s=gateway&sr=8-34";
		Product microFibra = new Product(urlMicroFibra, 697, 0.9f);
		productos.add(microFibra);
		
		String urlMeguiarsWax ="https://www.amazon.com.mx/Meguiars-Cera-L%C3%ADquida-Pulir-G18216/dp/B06WVGZL5W/ref=sr_1_1?__mk_es_MX=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=meguiars%2Bwax&qid=1568427340&s=gateway&sr=8-1&th=1";
		Product meguiarsWax = new Product(urlMeguiarsWax, 425, 0.9f);
		productos.add(meguiarsWax);
		
		String urlSupremeShine ="https://www.amazon.com.mx/Meguiars%C2%B4s-G190315-Abrillantador-Llantas-Recubrimiento/dp/B06W5FXCMS/ref=sr_1_108?__mk_es_MX=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=meguiars&qid=1568427680&s=gateway&sr=8-108";
		Product supremeShine = new Product(urlSupremeShine, 499, 0.9f);
		productos.add(supremeShine);
		
		String urlSelladorVidrios = "https://www.liverpool.com.mx/tienda/pdp/sellador-de-parabrisas-meguiar's-glass/1078971629";
		Product selladorVidrios = new Product(urlSelladorVidrios, 279, 0.9f);
		productos.add(selladorVidrios);
		
		String urlLimpiadorCristales = "https://www.amazon.com.mx/Meguiars-G190719-Limpiador-cristales-peque%C3%B1o/dp/B06WLQH52X/ref=sr_1_1?__mk_es_MX=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=Meguiars+Limpiador+de+cristales%2C&qid=1568430588&s=automotive&sr=1-1";
		Product limpiadorCristales = new Product(urlLimpiadorCristales, 297, 0.9f);
		productos.add(limpiadorCristales);
		
		initTracker(verificadorPrecio, productos);
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

package com.inte.tracker.model;

public class Product {

	private int id;
	private String urlProducto;
	private Integer precioNormal;
	private Integer precioObjetivo;
	private float porcentajePrecioObjetivo;
	private String title;
	private boolean notificado;

	public Product(String urlProductoTMP, Integer precioObjetivoTMP) {
		this.urlProducto = urlProductoTMP;
		this.precioObjetivo = precioObjetivoTMP;
	}

	public Product(String urlProductoTMP, Integer precioNormalTMP, float porcentajePrecioObjetivoTMP) {
		this.urlProducto = urlProductoTMP;
		this.precioNormal = precioNormalTMP;
		this.porcentajePrecioObjetivo = porcentajePrecioObjetivoTMP;
	}

	public String getUrlProducto() {
		return urlProducto;
	}

	public void setUrlProducto(String urlProducto) {
		this.urlProducto = urlProducto;
	}

	public Integer getPrecioNormal() {
		return precioNormal;
	}

	public void setPrecioNormal(Integer precioNormal) {
		this.precioNormal = precioNormal;
	}

	public Integer getPrecioObjetivo() {
		return precioObjetivo;
	}

	public float getPorcentajePrecioObjetivo() {
		return porcentajePrecioObjetivo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isNotificado() {
		return notificado;
	}

	public void setNotificado(boolean notificado) {
		this.notificado = notificado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("urlProducto: " + urlProducto + "\n");
		sb.append("precioNormal: " + precioNormal + "\n");
		sb.append("precioObjetivo: " + precioObjetivo + "\n");
		sb.append("porcentajePrecioObjetivo: " + porcentajePrecioObjetivo + "\n");
		sb.append("title: " + title + "\n");
		sb.append("isNotificado: " + notificado + "\n");
		return sb.toString();
	}

}

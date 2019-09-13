package com.inte.tracker.model;

public class Product {

	private String urlProducto;
	private Integer precioNormal;
	private Integer precioObjetivo;
	private float porcentajePrecioObjetivo;
	private boolean isURLbroken;
	
	public Product(String urlProductoTMP, Integer precioObjetivoTMP) {
		this.urlProducto = urlProductoTMP;
		this.precioObjetivo = precioObjetivoTMP;
	}
	
	public Product(String urlProductoTMP, Integer precioNormalTMP, float porcentajePrecioObjetivoTMP) {
		this.urlProducto = urlProductoTMP;
		this.precioNormal = precioNormalTMP;
		this.porcentajePrecioObjetivo =porcentajePrecioObjetivoTMP;
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
	public boolean isURLbroken() {
		return isURLbroken;
	}
	public void setURLbroken(boolean isURLbroken) {
		this.isURLbroken = isURLbroken;
	}
}

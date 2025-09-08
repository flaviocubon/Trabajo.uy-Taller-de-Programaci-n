package com.trabajouy.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Properties;

import jakarta.xml.ws.Endpoint;

public class Consumir {
	private String rutafinal;
	
	public Consumir(String ruta) {
		this.rutafinal = ruta;
	}
	
	public Consumir() {
		String separador = FileSystems.getDefault().getSeparator();
        this.rutafinal = System.getProperty("user.home") + separador + "TrabajoUY_Utilidades" + separador + "config.properties";
	};
	
	public String getRuta() {
		return this.rutafinal;
	}
	
	public String getProperties(String prop) {
    	Properties properties = new Properties();
    	String url = "";
    	try {
    		properties.load(new BufferedReader(new FileReader(this.rutafinal)));
    		url = properties.getProperty(prop);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return url;
	}
}

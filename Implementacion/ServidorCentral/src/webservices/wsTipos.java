package webservices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.HashMap;

import excepciones.compraPaqueteRepetida;
import excepciones.nombrePaqueteRepetido;
import excepciones.nombreTipoPublicacionRepetido;
import excepciones.paqueteYaComprado;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.DTCompra;
import logica.DTPaquete;
import logica.DTTipoPublicacion;
import logica.Fabrica;
import logica.ITipos;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class wsTipos {
	
	private ITipos ctrlTipos; 
	
	private Endpoint endpoint = null;
    //Constructor
    public wsTipos(){
    	ctrlTipos = Fabrica.getInstance().getITipos();
    }

    //Operaciones las cuales quiero publicar

    @WebMethod(exclude = true)
    public void publicar(String rutaFinal){		  
    	Properties properties = new Properties();
    	String url = "";
    	try {
    		properties.load(new BufferedReader(new FileReader(rutaFinal)));
    		url = properties.getProperty("url") + "wsTipos";
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
        endpoint = Endpoint.publish(url, this);
        System.out.println(url);
        //endpoint = Endpoint.publish("http://localhost:9128/wsTipos", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    @WebMethod(exclude = true)
    public void detener() {
    	endpoint.stop();
    }
		
	public  DataTipos obtenerDataTipos(){
		DataTipos tipos = new DataTipos();
		tipos.setValue(ctrlTipos.obtenerDataTipos());
		return tipos;
			
	}

	public  DataPaquetes obtenerDataPaquetes(){
		DataPaquetes paquetes = new DataPaquetes();
		paquetes.setValue(ctrlTipos.obtenerDataPaquetes());
		return paquetes;
		
	};
	public  Boolean empresaPoseePaquete(String nombreEmpresa, String nombrePaquete) {
		return ctrlTipos.empresaPoseePaquete(nombreEmpresa, nombrePaquete);
		
	};
	
	public  void comprarPaquete(String nombreEmpresa, String nombrePaquete, LocalDate fecha) throws compraPaqueteRepetida{
		ctrlTipos.comprarPaquete(nombreEmpresa, nombrePaquete, fecha);
	};
	
	public  WrapperSetString listarPaquetesDisponiblesParaUsuarioYTipo(String nickname, String nombreTipo){
		WrapperSetString paquetesDisp = new WrapperSetString();
		paquetesDisp.setValue(ctrlTipos.listarPaquetesDisponiblesParaUsuarioYTipo(nickname, nombreTipo));
		return paquetesDisp;
	};
	
	public  DTPaquete obtenerDatosPaquete(String nombrePaquete) {
		return ctrlTipos.obtenerDatosPaquete(nombrePaquete);
		
	};

	public  DataCompra obtenerDatosCompra(String nombreEmpresa){
		DataCompra compras = new DataCompra();
		compras.setValue(ctrlTipos.obtenerDatosCompra(nombreEmpresa));
		return compras;	
	};
	
	public void canjearOfertaDePaquete(String nombreEmpresa, String nombreOferta, String nombrePaquete){
		ctrlTipos.canjearOfertaDePaquete(nombreEmpresa, nombreOferta, nombrePaquete);
	}
	
	public void comprarPaqueteFechaActual(String nombreEmpresa, String nombrePaquete) throws compraPaqueteRepetida{
		ctrlTipos.comprarPaquete(nombreEmpresa, nombrePaquete);
	}
}

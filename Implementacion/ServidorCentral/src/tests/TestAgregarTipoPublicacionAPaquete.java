package tests;

import java.util.Date;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.nombrePaqueteRepetido;
import excepciones.nombreTipoPublicacionRepetido;
import excepciones.paqueteYaComprado;
import logica.DTAgrupa;
import logica.DTPaquete;
import logica.Fabrica;
import logica.ITipos;

public class TestAgregarTipoPublicacionAPaquete {
	private static Fabrica fab;
	private static ITipos ctrlTipos;
	
	public TestAgregarTipoPublicacionAPaquete(){
		fab = Fabrica.getInstance();
		ctrlTipos = fab.getITipos();
	}
	
	@BeforeEach
	void clear() {
		ctrlTipos.borrarPaquetes();
		ctrlTipos.borrarTipos();
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	//Agregar a paquete vacio
	void AltaTipoPublicacionTest1() throws nombreTipoPublicacionRepetido, nombrePaqueteRepetido, paqueteYaComprado{
		ctrlTipos.ingresarDatosTipoPublicacion("Basico", "Paquete economico para pequeñas empresas", 0, new Date(2023,4,12), 0, 0);
		ctrlTipos.ingresarDatosPaquete("Paquete Basico", "Paquete de Tipo basico", 20, 0.5,  new Date(2021,5,10), "");
		ctrlTipos.agregarTipoPublicacion("Paquete Basico", "Basico", 3);
		Map<String, DTPaquete> dataPaquetes = ctrlTipos.obtenerDataPaquetes();
		Assertions.assertTrue(dataPaquetes.containsKey("Paquete Basico"));
		
		DTPaquete resultado = dataPaquetes.get("Paquete Basico");
		Assertions.assertTrue(resultado.getNombre() == "Paquete Basico");
		Map<String, DTAgrupa> tiposPaquete = resultado.getTipos();
		Assertions.assertTrue(tiposPaquete.containsKey("Basico"));
		DTAgrupa tipoRes = tiposPaquete.get("Basico");
		Assertions.assertTrue(tipoRes.getCant() == 3 || tipoRes.getNombreTipo() == "Basico");
	};
	
	//Agregar tipo que ya pertenece a paquete, debe sumar la cantidad que se desea agregar
	@SuppressWarnings("deprecation")
	@Test
	void AltaTipoPublicacionTest2() throws nombreTipoPublicacionRepetido, nombrePaqueteRepetido, paqueteYaComprado{
		ctrlTipos.ingresarDatosTipoPublicacion("Basico", "Paquete economico para pequeñas empresas", 0, new Date(2023,4,12), 0, 0);
		ctrlTipos.ingresarDatosPaquete("Paquete Basico", "Paquete de Tipo basico", 20, 0.5, new Date(2021,5,10), "");
		ctrlTipos.agregarTipoPublicacion("Paquete Basico", "Basico", 3);
		ctrlTipos.agregarTipoPublicacion("Paquete Basico", "Basico", 5);
		Map<String, DTPaquete> dataPaquetes = ctrlTipos.obtenerDataPaquetes();
		Assertions.assertTrue(dataPaquetes.containsKey("Paquete Basico"));
		
		DTPaquete resultado = dataPaquetes.get("Paquete Basico");
		Assertions.assertTrue(resultado.getNombre() == "Paquete Basico");
		Map<String, DTAgrupa> tiposPaquete = resultado.getTipos();
		Assertions.assertTrue(tiposPaquete.containsKey("Basico"));
		DTAgrupa tipoRes = tiposPaquete.get("Basico");
		Assertions.assertTrue(tipoRes.getCant() == 8 || tipoRes.getNombreTipo() == "Basico");
	};
}
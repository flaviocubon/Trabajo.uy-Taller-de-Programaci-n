package webservices;

public class Main {

	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        wsTipos webServiceTipos = new wsTipos();
        wsUsuarios webServiceUsuarios = new wsUsuarios();
        String ruta = "config.properties";
        webServiceTipos.publicar(ruta);
        webServiceUsuarios.publicar(ruta);
    }
}

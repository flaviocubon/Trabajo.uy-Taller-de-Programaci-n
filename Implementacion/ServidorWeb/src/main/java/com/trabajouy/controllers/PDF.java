package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservices.DtEmpresa;
import webservices.DtOfertaLaboral;
import webservices.DtPostulacion;
import webservices.DtPostulante;
import webservices.Exception_Exception;
import webservices.NoExisteOferta_Exception;
import webservices.WsUsuarios;
import webservices.WsUsuariosService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class PDF
 */
@WebServlet({"/PDFFile"})
public class PDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PDF() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios port = wsUsuarios.getWsUsuariosPort();
		String ofertaNombre = request.getParameter("oferta");	
		if (ofertaNombre == null || request.getSession().getAttribute("usuarioLogueado") == null || request.getSession().getAttribute("usuarioLogueado") instanceof DtEmpresa) {
			request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
			return;
		}
		DtPostulante user = (DtPostulante) request.getSession().getAttribute("usuarioLogueado");
		String nomArchivo = user.getNickname() + "Comprobante.pdf";
		 response.setContentType("application/pdf"); 
	        response.setHeader( 
	            "Content-disposition", 
	            "inline; filename=" + nomArchivo); 
	        try { 
	        	DtOfertaLaboral oferta = port.seleccionarOfertaLaboral(ofertaNombre);
	        	 List<DtPostulacion> postulaciones = oferta.getPostulaciones();
		            Iterator<DtPostulacion> itpost = postulaciones.iterator();
		            DtPostulacion post = null;
		            while (itpost.hasNext()) {
		            	post = (DtPostulacion) itpost.next(); 
		            	if (post.getPostulante().equals(user.getNickname())) {
		            		if (post.getOrden() != 0) {
		            			break;
		            		} else {
		            			request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
			        			return;
		            		}
		            	}
		            	if(!itpost.hasNext()) {
		        			request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
		        			return;
		            	}
		            }
	            Document document = new Document(); 
	            PdfWriter.getInstance(document, response.getOutputStream()); 
	            document.open(); 
	            Font font = new Font(FontFamily.TIMES_ROMAN, 30f);
	            Paragraph title = new Paragraph("Comprobante de Postulacion a Oferta Laboral", font);
	            title.setAlignment(Element.ALIGN_CENTER);
	            document.add(title); 
	            Image ofertaImagen = Image.getInstance(port.getFile(oferta.getImagen(), "ofertas"));
	            ofertaImagen.scaleToFit(418,418);
	            ofertaImagen.setAlignment(Image.MIDDLE);
	            document.add(ofertaImagen);
	            XMLGregorianCalendar fechaPost = post.getFechaPostulacion();
	            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	            Font valueFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC);
	            PdfPTable pdfPTable = new PdfPTable(2);
	            PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("Nombre de empresa: ", boldFont));
	            PdfPCell pdfpCell1Val = new PdfPCell(new Paragraph(oferta.getNombreEmpresa(), valueFont));
	            PdfPCell pdfPCell2 = new PdfPCell(new Paragraph("Nombre de oferta: ", boldFont));
	            PdfPCell pdfpCell2Val = new PdfPCell(new Paragraph(oferta.getNombre(), valueFont));
	            PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("Nombre del postulante: ", boldFont));
	            PdfPCell pdfpCell3Val = new PdfPCell(new Paragraph(user.getNombre() + " " + user.getApellido(), valueFont));
	            PdfPCell pdfPCell4 = new PdfPCell(new Paragraph("Nickname: ", boldFont));
	            PdfPCell pdfpCell4Val = new PdfPCell(new Paragraph(user.getNickname(), valueFont));
	            PdfPCell pdfPCell5 = new PdfPCell(new Paragraph("Posicion: ", boldFont));
	            PdfPCell pdfpCell5Val = new PdfPCell(new Paragraph(Integer.toString(post.getOrden()), valueFont));
	            PdfPCell pdfPCell6 = new PdfPCell(new Paragraph("Fecha de postulacion: ", boldFont));
	            PdfPCell pdfpCell6Val = new PdfPCell(new Paragraph(fechaPost.getYear() + "-" + fechaPost.getMonth() + "-" + fechaPost.getDay(), valueFont));
	            PdfPCell pdfPCell7 = new PdfPCell(new Paragraph("Fecha del resultado: ", boldFont));
	            PdfPCell pdfpCell7Val = new PdfPCell(new Paragraph(post.getFechaOrden(), valueFont));
	            pdfPTable.addCell(pdfPCell1);
	            pdfPTable.addCell(pdfpCell1Val);
	            pdfPTable.addCell(pdfPCell2);
	            pdfPTable.addCell(pdfpCell2Val);
	            pdfPTable.addCell(pdfPCell3);
	            pdfPTable.addCell(pdfpCell3Val);
	            pdfPTable.addCell(pdfPCell4);
	            pdfPTable.addCell(pdfpCell4Val);
	            pdfPTable.addCell(pdfPCell5);
	            pdfPTable.addCell(pdfpCell5Val);
	            pdfPTable.addCell(pdfPCell6);
	            pdfPTable.addCell(pdfpCell6Val);
	            pdfPTable.addCell(pdfPCell7);
	            pdfPTable.addCell(pdfpCell7Val);
	            document.add(pdfPTable);
	            document.close(); 
	        } 
	        catch (Exception de) { 
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
				return;
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

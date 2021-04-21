package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;

@WebServlet("/PrepareEditArticoloServlet")
public class PrepareEditArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrepareEditArticoloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String idArticoloDaModificare = request.getParameter("idArticolo");
	    
	    if(!NumberUtils.isCreatable(idArticoloDaModificare)) {
	    	request.setAttribute("errorMessage", "Attenzione si è modificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
	    }
	    
	    try {
	    	
	    	request.setAttribute("modifica_attribute", MyServiceFactory.getArticoloServiceInstance().caricaSingoloElemento(Long.parseLong(idArticoloDaModificare)));
	    	
	    } catch(Exception e) {
	    	
	    	e.printStackTrace();
	    	request.setAttribute("errorMessage", "Attenzione si è modificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
	    }
	    
	   RequestDispatcher rd = request.getRequestDispatcher("/articolo/edit.jsp");
       rd.forward(request, response);
	}

}

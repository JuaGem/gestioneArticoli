package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticolijspservletjpamaven.model.Articolo;
import it.gestionearticolijspservletjpamaven.service.ArticoloService;
import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;



@WebServlet("/PrepareDeleteArticoloServlet")
public class PrepareDeleteArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrepareDeleteArticoloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametroIdDellArticoloDiCuiVoglioIlDettaglio = request.getParameter("idArticolo");

		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();

		Articolo abitante = null;

		try {
			abitante = articoloServiceInstance
					.caricaSingoloElemento(Long.parseLong(parametroIdDellArticoloDiCuiVoglioIlDettaglio));
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("visualizza_articolo_attr", abitante);
		RequestDispatcher rd = request.getRequestDispatcher("/articolo/delete.jsp");
		rd.forward(request, response);
	}

}

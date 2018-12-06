package ufc.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufc.models.Fighter;
import ufc.sessionBean.FighterSB;

@WebServlet("/AvatarImage/*")
public class AvatarImage extends HttpServlet {

	@EJB
	private FighterSB fighterSB;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long index = Long.parseLong(request.getPathInfo().substring(1));
		Fighter f = (Fighter)fighterSB.find(index);
		if (f != null && f.getImage() != null) {
			response.setContentType("image/png");
			response.setContentLength(f.getImage().length);
			response.getOutputStream().write(f.getImage());
		} else {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			response.setHeader("Expires", "0"); // Proxies.
			request.getRequestDispatcher("/resources/img/avatar.png").forward(request, response);
		}
	}
}

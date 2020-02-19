package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AcaoBean;
import dao.AcaoDAO;

@WebServlet("/registrarAcao")
public class Acao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AcaoDAO acaoDAO = new AcaoDAO();

	public Acao() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");
		if (action == null) {
			try {
				RequestDispatcher view = req.getRequestDispatcher("/index.jsp");
				req.setAttribute("investimentos", acaoDAO.listar());
				view.forward(req, res);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("delete")) {
			int idAcao = Integer.parseInt(req.getParameter("idAcao"));
			acaoDAO.delete(idAcao);
			try {
				RequestDispatcher view = req.getRequestDispatcher("/index.jsp");
				req.setAttribute("investimentos", acaoDAO.listar());
				req.setAttribute("deleteSuccess", true);
				view.forward(req, res);
			} catch (Exception ex) {
				ex.printStackTrace();
				req.setAttribute("deleteSuccess", false);
			}
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {
			String codigo = req.getParameter("codigo");

			java.util.Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("data"));

			String quantidade = req.getParameter("quantidade");
			String valor = req.getParameter("valor");

			AcaoBean acao = new AcaoBean();

			acao.setCodigo(codigo);
			acao.setData(newDate);
			acao.setQuantidade(Integer.parseInt(quantidade));
			acao.setValor(Double.parseDouble(valor));

			acaoDAO.salvar(acao);
			RequestDispatcher view = req.getRequestDispatcher("/index.jsp");
			req.setAttribute("investimentos", acaoDAO.listar());
			req.setAttribute("saveSuccess", true);
			view.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("saveSuccess", false);
		}
	}

}

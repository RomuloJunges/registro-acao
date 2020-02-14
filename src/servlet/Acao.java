package servlet;

import java.io.IOException;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String codigo = req.getParameter("codigo");
		String dataString = req.getParameter("data");

		String quantidade = req.getParameter("quantidade");
		String valor = req.getParameter("valor");

		AcaoBean acao = new AcaoBean();
		acao.setCodigo(codigo);
		acao.setData(dataString);
		acao.setQuantidade(Double.parseDouble(quantidade));
		acao.setValor(Double.parseDouble(valor));

		acaoDAO.salvar(acao);
		
		RequestDispatcher view = req.getRequestDispatcher("/index.jsp");
		view.forward(req, res);

	}

}

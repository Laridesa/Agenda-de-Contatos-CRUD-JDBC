package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = {"/controller", "/main", "/insert", "/select", "/update", "/delete"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DAO dao = new DAO();
    JavaBeans contato = new JavaBeans();
    
    public Controller() {
        super();
        }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
	    System.out.println(action);
	    if (action.equals("/main")) {
	    	contatos(request, response); 
	    } else if (action.equals("/insert")) {
	    	novoContato(request, response);
	    } else if (action.equals("/select")) {
    	    listarContato(request, response);
        } else if (action.equals("/update")) {
    	    editarContato(request, response);
        }else if (action.equals("/delete")) {
    	    removerContato(request, response);
        }else {
	    	response.sendRedirect("index.html");
	    } 
	}
	
	// Listar contatos 
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		//Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
    }
	
	// Novo contato 
		protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//setar as variáveis JavaBeans
			contato.setNome(request.getParameter("nome"));
			contato.setFone(request.getParameter("fone"));
			contato.setEmail(request.getParameter("email"));
			// invocar o método inserirContato passando o objeto contato
			dao.inserirContato(contato);
			//redirecionar para o documento agenda.jsp
			response.sendRedirect("main");
		}
		
		//Editar contato
		protected void listarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//Recebimento do id do contato que será editado
			String idcon = request.getParameter("idcon");	
			//Setar a variável JavaBeans
		    contato.setIdcon(idcon);
		    //Executar o método selecionar contato (DAO)
		    dao.selecionarContato(contato);
		    //Setar os atributos do formulário com o conteúdo JavaBeans
		    request.setAttribute("idcon", contato.getIdcon());
		    request.setAttribute("nome", contato.getNome());
		    request.setAttribute("fone", contato.getFone());
		    request.setAttribute("email", contato.getEmail());
		    //Encaminhar ao documento editar.jsp
		    RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		    rd.forward(request, response);
		}
		
		protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//Setar as variáveis JavaBeans
			contato.setIdcon(request.getParameter("idcon"));
			contato.setNome(request.getParameter("nome"));
			contato.setFone(request.getParameter("fone"));
			contato.setEmail(request.getParameter("email"));
			//executar o método alterar contato
			dao.alterarContato(contato);
			//redirecionar para o documento agenda.jsp (atualizando as alterações)
			response.sendRedirect("main");
		}
		
		//Remover um contato
		protected void removerContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//Recebimento do id do contato a ser excluído 
			String idcon = request.getParameter("idcon");
			//Setar a variável idcon no JavaBeans
			contato.setIdcon(idcon);
			//executar o método deleletarContato (DAO) passando o objeto contato como parâmetro  
			dao.deletarContato(contato);
			//redirecionar para o documento agenda.jsp (atualizando as alterações)
			response.sendRedirect("main");
		}

}

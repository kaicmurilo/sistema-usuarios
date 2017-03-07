package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;

@WebServlet(urlPatterns="/usucontroller")
public class UsuarioController extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	
	private List<Usuario> lista = new ArrayList<>();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nome = req.getParameter("nome");
		//capturando o que vem do cliente
		String email = req.getParameter("email");
		
		//instanciando objeto	
		Usuario usu = new Usuario(email,nome);
		
		//inserir na lista
		lista.add(usu);
		
		
		
		resp.getWriter().print("\n{nome:"+usu.getNome()+" ,email: "+usu.getEmail()+" }");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String json="[";
		for(int i=0;i<lista.size();i++){
			
			json += "\n{nome:"+ lista.get(i).getNome()+", email: "+lista.get(i).getEmail()+" }";
			if(i<lista.size())
				json+= ",";
		}
		json +="]";
		
		resp.getWriter().print(json);
		
	}
	
}

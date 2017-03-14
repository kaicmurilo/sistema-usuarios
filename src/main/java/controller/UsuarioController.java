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
		for(int i=0;i<lista.size()-1;i++){
			
			json += "\n{nome:"+ lista.get(i).getNome()+", email: "+lista.get(i).getEmail()+" }";
			if(i<lista.size())
				json+= ",";
		}
		json +="]";
		
		resp.getWriter().print(json);
		
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		/* deletando por indice
		String nome = req.getParameter("nome");
		
		for(int i=0;i<lista.size();i++){
			if(lista.get(i).getNome().equals(nome)){
				lista.remove(i);
				i=-1;
				resp.getWriter().print("\nusuario removido");
			}
		}*/
		
		//deletando por indice 
		int ind = Integer.parseInt("ind");
		for(int i=0;i<lista.size();i++){
			if(i == ind){
				lista.remove(i);
				resp.getWriter().print("\nusuario removido");
			}
		}
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int ind = Integer.parseInt("ind");
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		
		if(nome == null){
			lista.get(ind).setNome(nome);
		}
		if(email == null){
			lista.get(ind).setEmail(email);
		}
	}
}

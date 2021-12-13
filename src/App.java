import java.util.ArrayList;

import Amigo.*;
import Emprestimo.*;
import Item.*;

public class App {

    private static Biblioteca bib = new Biblioteca("Biblioteca Pessoal");
	private static ListaEmprestimos emprestimos = new ListaEmprestimos();
	private static ListaAmigos amigos = new ListaAmigos();

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        // apenas alguns exemplos de manipula��o em rela��o a amigos
		// da mesma forma faz com biv e emprestimos
		// criando fun��es static na main para implementar as op��es do menu
		
		int idAmigo;
		idAmigo = amigos.addAmigo("Fulano");
		System.out.println("Adicionado com c�digo " + idAmigo);

		idAmigo = amigos.addAmigo("Ciclano");
		System.out.println("Adicionado com c�digo " + idAmigo);

		ArrayList<Amigo> alAmigos = amigos.getListaAmigos();
		for (Amigo amigo : alAmigos) {
			System.out.println(amigo);
		}
		
		
		/*  Menu do sistema Biblioteca Pessoal
	     *  0 - sair
	     *  1 - cadastrar item
	     *  2 - cadastrar amigo
	     *  3 - emprestar
	     *  4 - devolver
	     *  5 - listar emprestimos atuais
	     *  6 - listar hist�rico de empr�stimos
	     *  7 - listar biblioteca
	     */
    }
}

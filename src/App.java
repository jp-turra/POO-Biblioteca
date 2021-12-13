import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import Amigo.*;
import Armazenamento.Armazenamento;
import Emprestimo.*;
import Item.*;

public class App {

    private static Biblioteca biblioteca = new Biblioteca("Biblioteca Pessoal");
	private static ListaEmprestimos emprestimos = new ListaEmprestimos();
	private static ListaAmigos amigos = new ListaAmigos();
	protected static int menu;
	public static Armazenamento armazenamento = new Armazenamento();

    public static void main(String[] args) throws Exception {

		amigos = (ListaAmigos) armazenamento.db_seed_amigos(amigos);
		emprestimos = armazenamento.get_lista_emprestimo();
		biblioteca = armazenamento.get_biblioteca();
		ArrayList<Amigo> alAmigos = amigos.getListaAmigos();
		
		Scanner scanner = new Scanner(System.in);		
		do {
			try {
				mostrarMenu();
				menu = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Entrada Inválida, tente novamente.");
				menu = 8;
			}
			switch (menu) {
				case 0:
					System.out.println("Até logo!!");
					break;
				case 1:
					// Cadastrar Item
					biblioteca.adicionarItem();
					break;
				case 2:
					// Cadastrar Amigo
					break;
				case 3:
					// Cadastrar Emprestimo
					break;
				case 4:
					// Cadastrar Devolução
					break;
				case 5:
					// Mostrar Itens emprestados atualmente
					break;
				case 6:
					// Mostrar histórico de emprestimos
					break;
				case 7:
					// Mostrar itens da biblioteca
					break;
				default:
					break;
			}
			armazenamento.set_table("biblioteca", biblioteca);
			armazenamento.set_table("lista_amigo", amigos);
			armazenamento.set_table("lista_emprestimo", emprestimos);
		} while (menu > 0);
		scanner.close();
    }

	public static void mostrarMenu() {
		System.out.println("\n****************************************************\n");
		System.out.println("\n1) Cadastrar novo item");
		System.out.println("\n2) Cadastrar um amigo");
		System.out.println("\n3) Cadastrar um empréstimo");
		System.out.println("\n4) Cadastrar uma devolução");
		System.out.println("\n5) Mostrar itens emprestados");
		System.out.println("\n6) Mostrar histórico de empréstimos");
		System.out.println("\n7) Mostrar itens da biblioteca");
		System.out.println("\n0) Sair");
		System.out.println("\nSelecione uma opção: ");
	}
}

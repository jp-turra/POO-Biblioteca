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

		amigos = armazenamento.db_seed_amigos();
		biblioteca = armazenamento.db_seed_biblioteca();
		emprestimos = armazenamento.db_seed_emprestimos();

		Scanner scanner = new Scanner(System.in);
		do {
			try {
				mostrarMenu();
				menu = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Entrada Inválida, tente novamente.");
				menu = 8;
			}
			if (menu == 0) {
				System.out.println("Até logo!!");
			} else if (menu == 1) {
				// Cadastrar Item
				biblioteca.adicionarItem();
			} else if (menu == 2) {
				// Cadastrar Amigo - Rede de Predição
				System.out.println("\nInsira o nome do seu amigo: ");
				String nome = scanner.nextLine();
				amigos.addAmigo(nome);
			} else if (menu == 3) {
				// Cadastrar Emprestimo
				int item_id = mostrarMenuBiblioteca(scanner);
				if (item_id < 0)
					continue;
				int amigo_id = mostrarMenuAmigos(scanner);
				Item item = emprestimos.registrar(item_id, amigo_id);
				if (item != null)
					biblioteca.setDisponibilidadeItem(item);
				armazenamento.set_table("biblioteca", biblioteca);

			} else if (menu == 4) {
				// Cadastrar Devolução
				System.out.println("\nInsira o id do item emprestado: ");
				int id = Integer.parseInt(scanner.nextLine());
				Item item = emprestimos.devolver(id);
				if (item != null)
					biblioteca.setDisponibilidadeItem(item);
			} else if (menu == 5) {
				// Mostrar Itens emprestados atualmente
				emprestimos.mostrarEmprestados();
			} else if (menu == 6) {
				// Mostrar histórico de emprestimos
				System.out.println("\nInsira o id do item emprestado: ");
				int id = Integer.parseInt(scanner.nextLine());
				emprestimos.mostrarHistorico(id);
			} else if (menu == 7) {
				// Mostrar itens da biblioteca
				System.out.println("\n****************************************************\n");
				System.out.println(biblioteca.toString());
			} else if (menu == 8) {
				mostrarMenuDisponibilidade(scanner);
			}

			armazenamento.set_table("lista_amigo", amigos);
			armazenamento.set_table("biblioteca", biblioteca);
			armazenamento.set_table("lista_emprestimo", emprestimos);
		} while (menu > 0);
		scanner.close();
	}

	public static void mostrarMenu() {
		System.out.println("\n****************************************************\n");
		System.out.println("\n1) Cadastrar novo item");
		System.out.println("2) Cadastrar um amigo");
		System.out.println("3) Cadastrar um empréstimo");
		System.out.println("4) Cadastrar uma devolução");
		System.out.println("5) Mostrar itens emprestados");
		System.out.println("6) Mostrar histórico de empréstimos");
		System.out.println("7) Mostrar itens da biblioteca");
		System.out.println("8) Mudar disponibilidade de um item");
		System.out.println("0) Sair");
		System.out.println("\nSelecione uma opção: ");
	}

	public static int mostrarMenuBiblioteca(Scanner scanner) {
		System.out.println("\n****************************************************\n");
		System.out.println("Itens disponíveis: ");
		boolean hasItem = false;
		for (int i = 1; i <= biblioteca.getAlItem().size(); i++) {
			if (biblioteca.getAlItem().get(i - 1).getDispItem() == Disponibilidade.DISPONIVEL) {
				System.out.println("\n" + String.valueOf(i) + ") " + biblioteca.getAlItem().get(i - 1).getTituloItem());
				hasItem = true;
			}

		}
		if (!hasItem) {
			System.out.println("\nNenhum item disponível");
			return -1;
		}

		System.out.println("\nSelecione uma opção:");
		int id = Integer.parseInt(scanner.nextLine());
		return biblioteca.getAlItem().get(id - 1).getIdItem();
	}

	public static int mostrarMenuAmigos(Scanner scanner) {
		System.out.println("\n****************************************************\n");
		System.out.println("Amigos disponíveis: ");
		for (int i = 1; i <= amigos.getListaAmigos().size(); i++) {
			System.out.println("\n" + String.valueOf(i) + ") " + amigos.getListaAmigos().get(i - 1).getNomeAmigo());
		}
		System.out.println("\nSelecione uma opção: ");
		int id = Integer.parseInt(scanner.nextLine());
		return amigos.getListaAmigos().get(id - 1).getIdAmigo();
	}

	public static void mostrarMenuDisponibilidade(Scanner scanner) {
		System.out.println("\nInsira o id do item emprestado: ");
		int id = Integer.parseInt(scanner.nextLine());
		Item item = biblioteca.getItem(id);
		if (item == null) {
			System.out.println("\nITEM NÃO ENCONTRADO!");
			return;
		}
		System.out.println("Selecionae uma opção: ");
		String s = "";
		boolean liberado = true;
		for (Disponibilidade d : Disponibilidade.values()) {
			if (!(item.getDispItem().compareTo(Disponibilidade.EMPRESTADO) == 0
					&& d.compareTo(Disponibilidade.CONSULTALOCAL) == 0))
				s += String.valueOf(d.getValor()) + ") " + d.name() + "\n";
			else
				liberado = false;
		}
		System.out.println(s);
		try {
			id = Integer.parseInt(scanner.nextLine());
			if (!liberado && Disponibilidade.CONSULTALOCAL.getValor() == id) {
				System.out.println("Opção inválida!");
				return;
			}
			Disponibilidade d = item.getDispItem().getDisponibilidade(id);
			System.out.println(d.name());
			item.alterarDisponibilidade(d);
			biblioteca.setDisponibilidadeItem(item);
		} catch (Exception e) {
			System.out.println("Operação inválida");
			e.printStackTrace();
		}
	}
}

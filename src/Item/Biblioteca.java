package Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Biblioteca {

	private String nomeBib;
	private ArrayList<Item> alItem = new ArrayList<Item>();;
	private int last_id = 0;

	public Biblioteca(String nomeBib) {
		this.nomeBib = nomeBib;
	}

	public Biblioteca(String nomeBib, ArrayList<Item> itens, int lastId) {
		this.nomeBib = nomeBib;
		this.alItem = itens;
		this.last_id = lastId;
	}

	public void adicionarItem() {
		Scanner scanner = new Scanner(System.in);
		// Adicionar Livro, Dvd ou Documentario
		System.out.println("\n************************************************\n");
		System.out.println("Selecione o tipo de item que deseja adicionar (1, 2 ou 3)");
		System.out.println("\n1) Livro");
		System.out.println("\n2) DVD");
		System.out.println("\n3) Documentário");
		int tipo = Integer.parseInt(scanner.nextLine());
		System.out.println("\nInsira o titulo do item: ");
		String titulo = scanner.nextLine();
		if (this.alItem == null) {
			this.alItem = new ArrayList<Item>();
		}
		switch (tipo) {
			case 1:
				// Livro
				System.out.println("\nInsira o nome do autor: ");
				String autor = scanner.nextLine();
				System.out.println("\nInsira a quantia total de páginas: ");
				int duracao = Integer.parseInt(scanner.nextLine());

				Livro livro = new Livro(titulo, autor, duracao, this.last_id);
				alItem.add(livro);
				break;
			case 2:
				// DVD
				System.out.println("\nInsira o nome do autor: ");
				autor = scanner.nextLine();
				System.out.println("\nInsira o tempo de duração: ");
				duracao = Integer.parseInt(scanner.nextLine());

				Dvd dvd = new Dvd(titulo, autor, duracao, this.last_id);
				alItem.add(dvd);
				break;
			case 3:
				// DVD
				System.out.println("\nInsira o tema: ");
				String tema = scanner.nextLine();
				System.out.println("\nInsira o tempo de duração: ");
				duracao = Integer.parseInt(scanner.nextLine());

				Documentario documentario = new Documentario(titulo, tema, duracao, this.last_id);
				alItem.add(documentario);
				break;
			default:
				System.out.println("\nOpção inválida");
				break;
		}
		this.last_id++;
	}

	public ArrayList<Item> getAlItem() {
		return this.alItem;
	}

	public Item getItem(int itemId) {
		for (Item i : this.alItem) {
			if (i.getIdItem() == itemId)
				return i;
		}
		return null;
	}

	public void setDisponibilidadeItem(Item item) {
		for (Item i : this.alItem) {
			if (i.getIdItem() == item.getIdItem())
				i.alterarDisponibilidade(item.getDispItem());
		}
	}

	public void setarItem(Item item) {
		this.alItem.add(item);
		this.last_id++;
	}

	public class SortByName implements Comparator<Item> {
		@Override
		public int compare(Item item, Item item1) {
			// TODO Auto-generated method stub
			return item.getTituloItem().compareTo(item1.getTituloItem());
		}

	}

	@Override
	public String toString() {
		String s = "";
		ArrayList<Item> itens = this.getAlItem();
		Collections.sort(itens, new SortByName());
		for (Item item : itens) {
			s += item.getTituloItem() + " - " + item.getDispItem() + "\n";
		}
		return s;
	}
}

package Emprestimo;

import java.time.LocalDate;
import java.util.ArrayList;

import Item.Disponibilidade;
import Item.Item;

public class ListaEmprestimos {

	private ArrayList<Emprestimo> alEmprestimos;
	private int last_id = 0;

	public ListaEmprestimos() {
		super();
		this.alEmprestimos = new ArrayList<Emprestimo>();
	}

	public ListaEmprestimos(ArrayList<Emprestimo> emprestimos, int lastId) {
		super();
		this.alEmprestimos = emprestimos;
		this.last_id = lastId;
	}

	public Item registrar(int item_id, int amigo_id) {
		Emprestimo emprestimo = new Emprestimo(item_id, amigo_id, this.last_id + 1);
		Item item = emprestimo.getItem();
		item.alterarDisponibilidade(Disponibilidade.EMPRESTADO);
		this.alEmprestimos.add(emprestimo);
		this.last_id++;
		return item;
	}

	public Item devolver(int item_id) {
		for (int i = 0; i < this.alEmprestimos.size(); i++) {
			Emprestimo emprestimo = this.alEmprestimos.get(i);
			if (emprestimo.itemEstaEmprestado() == true && emprestimo.getIdItem() == item_id) {
				emprestimo.setDataDevolucao(LocalDate.now());
			}
			Item item = emprestimo.getItem();
			if (item != null) {
				item.alterarDisponibilidade(Disponibilidade.DISPONIVEL);
				return item;
			} else
				System.out.println("ITEM NÃO IDENTIFICADO");
		}
		return null;
	}

	public ArrayList<Emprestimo> getAlEmprestimos() {
		return alEmprestimos;
	}

	public void mostrarEmprestados() {
		String s = "\n";
		if (this.alEmprestimos.size() == 0) {
			System.out.println("\nSem emprestimos");
			return;
		}
		boolean hasEmprestimo = false;
		for (Emprestimo e : this.alEmprestimos) {
			if (e.itemEstaEmprestado()) {
				s += e.toString() + "\n";
				hasEmprestimo = true;
			}
		}
		System.out.println(hasEmprestimo ? s : "\nSem emprestimos");
	}

	public void mostrarHistorico(int id) {
		String s = "\n";
		if (this.alEmprestimos.size() == 0) {
			System.out.println("\nSem emprestimos");
			return;
		}
		boolean hasEmprestimo = false;
		for (Emprestimo e : this.alEmprestimos) {
			if (!e.itemEstaEmprestado() && e.getIdItem() == id) {
				s += e.toString() + "\n";
				hasEmprestimo = true;
			}
		}
		System.out.println(hasEmprestimo ? s : "\nSem emprestimos");
	}
}

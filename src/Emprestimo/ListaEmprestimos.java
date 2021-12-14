package Emprestimo;
import java.time.LocalDate;
import java.util.ArrayList;

import Item.Item;

public class ListaEmprestimos {

	private ArrayList<Emprestimo> alEmprestimos;
	private int last_id = 0;

	public ListaEmprestimos() {
		super();
		this.alEmprestimos = new ArrayList<Emprestimo>();
	}	
	
	public void registrar(int item_id, int amigo_id) {
		Emprestimo emprestimo = new Emprestimo(item_id, amigo_id, this.last_id+1);
		Item item = emprestimo.getItem();
		item.alterarDisponibilidade(3); // Empretado
		this.alEmprestimos.add(emprestimo);
		this.last_id ++;
	}
	
	public void devolver(int item_id) {
		for (int i = 0; i < this.alEmprestimos.size(); i++) {
			Emprestimo emprestimo = this.alEmprestimos.get(i);
			if (emprestimo.itemEstaEmprestado() == true && emprestimo.getIdItem() == item_id) {
				emprestimo.setDataDevolucao(LocalDate.now());
			}
			Item item = emprestimo.getItem();
			item.alterarDisponibilidade(1);
		}
	}

	public ArrayList<Emprestimo> getAlEmprestimos() {
		return alEmprestimos;
	}
	// construtor
	// getters e setters
	// toString
}

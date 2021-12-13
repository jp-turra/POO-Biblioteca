package Emprestimo;
import java.time.LocalDate;
import java.util.ArrayList;

public class ListaEmprestimos {

	private ArrayList<Emprestimo> alEmprestimos;
	private int last_id = 0;

	public ListaEmprestimos() {
		super();
		this.alEmprestimos = new ArrayList<Emprestimo>();
	}	
	
	public void registrar(int item_id, int amigo_id) {
		Emprestimo emprestimo = new Emprestimo(item_id, amigo_id, this.last_id+1);
		this.alEmprestimos.add(emprestimo);
		this.last_id ++;
	}
	
	public void devolver(int item_id) {
		for (int i = 0; i < this.alEmprestimos.size(); i++) {
			Emprestimo item = this.alEmprestimos.get(i);
			if (item.itemEstaEmprestado() == true && item.getIdItem() == item_id) {
				item.setDataDevolucao(LocalDate.now());
			}
		}
	}
	// construtor
	// getters e setters
	// toString
}

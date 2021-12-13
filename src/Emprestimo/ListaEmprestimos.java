package Emprestimo;
import java.util.ArrayList;

public class ListaEmprestimos {

	private ArrayList<Emprestimo> alEmprestimos;
	private int idEmprestimo;

	public ListaEmprestimos() {
		super();
		this.alEmprestimos = new ArrayList<Emprestimo>();
	}	
	
	public void registrar(int item_id, int amigo_id) {
		Emprestimo emprestimo = new Emprestimo(item_id, amigo_id);
		this.alEmprestimos.add(emprestimo);
	}
	// construtor
	// getters e setters
	// toString
}

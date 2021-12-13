package Emprestimo;
import java.time.LocalDate;

import Amigo.Amigo;
import Item.Item;

public class Emprestimo {

	private int idAmigo;
	private int idItem;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;
	public int idEmprestimo;
	
	// para a equipe pensar nesta repeti��o, pois pode-se obter os objetos pelo IDs
	private Item item;
	private Amigo amigo;

	public Emprestimo(int item_id, int amigo_id, int id) { 
		this.idAmigo = amigo_id;
		this.idItem = item_id;
		this.idEmprestimo = id;
		this.dataEmprestimo = LocalDate.now(); 
	}
	public boolean itemEstaEmprestado() {
		return dataDevolucao == null;
	}
	public int getIdItem() {
		return idItem;
	}
	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
}

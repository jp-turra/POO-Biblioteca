package Emprestimo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Amigo.Amigo;
import Armazenamento.Armazenamento;
import Item.Disponibilidade;
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
		return this.getItem().getDispItem() == Disponibilidade.EMPRESTADO && this.dataDevolucao == null;
	}

	public int getIdItem() {
		return idItem;
	}

	public Item getItem() {
		return new Armazenamento().get_biblioteca().getItem(idItem);
	}

	public Amigo getAmigo() {
		return new Armazenamento().get_lista_amigos().getAmigo(idAmigo);
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String s = this.getItem().getTituloItem()
				+ " - Alugado por: "
				+ this.getAmigo().getNomeAmigo()
				+ " - Alugado em: "
				+ this.getDataEmprestimo().format(formatter);
		if (!this.itemEstaEmprestado())
			s += "até " + this.getDataDevolucao().format(formatter);
		return s;
	}
}

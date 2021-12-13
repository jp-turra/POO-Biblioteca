package Amigo;
import java.util.ArrayList;

public class ListaAmigos {

	private ArrayList<Amigo> alAmigos;
	private int last_id = 0;
	
	// construtor
	public ListaAmigos() {
		super();
		this.alAmigos = new ArrayList<Amigo>();
	}
	
	public int addAmigo (String nome) {
		int idAmigo = this.getLast_id()+1;
		Amigo amigo = new Amigo(idAmigo, nome);
		alAmigos.add(amigo);
		// this.sort();
		this.last_id = idAmigo;
		return idAmigo;
	}
	
	public ArrayList<Amigo> getListaAmigos(){
		return alAmigos;
	}
	// public void sort() {
	// 	this.alAmigos.sort(null);
	// }
	// getters e setters
	public int getLast_id() {
		return last_id;
	}
	// toString

}

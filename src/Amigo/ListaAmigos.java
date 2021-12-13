package Amigo;
import java.util.ArrayList;

public class ListaAmigos {

	private ArrayList<Amigo> alAmigos;
	
	// construtor
	public ListaAmigos() {
		super();
		this.alAmigos = new ArrayList<Amigo>();
	}
	
	public int addAmigo (String nome) {
		int idAmigo = alAmigos.size() + 1; 
		Amigo amigo = new Amigo(idAmigo, nome);
		alAmigos.add(amigo);
		return idAmigo;
	}
	
	public ArrayList<Amigo> getListaAmigos(){
		return alAmigos;
	}
	// getters e setters
	// toString

}

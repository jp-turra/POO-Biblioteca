package Armazenamento;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Amigo.ListaAmigos;
import Emprestimo.ListaEmprestimos;
import Item.Biblioteca;

public class Armazenamento {
    
    public String projectPath;
    private ArrayList<String> db_tables = new ArrayList<String>(List.of("lista_amigo", "lista_emprestimo", "biblioteca"));

    public Armazenamento() {
        this.projectPath = System.getProperty("user.dir");
        // Cria arquivos JSON para armazenar dados
        this.db_init();
    }

    public void db_init() {
        this.db_tables.forEach((table_name) -> {
            File file = new File(this.projectPath+"/src/Armazenamento/db/"+table_name+".json");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Erro ao carregar tabela: "+table_name);
                System.out.println(e.getMessage());
            }
        });
    }
    public ListaAmigos db_seed_amigos(ListaAmigos lista_amigos) {
        String tabela = "lista_amigo";
        ListaAmigos table_amigos = this.get_lista_amigos();
        if (table_amigos == null) {
            table_amigos = lista_amigos;
            table_amigos.addAmigo("Jorge");
            table_amigos.addAmigo("Alves");
            this.set_table(tabela, table_amigos);
        }
        return table_amigos;
    }

    public ListaAmigos get_lista_amigos() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ListaAmigos arrayList = gson.fromJson(new FileReader(this.projectPath + "/src/Armazenamento/db/lista_amigo.json"), ListaAmigos.class);
            return arrayList;
        } catch (Exception e) {
            System.out.println("\nArquivo não encontrado\n");
            System.out.println(e);
            return null;
        }
    }
    public ListaEmprestimos get_lista_emprestimo() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ListaEmprestimos arrayList = gson.fromJson(new FileReader(this.projectPath + "/src/Armazenamento/db/lista_amigo.json"), ListaEmprestimos.class);
            return arrayList;
        } catch (Exception e) {
            System.out.println("\nArquivo não encontrado\n");
            System.out.println(e);
            return null;
        }
    }
    public Biblioteca get_biblioteca() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Biblioteca arrayList = gson.fromJson(new FileReader(this.projectPath + "/src/Armazenamento/db/lista_amigo.json"), Biblioteca.class);
            return arrayList;
        } catch (Exception e) {
            System.out.println("\nArquivo não encontrado\n");
            System.out.println(e);
            return null;
        }
    }
    public void set_table(String table, Object json) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter file = new FileWriter(this.projectPath+"/src/Armazenamento/db/"+table+".json");
            file.write(gson.toJson(json));
            file.close();
        } catch (Exception e) {
            System.out.println("\nErro ao salvar dados\n");
        }
    }
}

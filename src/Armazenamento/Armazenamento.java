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
    public ListaAmigos db_seed_amigos() {
        String tabela = "lista_amigo";
        ListaAmigos table_amigos = this.get_lista_amigos();
        if (table_amigos.getListaAmigos().size() == 0) {
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
            if (arrayList == null) {
                ListaAmigos amigos = new ListaAmigos();
                this.set_table("lista_amigo", amigos);
                return amigos;
            }
            System.out.println("Amigos: ");
            System.out.println(arrayList.getListaAmigos().size());
            return arrayList;
        } catch (Exception e) {
            System.out.println("\nArquivo não encontrado\n");
            System.out.println(e);
            return new ListaAmigos();
        }
    }
    public ListaEmprestimos get_lista_emprestimo() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ListaEmprestimos arrayList = gson.fromJson(new FileReader(this.projectPath + "/src/Armazenamento/db/lista_amigo.json"), ListaEmprestimos.class);
            if (arrayList == null) {
                ListaEmprestimos emprestimos = new ListaEmprestimos();
                this.set_table("lista_emprestimo", emprestimos);
                return emprestimos;
            }
            System.out.println("Emprestimos: ");
            System.out.println(arrayList.getAlEmprestimos().size());
            return arrayList;
        } catch (Exception e) {
            System.out.println("\nArquivo não encontrado\n");
            System.out.println(e);
            return new ListaEmprestimos();
        }
    }
    public Biblioteca get_biblioteca() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Biblioteca arrayList = gson.fromJson(new FileReader(this.projectPath + "/src/Armazenamento/db/lista_amigo.json"), Biblioteca.class);
            if (arrayList == null) {
                {
                    Biblioteca biblioteca = new Biblioteca("Biblioteca Pessoal");
                    this.set_table("biblioteca", biblioteca);
                    return biblioteca;
                }
            }
            System.out.println("Biblioteca: ");
            System.out.println(arrayList.getAlItem().size());
            return arrayList;
        } catch (Exception e) {
            System.out.println("\nArquivo não encontrado\n");
            System.out.println(e);
            return new Biblioteca("Biblioteca Pessoal");
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

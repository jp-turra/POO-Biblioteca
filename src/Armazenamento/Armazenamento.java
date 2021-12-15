package Armazenamento;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Amigo.ListaAmigos;
import Emprestimo.ListaEmprestimos;
import Item.Biblioteca;
import Item.Disponibilidade;
import Item.Documentario;
import Item.Dvd;
import Item.Item;
import Item.Livro;

public class Armazenamento {

    public String projectPath;
    private ArrayList<String> db_tables = new ArrayList<String>(
            List.of("lista_amigo", "lista_emprestimo", "biblioteca"));

    public Armazenamento() {
        this.projectPath = System.getProperty("user.dir");
        // Cria arquivos JSON para armazenar dados
        this.db_init();
    }

    public void db_init() {
        this.db_tables.forEach((table_name) -> {
            File file = new File(this.projectPath + "/src/Armazenamento/db/" + table_name + ".json");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Erro ao carregar tabela: " + table_name);
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
            ListaAmigos arrayList = gson.fromJson(
                    new FileReader(this.projectPath + "/src/Armazenamento/db/lista_amigo.json"), ListaAmigos.class);
            if (arrayList == null) {
                ListaAmigos amigos = new ListaAmigos();
                this.set_table("lista_amigo", amigos);
                return amigos;
            }
            return arrayList;
        } catch (Exception e) {
            System.out.println("\nArquivo não encontrado\n");
            System.out.println(e);
            return new ListaAmigos();
        }
    }

    public ListaEmprestimos get_lista_emprestimo() {
        try {
            Gson gson = new Gson();
            ListaEmprestimos arrayList = gson.fromJson(
                    new FileReader(this.projectPath + "/src/Armazenamento/db/lista_emprestimo.json"),
                    ListaEmprestimos.class);
            if (arrayList == null) {
                ListaEmprestimos emprestimos = new ListaEmprestimos();
                this.set_table("lista_emprestimo", emprestimos);
                return emprestimos;
            }
            return arrayList;
        } catch (Exception e) {
            System.out.println("\nArquivo não encontrado\n");
            System.out.println(e);
            return new ListaEmprestimos();
        }
    }

    public Biblioteca get_biblioteca() {
        try {
            Gson gson = new Gson();
            JsonObject jsonObject = JsonParser
                    .parseReader(new FileReader(this.projectPath + "/src/Armazenamento/db/biblioteca.json"))
                    .getAsJsonObject();
            if (jsonObject == null) {
                Biblioteca biblioteca = new Biblioteca("Biblioteca Pessoal");
                this.set_table("biblioteca", biblioteca);
                return biblioteca;
            }
            String nome = jsonObject.get("nomeBib").getAsString();
            int last_id = jsonObject.get("last_id").getAsInt();
            JsonArray jsonArray = jsonObject.get("alItem").getAsJsonArray();
            ArrayList<Item> itens = new ArrayList<Item>();
            if (jsonArray.size() > 0)
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject itemObj = jsonArray.get(i).getAsJsonObject();
                    ArrayList<String> keys = new ArrayList<String>(itemObj.keySet());
                    String key = keys.get(0);
                    String titulo = itemObj.get("tituloItem").getAsString();
                    int id = itemObj.get("IdItem").getAsInt();
                    Disponibilidade disponibilidade = null;
                    if (itemObj.get("dispItem") != null)
                        disponibilidade = gson.fromJson(itemObj.get("dispItem"), Disponibilidade.class);

                    switch (key) {
                        case "autorLivro":
                            String extra = itemObj.get("autorLivro").getAsString();
                            int quantidade = itemObj.get("totPagLivro").getAsInt();
                            Livro livro = new Livro(titulo, extra, quantidade, id);
                            livro.alterarDisponibilidade(disponibilidade);
                            itens.add(livro);
                            break;
                        case "autor":
                            extra = itemObj.get("autor").getAsString();
                            quantidade = itemObj.get("duracao").getAsInt();
                            Dvd dvd = new Dvd(titulo, extra, quantidade, id);
                            dvd.alterarDisponibilidade(disponibilidade);
                            itens.add(dvd);
                            break;
                        case "tema":
                            extra = itemObj.get("tema").getAsString();
                            quantidade = itemObj.get("totalMinutos").getAsInt();
                            Documentario documentario = new Documentario(titulo, extra, quantidade, id);
                            documentario.alterarDisponibilidade(disponibilidade);
                            itens.add(documentario);
                            break;
                        default:
                            break;
                    }
                }
            Biblioteca biblioteca = new Biblioteca(nome, itens, last_id);
            this.set_table("biblioteca", biblioteca);
            return biblioteca;
        } catch (Exception e) {
            System.out.println("\nArquivo não encontrado\n");
            System.out.println(e);
            Biblioteca biblioteca = new Biblioteca("Biblioteca Pessoal");
            this.set_table("biblioteca", biblioteca);
            return biblioteca;
        }
    }

    public void set_table(String table, Object json) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter file = new FileWriter(this.projectPath + "/src/Armazenamento/db/" + table + ".json");
            file.write(gson.toJson(json));
            file.close();
        } catch (Exception e) {
            System.out.println("\nErro ao salvar dados\n");
        }
    }
}

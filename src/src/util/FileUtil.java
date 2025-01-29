package util;

import model.Task;
import model.Status;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static void salvarTarefas(List<Task> tarefas, String nomeArquivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Task t : tarefas) {
                String linha = t.getNome() + ";"
                        + t.getDescricao() + ";"
                        + t.getDataTermino() + ";"
                        + t.getNivelPrioridade() + ";"
                        + t.getCategoria() + ";"
                        + t.getStatus();
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas: " + e.getMessage());
        }
    }

    public static List<Task> carregarTarefas(String nomeArquivo) {
        List<Task> tarefas = new ArrayList<>();
        File file = new File(nomeArquivo);

        if (!file.exists()) {
            return tarefas;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                
                if (partes.length == 6) {
                    String nome = partes[0];
                    String descricao = partes[1];
                    LocalDate dataTermino = LocalDate.parse(partes[2]);
                    int prioridade = Integer.parseInt(partes[3]);
                    String categoria = partes[4];
                    Status status = Status.valueOf(partes[5]);

                    Task tarefa = new Task(nome, descricao, dataTermino,
                            prioridade, categoria, status);
                    tarefas.add(tarefa);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar tarefas: " + e.getMessage());
        }

        return tarefas;
    }
}

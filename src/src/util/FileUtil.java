package util;

import model.Task;
import model.Status;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public static void salvarTarefas(List<Task> tarefas, String nomeArquivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Task t : tarefas) {
                String linha = t.getNome() + ";"
                        + t.getDescricao() + ";"
                        + t.getDataTermino().format(FORMATTER) + ";"  // Formata a data corretamente
                        + t.getNivelPrioridade() + ";"
                        + t.getCategoria() + ";"
                        + t.getStatus() + ";"
                        + t.isAlarmeAtivado() + ";"
                        + t.getAntecedenciaHoras();
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

                if (partes.length == 8) {
                    String nome = partes[0];
                    String descricao = partes[1];
                    LocalDateTime dataTermino = LocalDateTime.parse(partes[2], FORMATTER);
                    int prioridade = Integer.parseInt(partes[3]);
                    String categoria = partes[4];
                    Status status = Status.valueOf(partes[5]);
                    boolean alarmeAtivado = Boolean.parseBoolean(partes[6]);
                    int antecedenciaHoras = Integer.parseInt(partes[7]);

                    Task tarefa = new Task(nome, descricao, dataTermino,
                            prioridade, categoria, status, alarmeAtivado, antecedenciaHoras);
                    tarefas.add(tarefa);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar tarefas: " + e.getMessage());
        }

        return tarefas;
    }
}

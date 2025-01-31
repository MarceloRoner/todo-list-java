package service;

import model.Status;
import model.Task;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskService {

    private List<Task> listaTarefas;

    public TaskService() {
        this.listaTarefas = new ArrayList<>();
    }

    public List<Task> getListaTarefas() {
        return listaTarefas;
    }

    public void setListaTarefas(List<Task> listaTarefas) {
        this.listaTarefas = listaTarefas;
    }

    public void adicionarTarefa(String nome, String descricao, String data, int prioridade, String categoria, int statusInt) {
        LocalDate dataTermino = LocalDate.parse(data);
        Status status = converteIntParaStatus(statusInt);
        Task nova = new Task(nome, descricao, dataTermino, prioridade, categoria, status);
        listaTarefas.add(nova);
        reordenarPorPrioridade();
    }

    public List<Task> listarTodas() {
        return listaTarefas;
    }

    public List<Task> listarPorCategoria(String categoria) {
        List<Task> filtradas = new ArrayList<>();
        for (Task t : listaTarefas) {
            if (t.getCategoria().equalsIgnoreCase(categoria)) {
                filtradas.add(t);
            }
        }
        return filtradas;
    }

    public List<Task> listarPorPrioridade(int prioridade) {
        List<Task> filtradas = new ArrayList<>();
        for (Task t : listaTarefas) {
            if (t.getNivelPrioridade() == prioridade) {
                filtradas.add(t);
            }
        }
        return filtradas;
    }

    public List<Task> listarPorStatus(Status status) {
        List<Task> filtradas = new ArrayList<>();
        for (Task t : listaTarefas) {
            if (t.getStatus() == status) {
                filtradas.add(t);
            }
        }
        return filtradas;
    }

    public boolean deletarTarefa(int indice) {
        if (indice >= 0 && indice < listaTarefas.size()) {
            listaTarefas.remove(indice);
            return true;
        }
        return false;
    }

    public Task buscarTarefaPorIndice(int indice) {
        if (indice >= 0 && indice < listaTarefas.size()) {
            return listaTarefas.get(indice);
        }
        return null;
    }

    public void atualizarTarefa(int indice, int opcao, String novoValor) {
        Task tarefa = buscarTarefaPorIndice(indice);
        if (tarefa == null) {
            System.out.println("Índice inválido!");
            return;
        }
        switch (opcao) {
            case 1: tarefa.setNome(novoValor); break;
            case 2: tarefa.setDescricao(novoValor); break;
            case 3: tarefa.setDataTermino(LocalDate.parse(novoValor)); break;
            case 4: tarefa.setNivelPrioridade(Integer.parseInt(novoValor)); break;
            case 5: tarefa.setCategoria(novoValor); break;
            case 6: tarefa.setStatus(converteIntParaStatus(Integer.parseInt(novoValor))); break;
            default:
                System.out.println("Opção inválida, não atualizou nada.");
                return;
        }

        reordenarPorPrioridade();
        System.out.println("Tarefa atualizada com sucesso!");
    }

    private void reordenarPorPrioridade() {
        Collections.sort(listaTarefas, Comparator.comparing(Task::getNivelPrioridade));
    }

    private Status converteIntParaStatus(int valor) {
        switch (valor) {
            case 1: return Status.TODO;
            case 2: return Status.DOING;
            case 3: return Status.DONE;
            default: return Status.TODO;
        }
    }
}

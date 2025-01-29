package service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Status;
import model.Task;

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

    public void adicionarTarefa(Task task) {
        // adiciona e ordena pela prioridade
        listaTarefas.add(task);
        Collections.sort(listaTarefas, Comparator.comparing(Task::getNivelPrioridade));
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
    public void reordenarPorPrioridade() {
        Collections.sort(listaTarefas, Comparator.comparing(Task::getNivelPrioridade));
    }


}

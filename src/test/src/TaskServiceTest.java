import model.Status;
import model.Task;
import service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void testAdicionarTarefa() {
        // Given
        String nome = "Estudar Java";
        String descricao = "Revisar conceitos de Streams";
        LocalDateTime dataTermino = LocalDateTime.now().plusDays(1);
        int prioridade = 1;
        String categoria = "Estudo";
        int status = 1;
        boolean alarmeAtivado = true;
        int antecedenciaHoras = 2;

        // When
        taskService.adicionarTarefa(nome, descricao, dataTermino, prioridade, categoria, status, alarmeAtivado, antecedenciaHoras);

        // Then
        List<Task> tarefas = taskService.getListaTarefas();
        assertEquals(1, tarefas.size());
        assertEquals(nome, tarefas.get(0).getNome());
        assertEquals(descricao, tarefas.get(0).getDescricao());
        assertEquals(prioridade, tarefas.get(0).getNivelPrioridade());
        assertEquals(categoria, tarefas.get(0).getCategoria());
        assertEquals(Status.TODO, tarefas.get(0).getStatus());
        assertTrue(tarefas.get(0).isAlarmeAtivado());

        System.out.println("✅ Teste de criação de tarefa passou com sucesso!");
    }

    @Test
    void testAtualizarTarefa() {
        // Given
        taskService.adicionarTarefa("Estudar Java", "Descrição", LocalDateTime.now().plusDays(1), 1, "Estudo", 1, true, 2);
        int indice = 0;
        String novoNome = "Estudar Java Avançado";

        // When
        taskService.atualizarTarefa(indice, 1, novoNome);

        // Then
        assertEquals(novoNome, taskService.getListaTarefas().get(indice).getNome());
        System.out.println("✅ Teste de atualização de tarefa passou com sucesso!");
    }

    @Test
    void testDeletarTarefa() {
        // Given
        taskService.adicionarTarefa("Estudar Java", "Descrição", LocalDateTime.now().plusDays(1), 1, "Estudo", 1, true, 2);

        // When
        boolean result = taskService.deletarTarefa(0);

        // Then
        assertTrue(result);
        assertEquals(0, taskService.getListaTarefas().size());
        System.out.println("✅ Teste de deleção de tarefa passou com sucesso!");
    }

    @Test
    void testBuscarTarefaPorIndice() {
        // Given
        taskService.adicionarTarefa("Estudar Java", "Descrição", LocalDateTime.now().plusDays(1), 1, "Estudo", 1, true, 2);

        // When
        Task tarefa = taskService.buscarTarefaPorIndice(0);

        // Then
        assertNotNull(tarefa);
        assertEquals("Estudar Java", tarefa.getNome());
        System.out.println("✅ Teste de busca de tarefa passou com sucesso!");
    }

    @Test
    void testListarPorCategoria() {
        // Given
        taskService.adicionarTarefa("Estudar Java", "Descrição", LocalDateTime.now().plusDays(1), 1, "Estudo", 1, true, 2);

        // When
        List<Task> tarefas = taskService.listarPorCategoria("Estudo");

        // Then
        assertEquals(1, tarefas.size());
        System.out.println("✅ Teste de listagem por categoria passou com sucesso!");
    }
    @Test
    void testAtualizarStatusDaTarefa() {
        // Given
        taskService.adicionarTarefa("Estudar Java", "Descrição", LocalDateTime.now().plusDays(1), 1, "Estudo", 1, true, 2);

        // When
        taskService.atualizarTarefa(0, 6, "3");

        // Then
        assertEquals(Status.DONE, taskService.getListaTarefas().get(0).getStatus());
        System.out.println("✅ Teste de atualização de status passou com sucesso!");
    }
    @Test
    void testVerificarAlarmes() {
        // Given
        LocalDateTime dataTermino = LocalDateTime.now().plusHours(1);
        taskService.adicionarTarefa("Tarefa com Alarme", "Descrição", dataTermino, 1, "Estudo", 1, true, 2);

        // When
        taskService.verificarAlarmes();

        // Then
        assertTrue(taskService.getListaTarefas().get(0).shouldTriggerAlarm(LocalDateTime.now()));
        System.out.println("✅ Teste de verificação de alarme passou com sucesso!");
    }

    @Test
    void testListarPorPrioridade() {
        // Given
        taskService.adicionarTarefa("Tarefa Prioridade Alta", "Descrição", LocalDateTime.now().plusDays(1), 5, "Estudo", 1, true, 2);

        // When
        List<Task> tarefas = taskService.listarPorPrioridade(5);

        // Then
        assertEquals(1, tarefas.size());
        System.out.println("✅ Teste de listagem por prioridade passou com sucesso!");
    }
    @Test
    void testListarPorStatus() {
        // Given
        taskService.adicionarTarefa("Tarefa Status DONE", "Descrição", LocalDateTime.now().plusDays(1), 1, "Estudo", 3, true, 2);

        // When
        List<Task> tarefas = taskService.listarPorStatus(Status.DONE);

        // Then
        assertEquals(1, tarefas.size());
        System.out.println("✅ Teste de listagem por status passou com sucesso!");
    }

}


import model.Status;
import model.Task;
import service.TaskService;
import util.FileUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String NOME_ARQUIVO = "tarefas.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskService service = new TaskService();
        service.setListaTarefas(FileUtil.carregarTarefas(NOME_ARQUIVO));
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- TODO LIST MENU ---");
            System.out.println("1 - Adicionar Tarefa");
            System.out.println("2 - Listar Tarefas (todas)");
            System.out.println("3 - Listar Tarefas por Categoria");
            System.out.println("4 - Listar Tarefas por Prioridade");
            System.out.println("5 - Listar Tarefas por Status");
            System.out.println("6 - Deletar Tarefa");
            System.out.println("7 - Atualizar Tarefa");
            System.out.println("8 - Verificar Alarmes");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();
                    System.out.print("Data de término (yyyy-MM-dd'T'HH:mm): ");
                    String data = sc.nextLine();
                    LocalDateTime dataTermino = LocalDateTime.parse(data, FORMATTER);
                    System.out.print("Nível de prioridade (1-5): ");
                    int prioridade = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Categoria: ");
                    String categoria = sc.nextLine();
                    System.out.println("Status (1=TODO, 2=DOING, 3=DONE): ");
                    int status = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Deseja ativar um alarme para essa tarefa? (true/false): ");
                    boolean alarmeAtivado = sc.nextBoolean();
                    int antecedenciaHoras = 0;
                    if (alarmeAtivado) {
                        System.out.print("Com quantas horas de antecedência o alarme deve ser disparado? ");
                        antecedenciaHoras = sc.nextInt();
                        sc.nextLine();
                    }

                    service.adicionarTarefa(nome, descricao, dataTermino, prioridade, categoria, status, alarmeAtivado, antecedenciaHoras);
                    System.out.println("Tarefa adicionada com sucesso!");
                    break;

                case 2:
                    imprimirTarefas(service.listarTodas());
                    break;

                case 3:
                    System.out.print("Digite a categoria: ");
                    imprimirTarefas(service.listarPorCategoria(sc.nextLine()));
                    break;

                case 4:
                    System.out.print("Digite a prioridade (1-5): ");
                    imprimirTarefas(service.listarPorPrioridade(sc.nextInt()));
                    sc.nextLine();
                    break;

                case 5:
                    System.out.print("Status (1=TODO, 2=DOING, 3=DONE): ");
                    imprimirTarefas(service.listarPorStatus(Status.values()[sc.nextInt() - 1]));
                    sc.nextLine();
                    break;

                case 6:
                    System.out.print("Informe o índice da tarefa para deletar: ");
                    System.out.println(service.deletarTarefa(sc.nextInt()) ? "Tarefa removida!" : "Índice inválido!");
                    sc.nextLine();
                    break;

                case 7:
                    System.out.print("Informe o índice da tarefa para atualizar: ");
                    int indice = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Informe a opção (1-nome, 2-descrição, 3-data de término, 4-prioridade, 5-categoria, 6-status): ");
                    int opcaoAtualizacao = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Digite o novo valor: ");
                    String novoValor = sc.nextLine();

                    service.atualizarTarefa(indice, opcaoAtualizacao, novoValor);
                    break;

                case 8:
                    service.verificarAlarmes();
                    break;

                case 0:
                    FileUtil.salvarTarefas(service.listarTodas(), NOME_ARQUIVO);
                    System.out.println("Saindo e salvando tarefas...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        sc.close();
    }

    private static void imprimirTarefas(List<Task> tarefas) {
        tarefas.forEach(System.out::println);
    }
}

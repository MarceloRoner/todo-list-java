
import model.Status;
import model.Task;
import service.TaskService;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import util.FileUtil;

public class Main {
    private static final String NOME_ARQUIVO = "tarefas.csv";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskService service = new TaskService();
        List<Task> tarefasNoArquivo = FileUtil.carregarTarefas(NOME_ARQUIVO);
        service.setListaTarefas(tarefasNoArquivo);
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
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine(); // limpa o buffer

            switch (opcao) {
                case 1:
                    adicionarTarefa(sc, service);
                    break;
                case 2:
                    listarTodas(service);
                    break;
                case 3:
                    listarPorCategoria(sc, service);
                    break;
                case 4:
                    listarPorPrioridade(sc, service);
                    break;
                case 5:
                    listarPorStatus(sc, service);
                    break;
                case 6:
                    deletarTarefa(sc, service);
                    break;
                case 7:
                    atualizarTarefa(sc, service); // chamaremos esse método
                    break;
                case 0:
                    System.out.println("Saindo...");

                    FileUtil.salvarTarefas(service.listarTodas(), NOME_ARQUIVO);
                    System.out.println("Saindo e salvando tarefas no arquivo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }


    private static void adicionarTarefa(Scanner sc, TaskService service) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Data de término (yyyy-mm-dd): ");
        String data = sc.nextLine();
        System.out.print("Nível de prioridade (1-5): ");
        int prioridade = sc.nextInt();
        sc.nextLine();
        System.out.print("Categoria: ");
        String categoria = sc.nextLine();

        System.out.println("Status (1=TODO, 2=DOING, 3=DONE): ");
        int statusInt = sc.nextInt();
        sc.nextLine();
        Status status = converteIntParaStatus(statusInt);

        LocalDate dataTermino = LocalDate.parse(data);

        Task nova = new Task(nome, descricao, dataTermino, prioridade, categoria, status);
        service.adicionarTarefa(nova);

        System.out.println("Tarefa adicionada com sucesso!");
    }

    private static void listarTodas(TaskService service) {
        List<Task> tarefas = service.listarTodas();
        imprimirTarefas(tarefas);
    }

    private static void listarPorCategoria(Scanner sc, TaskService service) {
        System.out.print("Digite a categoria: ");
        String cat = sc.nextLine();
        List<Task> tarefas = service.listarPorCategoria(cat);
        imprimirTarefas(tarefas);
    }

    private static void listarPorPrioridade(Scanner sc, TaskService service) {
        System.out.print("Digite a prioridade (1-5): ");
        int prio = sc.nextInt();
        sc.nextLine();
        List<Task> tarefas = service.listarPorPrioridade(prio);
        imprimirTarefas(tarefas);
    }

    private static void listarPorStatus(Scanner sc, TaskService service) {
        System.out.print("Status (1=TODO, 2=DOING, 3=DONE): ");
        int st = sc.nextInt();
        sc.nextLine();
        Status status = converteIntParaStatus(st);
        List<Task> tarefas = service.listarPorStatus(status);
        imprimirTarefas(tarefas);
    }

    private static void deletarTarefa(Scanner sc, TaskService service) {
        System.out.print("Informe o índice da tarefa para deletar: ");
        int indice = sc.nextInt();
        sc.nextLine();
        boolean removida = service.deletarTarefa(indice);
        if (removida) {
            System.out.println("Tarefa removida!");
        } else {
            System.out.println("Índice inválido!");
        }
    }

    private static void imprimirTarefas(List<Task> tarefas) {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
            return;
        }
        int i = 0;
        for (Task t : tarefas) {
            System.out.println("[" + i + "] "
                    + t.getNome() + " | "
                    + t.getDescricao() + " | "
                    + t.getDataTermino() + " | Prioridade "
                    + t.getNivelPrioridade() + " | "
                    + t.getCategoria() + " | "
                    + t.getStatus());
            i++;
        }
    }

    private static Status converteIntParaStatus(int valor) {
        switch (valor) {
            case 1: return Status.TODO;
            case 2: return Status.DOING;
            case 3: return Status.DONE;
            default: return Status.TODO;
        }
    }

    private static void atualizarTarefa(Scanner sc, TaskService service) {
        System.out.print("Informe o índice da tarefa que deseja atualizar: ");
        int indice = sc.nextInt();
        sc.nextLine(); // limpa buffer

        Task tarefa = service.buscarTarefaPorIndice(indice);
        if (tarefa == null) {
            System.out.println("Índice inválido!");
            return;
        }

        // Exibir infos atuais e perguntar o que quer atualizar
        System.out.println("Tarefa atual: ");
        System.out.println("Nome: " + tarefa.getNome());
        System.out.println("Descrição: " + tarefa.getDescricao());
        System.out.println("Data de término: " + tarefa.getDataTermino());
        System.out.println("Prioridade: " + tarefa.getNivelPrioridade());
        System.out.println("Categoria: " + tarefa.getCategoria());
        System.out.println("Status: " + tarefa.getStatus());

        System.out.println("\nQual campo deseja alterar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Descrição");
        System.out.println("3 - Data de término");
        System.out.println("4 - Prioridade (1-5)");
        System.out.println("5 - Categoria");
        System.out.println("6 - Status (1=TODO,2=DOING,3=DONE)");
        System.out.print("Escolha: ");
        int opc = sc.nextInt();
        sc.nextLine();

        switch (opc) {
            case 1:
                System.out.print("Novo nome: ");
                String nome = sc.nextLine();
                tarefa.setNome(nome);
                break;
            case 2:
                System.out.print("Nova descrição: ");
                String desc = sc.nextLine();
                tarefa.setDescricao(desc);
                break;
            case 3:
                System.out.print("Nova data de término (yyyy-mm-dd): ");
                String data = sc.nextLine();
                tarefa.setDataTermino(LocalDate.parse(data));
                break;
            case 4:
                System.out.print("Nova prioridade (1-5): ");
                int prio = sc.nextInt();
                sc.nextLine();
                tarefa.setNivelPrioridade(prio);
                break;
            case 5:
                System.out.print("Nova categoria: ");
                String cat = sc.nextLine();
                tarefa.setCategoria(cat);
                break;
            case 6:
                System.out.print("Novo status (1=TODO, 2=DOING, 3=DONE): ");
                int statusInt = sc.nextInt();
                sc.nextLine();
                tarefa.setStatus(converteIntParaStatus(statusInt));
                break;
            default:
                System.out.println("Opção inválida, não atualizou nada.");
                break;
        }

        service.reordenarPorPrioridade();

        System.out.println("Tarefa atualizada com sucesso!");
    }
}
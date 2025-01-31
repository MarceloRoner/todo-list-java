# TODO List em Java

Este repositório contém um projeto de **TODO List** desenvolvido em Java, que implementa 
operações de CRUD (Create, Read, Update, Delete) com persistência em arquivo CSV.

## Funcionalidades

- **Adicionar Tarefa**: nome, descrição, data de término, prioridade (1-5), categoria, status (TODO, DOING, DONE)
- **Listar Tarefas**: todas, por categoria, por prioridade, por status
- **Atualizar Tarefa**: alterar campos de uma tarefa existente
- **Deletar Tarefa**: remover da lista
- **Persistência**: salva e carrega tarefas de um arquivo TXT (tarefas.txt)

## Como executar

1. **Clonar** este repositório:
   git clone https://github.com/SEU_USUARIO/todo-list-java.git
Abrir no IntelliJ (ou outra IDE) ou compilar via terminal (pasta raiz do projeto):
cd todo-list-java
javac src/**/*.java
java -cp src Main
Observação: o comando exato depende de como você estruturou o package.
Caso use IntelliJ, é só abrir o projeto e rodar Main.

Tecnologias e Ferramentas
Java 
IntelliJ IDEA (ou Eclipse, etc.)
Git e GitHub para versionamento
Sem uso de frameworks (Spring, etc.), somente Java puro
Autor
Marcelo Roner

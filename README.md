# ✅ TODO List em Java + Frontend  

Este repositório contém um projeto de **TODO List** desenvolvido em **Java** para o backend e **HTML, CSS, e JavaScript** para o frontend. O sistema implementa operações de **CRUD (Create, Read, Update, Delete)** com persistência em **arquivo TXT**.

---

## 🚀 Funcionalidades  

### **📌 Backend (Java)**
- ✅ **Adicionar Tarefa:** Nome, descrição, data de término, prioridade (1-5), categoria, status (`TODO`, `DOING`, `DONE`).
- ✅ **Listar Tarefas:** Exibir todas, filtrar por **categoria, prioridade ou status**.
- ✅ **Atualizar Tarefa:** Alterar qualquer campo de uma tarefa existente.
- ✅ **Deletar Tarefa:** Remover uma tarefa da lista.
- ✅ **Persistência:** Salva e carrega tarefas de um arquivo **TXT (`tarefas.txt`)**.

### **📌 Frontend (HTML, CSS, JS)**
- 🎨 **Interface amigável para manipulação de tarefas.**
- 📝 **Formulário para adicionar novas tarefas.**
- 📋 **Tabela dinâmica para listar as tarefas cadastradas.**
- ✏️ **Botões para editar e excluir tarefas diretamente na interface.**
- 🔗 **Simulação do backend via JavaScript** *(enquanto a API não está integrada)*.

---

## 🔧 Como Executar

### **1️⃣ Rodando o Backend (Java)**
1. **Clone o repositório:**
git clone https://github.com/SEU_USUARIO/todo-list-java.git

Abra no IntelliJ IDEA (ou Eclipse) OU compile via terminal:
cd todo-list-java
javac src/**/*.java
java -cp src Main
📌 OBS: O comando pode variar dependendo da estrutura de pacotes.
📌 Se estiver usando uma IDE, basta rodar a classe Main.java.

2️⃣ Rodando o Frontend (HTML, CSS, JavaScript)
Acesse a pasta do frontend:

cd Frontend
Abra o arquivo index.html no navegador.
🛠️ Tecnologias e Ferramentas
🔹 Java (Backend)
🔹 HTML, CSS, JavaScript (Frontend)
🔹 IntelliJ IDEA (ou Eclipse, etc.)
🔹 Git & GitHub para versionamento
🔹 Sem frameworks (Java puro no backend, sem Spring, etc.)

👤 Autor
Marcelo Roner

📌 Melhorias Futuras
 Conectar frontend com backend via API REST.
 Adicionar suporte a banco de dados (PostgreSQL).
 Melhorar design do frontend.
 Criar autenticação de usuários.

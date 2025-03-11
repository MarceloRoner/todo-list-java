let tasks = [];

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("task-form");
  const filterBtn = document.getElementById("filterBtn");

  form.addEventListener("submit", (e) => {
    e.preventDefault();
    saveTask();
  });

  filterBtn.addEventListener("click", () => {
    const status = document.getElementById("filterStatus").value;
    renderTaskTable(status);
  });

  tasks = [
    {
      nome: "Estudar HTML/CSS",
      descricao: "Revisar tags básicas",
      dataTermino: "2025-02-20",
      nivelPrioridade: 2,
      categoria: "Estudos",
      status: "TODO"
    },
    {
      nome: "Implementar Frontend",
      descricao: "Criar layout em JS",
      dataTermino: "2025-02-22",
      nivelPrioridade: 1,
      categoria: "Projeto",
      status: "DOING"
    }
  ];

  renderTaskTable();
});

function saveTask() {
  const nameInput = document.getElementById("taskName");
  const descInput = document.getElementById("taskDesc");
  const dateInput = document.getElementById("taskDate");
  const priorityInput = document.getElementById("taskPriority");
  const categoryInput = document.getElementById("taskCategory");
  const statusSelect = document.getElementById("taskStatus");
  const editingIndex = document.getElementById("editingTaskIndex").value;

  const newTask = {
    nome: nameInput.value,
    descricao: descInput.value,
    dataTermino: dateInput.value,
    nivelPrioridade: parseInt(priorityInput.value),
    categoria: categoryInput.value,
    status: statusSelect.value
  };

  if (editingIndex === "") {
    tasks.push(newTask);
  } else {
    tasks[editingIndex] = newTask;
  }

  formReset();
  renderTaskTable();
}

function renderTaskTable(filterStatus = "") {
  const tbody = document.querySelector("#task-table tbody");
  tbody.innerHTML = "";
  const filtered = filterStatus
    ? tasks.filter((t) => t.status === filterStatus)
    : tasks;
  filtered.forEach((task, index) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${task.nome}</td>
      <td>${task.descricao}</td>
      <td>${task.dataTermino}</td>
      <td>${task.nivelPrioridade}</td>
      <td>${task.categoria}</td>
      <td>${task.status}</td>
      <td>
        <button onclick="editTask(${index})">Editar</button>
        <button onclick="deleteTask(${index})">Excluir</button>
      </td>
    `;
    tbody.appendChild(row);
  });
}

function editTask(index) {
  const task = tasks[index];
  document.getElementById("taskName").value = task.nome;
  document.getElementById("taskDesc").value = task.descricao;
  document.getElementById("taskDate").value = task.dataTermino;
  document.getElementById("taskPriority").value = task.nivelPrioridade;
  document.getElementById("taskCategory").value = task.categoria;
  document.getElementById("taskStatus").value = task.status;
  document.getElementById("editingTaskIndex").value = index;
}

function deleteTask(index) {
  if (confirm("Tem certeza que deseja excluir esta tarefa?")) {
    tasks.splice(index, 1);
    renderTaskTable();
  }
}

function formReset() {
  document.getElementById("task-form").reset(); //redefine os valores do formulário para os valores originais
  document.getElementById("editingTaskIndex").value = "";
}

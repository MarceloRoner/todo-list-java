let tasks = [];

function loadTasks() {
  const storedTasks = localStorage.getItem("tasks");
  tasks = storedTasks ? JSON.parse(storedTasks) : [];
}

function saveTasksToLocalStorage() {
  localStorage.setItem("tasks", JSON.stringify(tasks));
}

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("task-form");
  const filterBtn = document.getElementById("filterBtn");
  const updateMultipleStatusBtn = document.getElementById("updateMultipleStatusBtn");

  form.addEventListener("submit", (e) => {
    e.preventDefault();
    saveTask();
  });

  filterBtn.addEventListener("click", () => {
    const status = document.getElementById("filterStatus").value;
    renderTaskTable(status);
  });

  updateMultipleStatusBtn.addEventListener("click", updateMultipleStatus);

  loadTasks();
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
    status: statusSelect.value,
  };

  if (editingIndex === "") {
    tasks.push(newTask);
  } else {
    tasks[editingIndex] = newTask;
  }

  saveTasksToLocalStorage();
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
      <td><input type="checkbox" class="task-checkbox" data-index="${index}"></td>
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

function updateMultipleStatus() {
  const selectedStatus = prompt("Digite o novo status para as tarefas selecionadas (TODO, DOING, DONE):");

  if (!selectedStatus || !["TODO", "DOING", "DONE"].includes(selectedStatus.toUpperCase())) {
    alert("Status inválido. Use: TODO, DOING ou DONE.");
    return;
  }

  const checkboxes = document.querySelectorAll(".task-checkbox:checked");

  checkboxes.forEach((checkbox) => {
    const index = checkbox.getAttribute("data-index");
    tasks[index].status = selectedStatus.toUpperCase();
  });

  saveTasksToLocalStorage();
  renderTaskTable();
  alert("Status atualizado para as tarefas selecionadas!");
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
    saveTasksToLocalStorage();
    renderTaskTable();
  }
}

function formReset() {
  document.getElementById("task-form").reset();
  document.getElementById("editingTaskIndex").value = "";
}
package model;

import java.time.LocalDate;

public class Task {

        private String nome;
        private String descricao;
        private LocalDate dataTermino;
        private int nivelPrioridade; 
        private String categoria;
        private Status status; 

        public Task(String nome, String descricao, LocalDate dataTermino,
                    int nivelPrioridade, String categoria, Status status) {
            this.nome = nome;
            this.descricao = descricao;
            this.dataTermino = dataTermino;
            this.nivelPrioridade = nivelPrioridade;
            this.categoria = categoria;
            this.status = status;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public LocalDate getDataTermino() {
            return dataTermino;
        }

        public void setDataTermino(LocalDate dataTermino) {
            this.dataTermino = dataTermino;
        }

        public int getNivelPrioridade() {
            return nivelPrioridade;
        }

        public void setNivelPrioridade(int nivelPrioridade) {
            this.nivelPrioridade = nivelPrioridade;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

    @Override
    public String toString() {
        return "Tarefa: " + nome + " | " +
                "Descrição: " + descricao + " | " +
                "Data de término: " + dataTermino + " | " +
                "Prioridade: " + nivelPrioridade+ " | " +
                "Categoria: " + categoria + " | " +
                "Status: " + status;
    }


}




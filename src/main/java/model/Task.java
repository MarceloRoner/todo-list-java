package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

        private String nome;
        private String descricao;
        private LocalDateTime dataTermino;
        private int nivelPrioridade; 
        private String categoria;
        private Status status;
        private boolean alarmeAtivado;
        private int antecedenciaHoras;

        public Task(String nome, String descricao, LocalDateTime dataTermino,
                    int nivelPrioridade, String categoria, Status status, boolean alarmeAtivado, int antecedenciaHoras) {
            this.nome = nome;
            this.descricao = descricao;
            this.dataTermino = dataTermino;
            this.nivelPrioridade = nivelPrioridade;
            this.categoria = categoria;
            this.status = status;
            this.alarmeAtivado = alarmeAtivado;
            this.antecedenciaHoras = antecedenciaHoras;
        }

    public boolean shouldTriggerAlarm(LocalDateTime currentTime) {
        return alarmeAtivado && currentTime.isAfter(dataTermino.minusHours(antecedenciaHoras));
    }

    public boolean isAlarmeAtivado() { return alarmeAtivado; }
    public int getAntecedenciaHoras() { return antecedenciaHoras; }


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

        public LocalDateTime getDataTermino() {
            return dataTermino;
        }

        public void setDataTermino(LocalDateTime dataTermino) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return "Tarefa: " + nome + " | " +
                "Descrição: " + descricao + " | " +
                "Data de término: " + dataTermino.format(formatter) + " | " +
                "Prioridade: " + nivelPrioridade + " | " +
                "Categoria: " + categoria + " | " +
                "Status: " + status +
                (alarmeAtivado ? " | Alarme: Ativado para " + antecedenciaHoras + " hora(s) antes" : "");
    }


}




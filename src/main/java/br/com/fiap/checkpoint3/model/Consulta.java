package br.com.fiap.checkpoint3.model;

import java.time.LocalDateTime;

public class Consulta {
    private Long id;
    private Long pacienteId;
    private Long profissionalId;
    private String descricao;
    private StatusConsulta status;
    private LocalDateTime dataHora;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Consulta() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Consulta(Long id, Long pacienteId, Long profissionalId, String descricao,
                    StatusConsulta status, LocalDateTime dataHora,
                    LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.profissionalId = profissionalId;
        this.descricao = descricao;
        this.status = status;
        this.dataHora = dataHora;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }

    public Long getProfissionalId() { return profissionalId; }
    public void setProfissionalId(Long profissionalId) { this.profissionalId = profissionalId; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public StatusConsulta getStatus() { return status; }
    public void setStatus(StatusConsulta status) { this.status = status; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
package br.com.fiap.checkpoint3.dto.consulta;

import java.time.LocalDateTime;

import br.com.fiap.checkpoint3.model.Consulta;
import br.com.fiap.checkpoint3.model.StatusConsulta;

public class ConsultaResponse {
    private Long id;
    private Long pacienteId;
    private Long profissionalId;
    private String descricao;
    private StatusConsulta status;
    private LocalDateTime dataHora;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ConsultaResponse() {}

    public ConsultaResponse(Long id, Long pacienteId, Long profissionalId, String descricao, StatusConsulta status,
                             LocalDateTime dataHora, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.profissionalId = profissionalId;
        this.descricao = descricao;
        this.status = status;
        this.dataHora = dataHora;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ConsultaResponse from(Consulta c) {
        return new ConsultaResponse(
            c.getId(),
            c.getPacienteId(),
            c.getProfissionalId(),
            c.getDescricao(),
            c.getStatus(),
            c.getDataHora(),
            c.getCreatedAt(),
            c.getUpdatedAt()
        );
    }

    public Long getId() { return id; }
    public Long getPacienteId() { return pacienteId; }
    public Long getProfissionalId() { return profissionalId; }
    public String getDescricao() { return descricao; }
    public StatusConsulta getStatus() { return status; }
    public LocalDateTime getDataHora() { return dataHora; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}

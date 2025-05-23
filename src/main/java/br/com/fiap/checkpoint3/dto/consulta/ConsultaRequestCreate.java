package br.com.fiap.checkpoint3.dto.consulta;

import java.time.LocalDateTime;
import br.com.fiap.checkpoint3.model.StatusConsulta;

public class ConsultaRequestCreate {
    private Long pacienteId;
    private Long profissionalId;
    private String descricao;
    private StatusConsulta status;
    private LocalDateTime dataHora;

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
}

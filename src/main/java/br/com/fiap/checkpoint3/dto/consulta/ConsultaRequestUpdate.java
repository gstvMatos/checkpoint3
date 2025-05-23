package br.com.fiap.checkpoint3.dto.consulta;

import java.time.LocalDateTime;
import br.com.fiap.checkpoint3.model.StatusConsulta;

public class ConsultaRequestUpdate {
    private String descricao;
    private StatusConsulta status;
    private LocalDateTime dataHora;

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public StatusConsulta getStatus() { return status; }
    public void setStatus(StatusConsulta status) { this.status = status; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
}
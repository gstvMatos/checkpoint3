package br.com.fiap.checkpoint3.dto.profissional;

import java.time.LocalDateTime;
import br.com.fiap.checkpoint3.model.Profissional;

public class ProfissionalResponse {
    private Long id;
    private String nome;
    private String especialidade;
    private String email;
    private String telefone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProfissionalResponse() {
    }

    public ProfissionalResponse(Long id, String nome, String especialidade, String email, String telefone,
                                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.email = email;
        this.telefone = telefone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ProfissionalResponse from(Profissional p) {
        return new ProfissionalResponse(
            p.getId(),
            p.getNome(),
            p.getEspecialidade(),
            p.getEmail(),
            p.getTelefone(),
            p.getCreatedAt(),
            p.getUpdatedAt()
        );
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEspecialidade() { return especialidade; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
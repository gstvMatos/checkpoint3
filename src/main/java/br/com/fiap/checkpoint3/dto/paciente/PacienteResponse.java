package br.com.fiap.checkpoint3.dto.paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.fiap.checkpoint3.model.Paciente;

public class PacienteResponse {
    private Long id;
    private String nome;
    private String endereco;
    private String bairro;
    private String email;
    private String telefoneCompleto;
    private LocalDate dataNascimento;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PacienteResponse() {
    }

    public PacienteResponse(Long id, String nome, String endereco, String bairro, String email,
                             String telefoneCompleto, LocalDate dataNascimento,
                             LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.bairro = bairro;
        this.email = email;
        this.telefoneCompleto = telefoneCompleto;
        this.dataNascimento = dataNascimento;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PacienteResponse from(Paciente paciente) {
        return new PacienteResponse(
            paciente.getId(),
            paciente.getNome(),
            paciente.getEndereco(),
            paciente.getBairro(),
            paciente.getEmail(),
            paciente.getTelefoneCompleto(),
            paciente.getDataNascimento(),
            paciente.getCreatedAt(),
            paciente.getUpdatedAt()
        );
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getBairro() { return bairro; }
    public String getEmail() { return email; }
    public String getTelefoneCompleto() { return telefoneCompleto; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
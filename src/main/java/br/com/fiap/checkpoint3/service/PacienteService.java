package br.com.fiap.checkpoint3.service;

import br.com.fiap.checkpoint3.dto.paciente.PacienteRequestCreate;
import br.com.fiap.checkpoint3.dto.paciente.PacienteRequestUpdate;
import br.com.fiap.checkpoint3.dto.paciente.PacienteResponse;
import br.com.fiap.checkpoint3.model.Paciente;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final Map<Long, Paciente> pacientes = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public PacienteResponse create(PacienteRequestCreate dto) {
        Long id = counter.getAndIncrement();
        Paciente paciente = new Paciente();
        paciente.setId(id);
        paciente.setNome(dto.getNome());
        paciente.setEndereco(dto.getEndereco());
        paciente.setBairro(dto.getBairro());
        paciente.setEmail(dto.getEmail());
        paciente.setTelefoneCompleto(dto.getTelefoneCompleto());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setCreatedAt(LocalDateTime.now());
        paciente.setUpdatedAt(LocalDateTime.now());
        pacientes.put(id, paciente);
        return PacienteResponse.from(paciente);
    }

    public List<PacienteResponse> findAll(String sort) {
        Comparator<Paciente> comparator = Comparator.comparing(Paciente::getNome);
        if ("desc".equalsIgnoreCase(sort)) {
            comparator = comparator.reversed();
        }
        return pacientes.values().stream()
                .sorted(comparator)
                .map(PacienteResponse::from)
                .collect(Collectors.toList());
    }

    public PacienteResponse findById(Long id) {
        Paciente paciente = pacientes.get(id);
        return paciente != null ? PacienteResponse.from(paciente) : null;
    }

    public PacienteResponse update(Long id, PacienteRequestUpdate dto) {
        Paciente paciente = pacientes.get(id);
        if (paciente == null) return null;
        paciente.setNome(dto.getNome());
        paciente.setEndereco(dto.getEndereco());
        paciente.setBairro(dto.getBairro());
        paciente.setEmail(dto.getEmail());
        paciente.setTelefoneCompleto(dto.getTelefoneCompleto());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setUpdatedAt(LocalDateTime.now());
        return PacienteResponse.from(paciente);
    }

    public boolean delete(Long id) {
        return pacientes.remove(id) != null;
    }
}

package br.com.fiap.checkpoint3.service;

import br.com.fiap.checkpoint3.dto.consulta.ConsultaRequestCreate;
import br.com.fiap.checkpoint3.dto.consulta.ConsultaRequestUpdate;
import br.com.fiap.checkpoint3.dto.consulta.ConsultaResponse;
import br.com.fiap.checkpoint3.model.Consulta;
import br.com.fiap.checkpoint3.model.StatusConsulta;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    private final Map<Long, Consulta> consultas = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public ConsultaResponse create(ConsultaRequestCreate dto) {
        Long id = counter.getAndIncrement();
        Consulta c = new Consulta();
        c.setId(id);
        c.setPacienteId(dto.getPacienteId());
        c.setProfissionalId(dto.getProfissionalId());
        c.setDescricao(dto.getDescricao());
        c.setStatus(dto.getStatus());
        c.setDataHora(dto.getDataHora());
        c.setCreatedAt(LocalDateTime.now());
        c.setUpdatedAt(LocalDateTime.now());
        consultas.put(id, c);
        return ConsultaResponse.from(c);
    }

    public List<ConsultaResponse> findAll() {
        return consultas.values().stream()
                .map(ConsultaResponse::from)
                .collect(Collectors.toList());
    }

    public ConsultaResponse findById(Long id) {
        Consulta c = consultas.get(id);
        return c != null ? ConsultaResponse.from(c) : null;
    }

    public ConsultaResponse update(Long id, ConsultaRequestUpdate dto) {
        Consulta c = consultas.get(id);
        if (c == null) return null;
        c.setDescricao(dto.getDescricao());
        c.setStatus(dto.getStatus());
        c.setDataHora(dto.getDataHora());
        c.setUpdatedAt(LocalDateTime.now());
        return ConsultaResponse.from(c);
    }

    public boolean delete(Long id) {
        return consultas.remove(id) != null;
    }

    public List<ConsultaResponse> findByFilters(Long pacienteId, Long profissionalId, StatusConsulta status,
                                                LocalDate dataDe, LocalDate dataAte) {
        return consultas.values().stream()
                .filter(c -> (pacienteId == null || c.getPacienteId().equals(pacienteId)) &&
                             (profissionalId == null || c.getProfissionalId().equals(profissionalId)) &&
                             (status == null || c.getStatus().equals(status)) &&
                             (dataDe == null || !c.getDataHora().toLocalDate().isBefore(dataDe)) &&
                             (dataAte == null || !c.getDataHora().toLocalDate().isAfter(dataAte)))
                .map(ConsultaResponse::from)
                .collect(Collectors.toList());
    }

    public long countByProfissionalId(Long profissionalId) {
        return consultas.values().stream()
                .filter(c -> c.getProfissionalId().equals(profissionalId))
                .count();
    }
}

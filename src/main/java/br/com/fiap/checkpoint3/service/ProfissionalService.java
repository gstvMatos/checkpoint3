package br.com.fiap.checkpoint3.service;

import br.com.fiap.checkpoint3.dto.profissional.ProfissionalRequestCreate;
import br.com.fiap.checkpoint3.dto.profissional.ProfissionalRequestUpdate;
import br.com.fiap.checkpoint3.dto.profissional.ProfissionalResponse;
import br.com.fiap.checkpoint3.model.Profissional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ProfissionalService {

    private final Map<Long, Profissional> profissionais = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public ProfissionalResponse create(ProfissionalRequestCreate dto) {
        Long id = counter.getAndIncrement();
        Profissional p = new Profissional();
        p.setId(id);
        p.setNome(dto.getNome());
        p.setEspecialidade(dto.getEspecialidade());
        p.setEmail(dto.getEmail());
        p.setTelefone(dto.getTelefone());
        p.setCreatedAt(LocalDateTime.now());
        p.setUpdatedAt(LocalDateTime.now());
        profissionais.put(id, p);
        return ProfissionalResponse.from(p);
    }

    public List<ProfissionalResponse> findAll(String sort) {
        Comparator<Profissional> comparator = Comparator.comparing(Profissional::getNome);
        if ("desc".equalsIgnoreCase(sort)) {
            comparator = comparator.reversed();
        }
        return profissionais.values().stream()
                .sorted(comparator)
                .map(ProfissionalResponse::from)
                .collect(Collectors.toList());
    }

    public ProfissionalResponse findById(Long id) {
        Profissional p = profissionais.get(id);
        return p != null ? ProfissionalResponse.from(p) : null;
    }

    public ProfissionalResponse update(Long id, ProfissionalRequestUpdate dto) {
        Profissional p = profissionais.get(id);
        if (p == null) return null;
        p.setNome(dto.getNome());
        p.setEspecialidade(dto.getEspecialidade());
        p.setEmail(dto.getEmail());
        p.setTelefone(dto.getTelefone());
        p.setUpdatedAt(LocalDateTime.now());
        return ProfissionalResponse.from(p);
    }

    public boolean delete(Long id) {
        return profissionais.remove(id) != null;
    }
}

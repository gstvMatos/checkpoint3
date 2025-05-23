package br.com.fiap.checkpoint3.controller;

import br.com.fiap.checkpoint3.dto.consulta.ConsultaRequestCreate;
import br.com.fiap.checkpoint3.dto.consulta.ConsultaRequestUpdate;
import br.com.fiap.checkpoint3.dto.consulta.ConsultaResponse;
import br.com.fiap.checkpoint3.model.StatusConsulta;
import br.com.fiap.checkpoint3.service.ConsultaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ConsultaResponse> create(@RequestBody ConsultaRequestCreate dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ConsultaResponse>> findAll(
        @RequestParam(required = false) Long pacienteId,
        @RequestParam(required = false) Long profissionalId,
        @RequestParam(required = false) StatusConsulta status,
        @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_de,
        @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_ate
    ) {
        if (pacienteId != null || profissionalId != null || status != null || data_de != null || data_ate != null) {
            return ResponseEntity.ok(service.findByFilters(pacienteId, profissionalId, status, data_de, data_ate));
        }
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponse> findById(@PathVariable Long id) {
        ConsultaResponse response = service.findById(id);
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponse> update(@PathVariable Long id, @RequestBody ConsultaRequestUpdate dto) {
        ConsultaResponse response = service.update(id, dto);
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.delete(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}

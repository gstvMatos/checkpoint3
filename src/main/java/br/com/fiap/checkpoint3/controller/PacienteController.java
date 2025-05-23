package br.com.fiap.checkpoint3.controller;

import br.com.fiap.checkpoint3.dto.paciente.PacienteRequestCreate;
import br.com.fiap.checkpoint3.dto.paciente.PacienteRequestUpdate;
import br.com.fiap.checkpoint3.dto.paciente.PacienteResponse;
import br.com.fiap.checkpoint3.dto.consulta.ConsultaResponse;
import br.com.fiap.checkpoint3.model.StatusConsulta;
import br.com.fiap.checkpoint3.service.ConsultaService;
import br.com.fiap.checkpoint3.service.PacienteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;
    private final ConsultaService consultaService;

    public PacienteController(PacienteService service, ConsultaService consultaService) {
        this.service = service;
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> create(@RequestBody PacienteRequestCreate dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> findAll(@RequestParam(required = false, defaultValue = "asc") String sort) {
        return ResponseEntity.ok(service.findAll(sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> findById(@PathVariable Long id) {
        PacienteResponse response = service.findById(id);
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> update(@PathVariable Long id, @RequestBody PacienteRequestUpdate dto) {
        PacienteResponse response = service.update(id, dto);
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.delete(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/consultas")
    public ResponseEntity<List<ConsultaResponse>> getConsultasDoPaciente(
        @PathVariable Long id,
        @RequestParam(required = false) StatusConsulta status,
        @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_de,
        @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_ate
    ) {
        return ResponseEntity.ok(consultaService.findByFilters(id, null, status, data_de, data_ate));
    }
}


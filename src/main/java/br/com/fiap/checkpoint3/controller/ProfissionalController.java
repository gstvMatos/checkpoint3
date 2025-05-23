package br.com.fiap.checkpoint3.controller;

import br.com.fiap.checkpoint3.dto.profissional.ProfissionalRequestCreate;
import br.com.fiap.checkpoint3.dto.profissional.ProfissionalRequestUpdate;
import br.com.fiap.checkpoint3.dto.profissional.ProfissionalResponse;
import br.com.fiap.checkpoint3.dto.consulta.ConsultaResponse;
import br.com.fiap.checkpoint3.service.ConsultaService;
import br.com.fiap.checkpoint3.service.ProfissionalService;
import br.com.fiap.checkpoint3.model.StatusConsulta;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    private final ProfissionalService service;
    private final ConsultaService consultaService;

    public ProfissionalController(ProfissionalService service, ConsultaService consultaService) {
        this.service = service;
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<ProfissionalResponse> create(@RequestBody ProfissionalRequestCreate dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProfissionalResponse>> findAll(@RequestParam(required = false, defaultValue = "asc") String sort) {
        return ResponseEntity.ok(service.findAll(sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalResponse> findById(@PathVariable Long id) {
        ProfissionalResponse response = service.findById(id);
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalResponse> update(@PathVariable Long id, @RequestBody ProfissionalRequestUpdate dto) {
        ProfissionalResponse response = service.update(id, dto);
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.delete(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/stats")
    public ResponseEntity<Long> getStats(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.countByProfissionalId(id));
    }

    @GetMapping("/{id}/consultas")
    public ResponseEntity<List<ConsultaResponse>> getConsultasDoProfissional(
        @PathVariable Long id,
        @RequestParam(required = false) StatusConsulta status,
        @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_de,
        @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_ate
    ) {
        return ResponseEntity.ok(consultaService.findByFilters(null, id, status, data_de, data_ate));
    }
}
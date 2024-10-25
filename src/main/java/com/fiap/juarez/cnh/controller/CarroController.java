package com.fiap.juarez.cnh.controller;

import com.fiap.juarez.cnh.dto.CarroCadastroDTO;
import com.fiap.juarez.cnh.dto.CarroExibicaoDTO;
import com.fiap.juarez.cnh.model.Carro;
import com.fiap.juarez.cnh.service.CarroService;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class CarroController {
    @Autowired
    private CarroService carroService;

    @PostMapping("/carros")
    @ResponseStatus(HttpStatus.CREATED)
    public CarroExibicaoDTO salvar(@RequestBody CarroCadastroDTO carro) {
        return carroService.salvarCarro(carro);
    }

    @GetMapping("/carros")
    @ResponseStatus(HttpStatus.OK)
    public Page<CarroExibicaoDTO> listarCarros(
            @PageableDefault(size = 2, page = 0) Pageable paginacao) {
        return carroService.listarCarros(paginacao);
    }

    @GetMapping("/carros/{id}")
    public ResponseEntity<CarroExibicaoDTO> buscarCarroPorId(@PathVariable Long id) {
            return ResponseEntity.ok(carroService.buscarCarroPorId(id));
    }

    @RequestMapping(value= "/carros", params = "placa", method = RequestMethod.GET)
    public ResponseEntity<CarroExibicaoDTO> buscarCarroPorPlaca(@RequestParam String placa) {
        return ResponseEntity.ok(carroService.buscarCarroPorPlaca(placa));
    }

    @DeleteMapping("/carros/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirCarro(@PathVariable Long id) {
        carroService.excluirCarro(id);
    }

    @PutMapping("/carros")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CarroExibicaoDTO> atualizarCarro(@RequestBody CarroCadastroDTO carro) {
        try {
            return ResponseEntity.ok(carroService.atualizarCarro(carro));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

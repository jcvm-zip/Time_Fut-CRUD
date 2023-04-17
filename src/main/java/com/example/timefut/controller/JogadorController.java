package com.example.timefut.controller;


import com.example.timefut.model.Exception.ResourceNotFoundException;
import com.example.timefut.model.Jogador;
import com.example.timefut.service.JogadorServiceImpl;
import com.example.timefut.shared.JogadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/jogadores")
public class JogadorController {

    @Autowired
    JogadorServiceImpl jogadorService;

    @GetMapping
    public ResponseEntity<List<JogadorDTO>> obterTodos(){
        return new ResponseEntity<>(jogadorService.obtertodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogadorDTO> obterPorId(@PathVariable Long id) throws ResourceNotFoundException {

        JogadorDTO jogadorDTO = jogadorService.obterPorId(id);

        return new ResponseEntity<>(jogadorDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JogadorDTO> adicionar(@RequestBody JogadorDTO jogadorDTO){
        JogadorDTO jogadorCadastrado = jogadorService.adicionar(jogadorDTO);

        return new ResponseEntity<>(jogadorCadastrado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) throws ResourceNotFoundException {
        jogadorService.deletar(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> atualizar(
            @PathVariable Long id,
            @RequestBody Jogador jogador){

        Jogador jogadorAtualizado = jogadorService.atualizar(id, jogador);
        return new ResponseEntity<>(jogadorAtualizado, HttpStatus.OK);
    }

}

package com.example.timefut.service;


import com.example.timefut.model.Exception.ResourceNotFoundException;
import com.example.timefut.model.Jogador;
import com.example.timefut.repository.JogadorRepository;
import com.example.timefut.shared.JogadorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JogadorServiceImpl implements JogadorService {

    @Autowired
    public JogadorRepository jogadorRepository;

    @Override
    public List<JogadorDTO> obtertodos() {


        List<Jogador> jogadores = jogadorRepository.findAll();

        ModelMapper mapper = new ModelMapper();

        List<JogadorDTO> jogadorDTOS = jogadores.stream().map(jogador -> mapper.map(jogador, JogadorDTO.class)).collect(Collectors.toList());

        return jogadorDTOS;
    }

    public JogadorDTO obterPorId(Long idJogador) throws ResourceNotFoundException {

        Optional<Jogador> jogador = jogadorRepository.findById(idJogador);

        if(jogador.isEmpty()) {
            throw new ResourceNotFoundException("Jogador não encontrado com o id: "+idJogador);
        }

        return new ModelMapper().map(jogador.get(), JogadorDTO.class);
    }


    @Override
    public JogadorDTO adicionar(JogadorDTO jogadorDTO) {

        jogadorDTO.setId(null);

        ModelMapper mapper = new ModelMapper();

        Jogador jogador = mapper.map(jogadorDTO, Jogador.class);

        jogador = jogadorRepository.save(jogador);

        return mapper.map(jogador, JogadorDTO.class);
    }

    @Override
    public void deletar(Long idCliente) throws ResourceNotFoundException {
        Optional<Jogador> cliente = jogadorRepository.findById(idCliente);

        if(cliente.isEmpty()){
            throw new ResourceNotFoundException("Não é possível deletar o cliente com o ID: " + idCliente + " - Cliente não encontrado");
        }

        jogadorRepository.deleteById(idCliente);
    }

    @Override
    public Jogador atualizar(Long idCliente, Jogador jogador) {
        jogador.setId(idCliente);

        return jogadorRepository.save(jogador);

    }

}

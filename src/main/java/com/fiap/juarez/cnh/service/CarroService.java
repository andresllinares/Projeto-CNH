package com.fiap.juarez.cnh.service;

import com.fiap.juarez.cnh.dto.CarroCadastroDTO;
import com.fiap.juarez.cnh.dto.CarroExibicaoDTO;
import com.fiap.juarez.cnh.exception.CarroNaoEncontradoException;
import com.fiap.juarez.cnh.model.Carro;
import com.fiap.juarez.cnh.repository.CarroRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public CarroExibicaoDTO salvarCarro(CarroCadastroDTO carroDTO) {
        Carro carro = new Carro();
        BeanUtils.copyProperties(carroDTO, carro);
        Carro carroSalvo = carroRepository.save(carro);
        return new CarroExibicaoDTO(carroSalvo);

    }

    public CarroExibicaoDTO buscarCarroPorPlaca(String placa) {
        Optional<Carro> carroOptional = carroRepository.findByPlaca(placa);
        if (carroOptional.isPresent()) {
            return new CarroExibicaoDTO(carroOptional.get());
        } else {
            throw new CarroNaoEncontradoException("Carro não encontrado");
        }
    }

    public CarroExibicaoDTO buscarCarroPorId(Long id) {
        Optional<Carro> carroOptional = carroRepository.findById(id);
        if (carroOptional.isPresent()) {
            return new CarroExibicaoDTO(carroOptional.get());
        } else {
            throw new CarroNaoEncontradoException("Carro não encontrado");
        }
    }

    public Page<CarroExibicaoDTO> listarCarros(Pageable paginacao) {
        return carroRepository.findAll(paginacao).map(CarroExibicaoDTO::new);
    }

    public void excluirCarro(Long id) {
        Optional<Carro> carroOptional = carroRepository.findById(id);
        if (carroOptional.isPresent()) {
            carroRepository.deleteById(id);
        } else {
            throw new RuntimeException("Carro não encontrado");
        }
    }

    public CarroExibicaoDTO atualizarCarro(CarroCadastroDTO carroDTO) {
        Optional<Carro> carroOptional = carroRepository.findById(carroDTO.carroId());
        if (carroOptional.isPresent()) {
            Carro carro = new Carro();
            BeanUtils.copyProperties(carroDTO, carro);

            return new CarroExibicaoDTO(carroRepository.save(carro));
        } else {
            throw new CarroNaoEncontradoException("Carro não encontrado");
        }
    }
}

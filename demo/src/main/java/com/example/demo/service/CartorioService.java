package com.example.demo.service;

import com.example.demo.model.domain.Cartorio;
import com.example.demo.model.repository.CartorioRepository;
import com.example.demo.service.dto.CartorioDTO;
import com.example.demo.service.mapper.CartorioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartorioService {
    private CartorioRepository cartorioRepository;

    @Autowired
    public CartorioService(CartorioRepository cartorioRepository) {
        this.cartorioRepository = cartorioRepository;
    }

    public Long saveCartorio(CartorioDTO catorioDTO) {
        Cartorio cartorio = new Cartorio();
        Long id = null;
        try {
            cartorio = CartorioMapper.converToEntity(catorioDTO);
            id = cartorioRepository.save(cartorio).getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }

    public List<CartorioDTO> getAllCartorios() {
        List<CartorioDTO> cartorioDTOS = new ArrayList<>();
        for (Cartorio cartorio : cartorioRepository.findAll()) {
            cartorioDTOS.add(CartorioMapper.convertToDTO(cartorio));
        }
        return cartorioDTOS;

    }

    public boolean deleteById(Long id) {
        try {
            cartorioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
        return false;
        }

    }
}

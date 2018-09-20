package com.example.demo.service.mapper;

import com.example.demo.model.domain.Cartorio;
import com.example.demo.service.dto.CartorioDTO;

public abstract class CartorioMapper {
    public static Cartorio converToEntity(CartorioDTO cartorioDTO) {
        Cartorio cartorio = new Cartorio();
        cartorio.setNome(cartorioDTO.getNome());
        cartorio.setId(cartorioDTO.getId());

        return cartorio;
    }

    public static CartorioDTO convertToDTO(Cartorio cartorio) {
        CartorioDTO cartorioDTO = new CartorioDTO();
        cartorioDTO.setId(cartorio.getId());
        cartorioDTO.setNome(cartorio.getNome());
        return cartorioDTO;
    }
}

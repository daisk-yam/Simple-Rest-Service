package com.example.demo.web.restcontroller;

import com.example.demo.service.CartorioService;
import com.example.demo.service.dto.CartorioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CartorioRestController {

    @Autowired
    CartorioService cartorioService;

    @GetMapping("/cartorios")
    public List<CartorioDTO> getAllCartorios() {
        return cartorioService.getAllCartorios();
    }

    @PostMapping("/excluir")
    public ResponseEntity<Boolean> excluirCartorio(@RequestParam("id") Long id) {
        System.out.println(id);
        Boolean salvo = cartorioService.deleteById(id);
        if(salvo != null && salvo)
        return new ResponseEntity<>(salvo, HttpStatus.ACCEPTED);
        else return new ResponseEntity<>(salvo, HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/salvar")
    public ResponseEntity<Long> salvarCartorio(@RequestBody CartorioDTO cartorioDTO) {
        System.out.println(cartorioDTO);
        Long id = cartorioService.saveCartorio(cartorioDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
    @PostMapping("/adicionar")
    public void adicionarCartorio(@RequestBody CartorioDTO cartorioDTO) {
        System.out.println(cartorioDTO);
    }
}

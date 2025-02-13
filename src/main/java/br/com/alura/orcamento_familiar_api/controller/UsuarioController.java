package br.com.alura.orcamento_familiar_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioController {


    @PostMapping
    public ResponseEntity efetuarLogin(){
       return ResponseEntity.ok().build();
    }
}

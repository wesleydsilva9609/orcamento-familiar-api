package br.com.alura.orcamento_familiar_api.controller;

import br.com.alura.orcamento_familiar_api.dto.DadosLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class UsuarioController {
    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity efetuarLogin(@RequestBody DadosLogin dadosLogin){
       return ResponseEntity.ok().build();
    }
}

package br.com.alura.orcamento_familiar_api.controller;

import br.com.alura.orcamento_familiar_api.service.ResumoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo")
@SecurityRequirement(name = "bearer-key")
public class ResumoController {
    @Autowired
    private ResumoService service;

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity resumo(@PathVariable String ano,@PathVariable String mes){
        return service.resumofiltro(ano,mes);
    }

}

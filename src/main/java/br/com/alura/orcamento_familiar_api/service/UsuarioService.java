package br.com.alura.orcamento_familiar_api.service;

import br.com.alura.orcamento_familiar_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

}

package br.com.alura.orcamento_familiar_api.service;

import br.com.alura.orcamento_familiar_api.dto.usuario.DadosLogin;
import br.com.alura.orcamento_familiar_api.dto.usuario.DadosToken;
import br.com.alura.orcamento_familiar_api.entities.Usuario;
import br.com.alura.orcamento_familiar_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    public ResponseEntity efetuandoLogin(DadosLogin dadosLogin) {
        var token = new UsernamePasswordAuthenticationToken(dadosLogin.login(),dadosLogin.senha());
        var authentication = manager.authenticate(token);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosToken(tokenJWT));
    }
}

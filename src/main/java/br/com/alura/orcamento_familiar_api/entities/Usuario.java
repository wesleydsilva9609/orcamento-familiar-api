package br.com.alura.orcamento_familiar_api.entities;

import jakarta.persistence.*;
import lombok.Getter;


@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String senha;

    public Usuario() {
    }

    public Usuario(Long id, String usuario, String senha) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
    }

}

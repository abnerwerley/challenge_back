package com.firedevit.model.mapper;

import com.firedevit.json.UsuarioForm;
import com.firedevit.model.Usuario;

public class UsuarioMapper {

    public static Usuario fromFormToEntity(UsuarioForm form) {
        return Usuario.builder()
                .idUsuario(form.getIdUsuario())
                .nome(form.getNome())
                .userName(form.getUserName())
                .email(form.getEmail())
                .senha(form.getSenha())
                .build();
    }

    private UsuarioMapper(){}
}

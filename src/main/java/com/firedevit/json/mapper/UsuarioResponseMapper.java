package com.firedevit.json.mapper;

import com.firedevit.json.UsuarioResponse;
import com.firedevit.model.Usuario;

public class UsuarioResponseMapper {

    public static UsuarioResponse fromEntityToResponse(Usuario usuario){
        return UsuarioResponse.builder()
                .id(usuario.getIdUsuario())
                .nome(usuario.getNome())
                .userName(usuario.getUserName())
                .email(usuario.getEmail())
                .build();
    }
    private UsuarioResponseMapper(){}
}

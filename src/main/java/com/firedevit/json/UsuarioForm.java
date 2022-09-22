package com.firedevit.json;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioForm {

    private Long idUsuario;
    @NotBlank
    private String nome;
    private String userName;

    @ApiModelProperty(example = "email@domínio.com.br")
    @NotBlank
    @Email(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", message = "Email deve ser válido.")
    private String email;
    @NotBlank
    @Size(min=8)
    private String senha;
}

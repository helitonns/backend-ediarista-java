package br.com.treinaweb.ediaristas.web.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEdicaoForm {

    @NotBlank
    @NotNull
    @Size(min = 3, max = 25)
    private String nomeCompleto;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    @Email
    private String email;

}

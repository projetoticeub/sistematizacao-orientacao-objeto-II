package br.com.sistematizacao.POO_II.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactSaveDTO(@NotBlank String name,
                             @NotBlank String phoneNumber,
                             @NotBlank @Email String email) {
}

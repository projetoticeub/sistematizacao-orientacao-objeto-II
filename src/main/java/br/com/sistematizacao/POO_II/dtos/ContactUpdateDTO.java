package br.com.sistematizacao.POO_II.dtos;

public record ContactUpdateDTO(Long id,
                               String name,
                               String phoneNumber,
                               String email) {
}

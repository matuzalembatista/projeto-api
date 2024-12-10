package br.edu.iftm.springmongo.models.dto;

import br.edu.iftm.springmongo.models.User;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;

    private String nome;

    private String cpf;

    private String endereco;

    private String email;

    private String telefone;

    private LocalDateTime dataCadastro;

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNome(user.getNome());
        userDTO.setEndereco(user.getEndereco());
        userDTO.setCpf(user.getCpf());
        userDTO.setEmail(user.getEmail());
        userDTO.setTelefone(user.getTelefone());
        userDTO.setDataCadastro(user.getDataCadastro());
        return userDTO;
    }
}
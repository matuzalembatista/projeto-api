package br.edu.iftm.springmongo.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import br.edu.iftm.springmongo.models.dto.UserDTO;


@Document(collection = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    @Id
    private String id;
    @Field("nome")
    private String nome;
    @Field("cpf")
    private String cpf;
    @Field("endereco")
    private String endereco;
    @Field("email")
    private String email;
    @Field("telefone")
    private String telefone;
    @Field("dataCadastro")
    private LocalDateTime dataCadastro;

    public static User convert(UserDTO userDTO) {
        User user = new User();
        user.setNome(userDTO.getNome());
        user.setEndereco(userDTO.getEndereco());
        user.setCpf(userDTO.getCpf());
        user.setEmail(userDTO.getEmail());
        user.setTelefone(userDTO.getTelefone());
        user.setDataCadastro(userDTO.getDataCadastro());
        return user;
    }
}
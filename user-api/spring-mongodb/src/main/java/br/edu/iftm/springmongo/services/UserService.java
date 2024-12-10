package br.edu.iftm.springmongo.services;

import br.edu.iftm.springmongo.models.User;
import br.edu.iftm.springmongo.models.dto.UserDTO;
import br.edu.iftm.springmongo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getAll() {
        List<User> usuarios = userRepository.findAll();
        return usuarios.stream()
            .map(UserDTO::convert)
            .collect(Collectors.toList());
    }

    public UserDTO findById(String userId) {
        User usuario = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return UserDTO.convert(usuario);
    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setDataCadastro(LocalDateTime.now());
        User user = userRepository.save(User.convert(userDTO));
        return UserDTO.convert(user);
    }

    public UserDTO delete(String userId) {
        User user = userRepository
            .findById(userId).orElseThrow(() -> new RuntimeException());
            userRepository.delete(user);
                return UserDTO.convert(user);
    }

    public UserDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if(user != null) {
            return UserDTO.convert(user);
        }
        return null;
    }

    public List<UserDTO> queryByName(String nome) {
        List<User> usuarios = userRepository.queryByNomeLike(nome);
        return usuarios
            .stream()
            .map(UserDTO::convert)
            .collect(Collectors.toList());
    }

    public UserDTO editUser(String userId, UserDTO userDTO) {
        User user = userRepository
            .findById(userId).orElseThrow(() -> new RuntimeException());
        if(userDTO.getEmail() != null && !user.getEmail().equals(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        if(userDTO.getTelefone() != null && !user.getTelefone().equals(userDTO.getTelefone())) {
            user.setTelefone(userDTO.getTelefone());
        }
        if(userDTO.getEndereco() != null && !user.getEndereco().equals(userDTO.getEndereco())) {
            user.setEndereco(userDTO.getEndereco());
        }
        user = userRepository.save(user);
        return UserDTO.convert(user);
    }

    public Page<UserDTO> getAllPages(Pageable page) {
        Page<User> users = userRepository.findAll();
        return users
            .map(UserDTO::convert);
    }
}
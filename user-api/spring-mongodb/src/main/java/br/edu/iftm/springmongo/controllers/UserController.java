package br.edu.iftm.springmongo.controllers;

import br.edu.iftm.springmongo.models.dto.UserDTO;
import br.edu.iftm.springmongo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private  UserService userService;

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @GetMapping("/{cpf}/cpf")
    public UserDTO findByCpf(@PathVariable String cpf) {
        return userService.findByCpf(cpf);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }

    @GetMapping("/search")
    public List<UserDTO> queryByName(@RequestParam(name="nome", required=true) String nome) {
        return userService.queryByName(nome);
    }

    @PatchMapping("/{id}")
    public UserDTO editUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        return userService.editUser(id, userDTO);
    }

    @GetMapping("/pageable")
    public Page<UserDTO> getUsersPage(Pageable pageable) {
        return userService.getAllPages(pageable);
    }
}

package br.edu.iftm.springmongo.repositories;

import br.edu.iftm.springmongo.models.User;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByCpf(String cpf);
    List<User> queryByNomeLike(String nome);
}
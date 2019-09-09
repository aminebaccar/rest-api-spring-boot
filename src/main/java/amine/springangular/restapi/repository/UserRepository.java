package amine.springangular.restapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import amine.springangular.restapi.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{}
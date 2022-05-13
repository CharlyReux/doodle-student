package fr.istic.tlc.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import fr.istic.tlc.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{


    public List<User> getAllUser(){
        return findAll().list();
    }
}

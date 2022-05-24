package fr.istic.tlc.dao;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import fr.istic.tlc.domain.User;

@ApplicationScoped
public class MealManagerRepository implements PanacheRepository<User> {
}
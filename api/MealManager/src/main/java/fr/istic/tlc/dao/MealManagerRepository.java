package fr.istic.tlc.dao;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MealManagerRepository implements PanacheRepository<User> {
}
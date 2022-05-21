package fr.istic.tlc.resources;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.tlc.dao.MealManagerRepository;

@RestController
@RequestMapping("/api/meal")
public class MealManagerResource {
    @Autowired
    MealManagerRepository mealManagerRepo;

    @PostMapping("/createByIDandPollID")
    @Transactional
    @Operation(summary = "Add a meal choice", description = "Add a meal choice for a user in a certain poll based on their IDs")
    public void addMealByUserIDAndPollID() {

    }
    @DeleteMapping
    @Transactional
    public void deleteMealByIDPoll() {

    }
    @DeleteMapping
    @Transactional
    public void deleteMealByUserIDAndPollID() {

    }
    @PutMapping
    @Transactional
    public void updateMealByUserIDAndPollID() {

    }
}

package fr.istic.tlc.domain;

import javax.persistence.*;

import javax.persistence.GenerationType;
import javax.persistence.Id;


//@Entity
public class Poll {

   // @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    public Poll(){}

    public Poll(String title){
        this.title=title;
    }

    public String getTitle(){
        return title;
    }
}

package com.codewithaish.springProject.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;


@RestController
public class FunRestController {

    // expose "/" that return "Hello world"

    @GetMapping("/")
    public String sayHello(){
        return "Hello Aishwarya!";
    }

//    expose new endpoint for "codeDaily"

    @GetMapping("/codeDaily")
    public String getCodeDaily() {
        return "Its good to code daily!!";
    }


//    use custom properties
    @Value("${coach.name}")
    private String name;

    @Value("${team.name}")
    private String team;

//    expose new endpoint for coach name and team name

    @GetMapping("/teamInfo")
    public String getCoachName(){
        return "Coach name " + name + "Team Name " + team;

    }

}

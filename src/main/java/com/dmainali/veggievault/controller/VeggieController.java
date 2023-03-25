package com.dmainali.veggievault.controller;

import com.dmainali.veggievault.entity.Category;
import com.dmainali.veggievault.entity.Vegetable;
import com.dmainali.veggievault.service.VeggieService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * This class is a Spring REST controller that handles requests related to vegetables.
 * @RestController annotation is used to build RESTful web services.
 * @author Dirghayu Mainali
 * @version 1.0
 */
@RestController
public class VeggieController {
    @Autowired

    VeggieService veggieService;

    @GetMapping(value= "/getCategory/{id}")
    public Category findCategoryByID(@PathVariable Long id){
        return veggieService.findById(id);
    }

}

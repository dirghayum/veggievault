package com.dmainali.veggievault.controller;

import com.dmainali.veggievault.dto.VegetableDTO;
import com.dmainali.veggievault.entity.Category;
import com.dmainali.veggievault.entity.Vegetable;
import com.dmainali.veggievault.service.VegetableFinderService;
import com.dmainali.veggievault.service.VeggieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class is a Spring REST controller that handles requests related to vegetables.
 * @RestController annotation is used to build RESTful web services.
 * @author Dirghayu Mainali
 * @version 1.0
 */
@RestController
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class VeggieController {

    VeggieService veggieService;

    VegetableFinderService vegetableFinderService;

    @GetMapping(value= "/getCategory/{id}")
    public Category findCategoryByID(@PathVariable Long id){
        return veggieService.findById(id);
    }

    @GetMapping(value= "/getVeggies")
    public List<Vegetable> findAllVeggies(){
        return veggieService.findAllVegetables();
    }


    @GetMapping(value= "/getRandom")
    public List<VegetableDTO> getRandomVegetable(){
        return vegetableFinderService.getRandom();
    }

}

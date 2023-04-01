package com.dmainali.veggievault.controller;

import com.dmainali.veggievault.dto.VegetableDTO;
import com.dmainali.veggievault.entity.Category;
import com.dmainali.veggievault.entity.ResultWrapper;
import com.dmainali.veggievault.entity.Vegetable;
import com.dmainali.veggievault.exception.VegetableNotFoundException;
import com.dmainali.veggievault.service.VegetableFinderService;
import com.dmainali.veggievault.service.VeggieService;
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
public class VeggieController {

    VeggieService veggieService;
    VegetableFinderService vegetableFinderService;
    ResultWrapper wrapper = new ResultWrapper();

    @Autowired
    public VeggieController(VeggieService veggieService, VegetableFinderService vegetableFinderService) {
        this.veggieService = veggieService;
        this.vegetableFinderService = vegetableFinderService;
    }

    @GetMapping(value= "/getCategory/{id}")
    public Category findCategoryByID(@PathVariable Long id){
        return veggieService.findById(id);
    }

    @GetMapping(value= "/getVeggies")
    public List<Vegetable> findAllVeggies(){
        return veggieService.findAllVegetables();
    }


    @GetMapping(value= "/getRandom")
    public ResultWrapper getRandomVegetable(){
        long start = System.currentTimeMillis();
        try {
            List<VegetableDTO> vegetableDTOList = vegetableFinderService.getRandom();
            wrapper.setVegetable(vegetableDTOList);
        }catch(VegetableNotFoundException exception){
            wrapper.setVegetableNotFoundException(exception);
        }
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        System.out.println("Time Taken :" + timeTaken+"ms");
        return wrapper;
    }

}

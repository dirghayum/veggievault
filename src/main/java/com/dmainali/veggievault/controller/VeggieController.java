package com.dmainali.veggievault.controller;

import com.dmainali.veggievault.dto.VegetableDTO;
import com.dmainali.veggievault.entity.Category;
import com.dmainali.veggievault.entity.ResultWrapper;
import com.dmainali.veggievault.entity.Vegetable;
import com.dmainali.veggievault.exception.VeggieException;
import com.dmainali.veggievault.service.VeggieService;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
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
@Transactional
@RestController
@NoArgsConstructor
public class VeggieController {

    VeggieService veggieService;
    ResultWrapper wrapper = new ResultWrapper();

    @Autowired
    public VeggieController(VeggieService veggieService) {
        this.veggieService = veggieService;
    }

    /**
     * This method finds and returns the Category with the specified ID.
     * @param id the ID of the category to be retrieved
     * @return the Category entity with the specified ID
     */
    @GetMapping(value= "/getCategory/{id}")
    public Category findCategoryByID(@PathVariable Long id){
        return veggieService.findById(id);
    }

    /**
     * This method retrieves and returns a list of all Vegetable entities.
     * @return a list of all Vegetable entities
     */
    @GetMapping(value= "/getVeggies")
    public List<Vegetable> getAllVeggies(){
        return veggieService.findAllVegetables();
    }

    /**
     * This method retrieves and returns a random Vegetable saved from database.
     * @return a ResultWrapper containing a random Vegetable(with id, name and ScientificName)
     * and the time taken to retrieve it
     */
    @GetMapping(value= "/getRandom")
    public ResultWrapper getRandomVegetable(){
        long start = System.currentTimeMillis();
        try {
            List<VegetableDTO> vegetableDTOList = veggieService.getRandom();
            List<Vegetable> vList = veggieService.saveAll(vegetableDTOList);
            wrapper.setVegetables(vList);
        }catch(VeggieException exception){
            wrapper.setVeggieException(exception);
        }
        catch(Exception e){
            wrapper.setVeggieException(new VeggieException("Unexpected error occurred", e));
        }
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        System.out.println("Time Taken :" + timeTaken+"ms");
        return wrapper;
    }
}

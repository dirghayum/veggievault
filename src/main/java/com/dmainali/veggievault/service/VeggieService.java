package com.dmainali.veggievault.service;

import com.dmainali.veggievault.api.VegetableFinderClient;
import com.dmainali.veggievault.dao.CategoryDao;
import com.dmainali.veggievault.dao.VegetableDao;
import com.dmainali.veggievault.dto.VegetableDTO;
import com.dmainali.veggievault.entity.Category;
import com.dmainali.veggievault.entity.Vegetable;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class VeggieService {

    private CategoryDao categoryDao;
    private VegetableDao vegetableDao;

    private VegetableFinderClient vegetableFinder;

    @Transactional
    public Category findById(Long id) {
        return categoryDao.findById(id);
    }

    public List<Vegetable> findAllVegetables(){
        return vegetableDao.findAll();
    }

    public List<Vegetable> saveAll(List<VegetableDTO> vegetables){
        Set<VegetableDTO> vSet = new HashSet<>(vegetables);
        List<Vegetable> vList = new ArrayList<>();
        Category c = categoryDao.findById(1L);
        vSet.forEach(vegetable-> {
            Vegetable v = Vegetable.builder()
                    .name(vegetable.getVegetableName())
                    .category(c)
                    .build();
            try {
                Vegetable veg = vegetableDao.save(v);
                vList.add(veg);
            }catch(Exception e){
                log.warn("Duplicate found {}",v);
            }

        });
        return vList;
    }

    public List<VegetableDTO> getRandom() {
        return vegetableFinder.getRandomVegetable();
    }
}
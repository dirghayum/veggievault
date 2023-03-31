package com.dmainali.veggievault.service;

import com.dmainali.veggievault.api.VegetableFinderClient;
import com.dmainali.veggievault.dto.VegetableDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class VegetableFinderService {

    private VegetableFinderClient vegetableFinder;

    public List<VegetableDTO> getRandom() {
        return vegetableFinder.getRandomVegetable();
    }
}

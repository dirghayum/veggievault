package com.dmainali.veggievault.entity;

import com.dmainali.veggievault.dto.VegetableDTO;
import com.dmainali.veggievault.exception.VeggieException;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultWrapper  {
    public List<Vegetable> vegetables;
    public VeggieException veggieException;
}

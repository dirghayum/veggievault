package com.dmainali.veggievault.entity;

import com.dmainali.veggievault.exception.VegetableException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeggieWrapper {
    public Vegetable1 vegetable;
    public VegetableException vegetableException;
}




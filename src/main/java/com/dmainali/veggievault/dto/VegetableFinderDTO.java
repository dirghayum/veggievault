package com.dmainali.veggievault.dto;

import ch.qos.logback.core.joran.sanity.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VegetableFinderDTO {

    List<VegetableDTO> vegetables;
}



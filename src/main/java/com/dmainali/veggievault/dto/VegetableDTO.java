package com.dmainali.veggievault.dto;

import lombok.*;

import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VegetableDTO {

        private String vegetableName;

        private String scientificName;

        @Override
        public int hashCode() {
                return Objects.hash(getVegetableName());
        }

        @Override
        public boolean equals(Object v1){
                VegetableDTO v = (VegetableDTO) v1;
                return
                        (Objects.hash(v.getVegetableName())==this.hashCode())
                        &&
                        v.getVegetableName().equals(this.vegetableName);
        }
}

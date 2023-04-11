package com.dmainali.veggievault.exception;

public class VegetableException extends RuntimeException {
        String message;
        Exception e;

        public VegetableException(String message, Exception e) {
            this.message = message;
            this.e = e;
        }

        public VegetableException(Exception e) {
            this.e = e;
        }

        public static VegetableException from(Exception e){
        return new VegetableException(e.getMessage(), e);
        }
}

package org.myfarm;

public enum Fertilizer {
    CHICKEN (.009),
    COW     (.005),
    SHEEP   (.009),
    SWINE   (.006),
    UREA    (.42);
    
    public final double nPercentage;
    Fertilizer(double nPercentage) {
        this.nPercentage = nPercentage; 
    }
    
    public double getNPercentage() {
        return this.nPercentage;
    }
}
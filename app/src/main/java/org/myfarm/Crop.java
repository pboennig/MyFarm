
import java.util.Calendar;

public enum Crop {
    CORN (67, 0.4, 75000, 9, 1, new int[] {255, 255, 0}), 
    RICE (50, 1.1, 250000, 5, 10, new int[] {0,0,0}),
    GRAIN (110, .35, 700000, 10, 3, new int [] {166, 123, 91}),
    SOYBEAN (15, .35, 123552, 10, 4, new int [] {153, 255, 0});
    
    public final double optimalFertilizer;
    public double optimalWater;
    public final double kcValue; 
    public final int seedsPerHectare;
    public final int startOfPlantingMonth;
    public final int startOfHarvestMonth;
    public final int[] color;
    
    public final int RED = 0;
    public final int GREEN = 1;
    public final int BLUE = 2;
    
    Crop(double optimalFertilizer, double kcValue, int seedsPerHectare, int startOfPlantingMonth, int startOfHarvestMonth, int[] color) {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(cal.get(Calendar.MONTH));
        this.optimalFertilizer = optimalFertilizer;
        this.kcValue = kcValue;
        this.optimalWater = kcValue * (EvapValues.P_VALUES[month] * (0.46 * EvapValues.T_VALUES[month] + 8.0));
        this.seedsPerHectare = seedsPerHectare;
        this.startOfPlantingMonth = startOfPlantingMonth;
        this.startOfHarvestMonth = startOfHarvestMonth;
        this.color = color;
    }
    public int[] getColor() {
        return this.color;
    }
    
    public double getOptimalFertilizer() {
        return this.optimalFertilizer; 
    }
    
    public int getHarvest() {
        return this.startOfHarvestMonth;
    }
    
    public int getStart() { 
        return this.startOfPlantingMonth;
    }
    
    public double getOptimalWater() {
        return this.optimalWater; 
    }
    
    public int getSeedsPerHectare() {
        return this.seedsPerHectare;
    }
}

import java.util.Calendar;

public enum Crop {
    CORN (67, 0.4, 75000, 9, 1), 
    RICE (50, 1.1, 250000, 5, 10),
    GRAIN (110, .35, 700000, 10, 3),
    SOYBEAN (15, .35, 123552, 10, 4);
    
    public final double optimalFertilizer;
    public double optimalWater;
    public final double kcValue; 
    public final int seedsPerHectare;
    public final int startOfPlantingMonth;
    public final int startOfHarvestMonth;
    
    Crop(double optimalFertilizer, double kcValue, int seedsPerHectare, int startOfPlantingMonth, int startOfHarvestMonth) {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(cal.get(Calendar.MONTH));
        this.optimalFertilizer = optimalFertilizer;
        this.kcValue = kcValue;
        this.optimalWater = kcValue * (EvapValues.P_VALUES[month] * (0.46 * EvapValues.T_VALUES[month] + 8.0));
        this.seedsPerHectare = seedsPerHectare;
        this.startOfPlantingMonth = startOfPlantingMonth;
        this.startOfHarvestMonth = startOfHarvestMonth;
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
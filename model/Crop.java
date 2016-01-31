import java.util.Calendar;

public enum Crop {
    CORN (67, 0.4, 75000), 
    RICE (50, 1.1, 250000),
    GRAIN (110, .35, 700000),
    SOYBEAN (15, .35, 123552);
    
    public final double optimalFertilizer;
    public double optimalWater;
    public final double kcValue; 
    public final int seedsPerHectare;
    
    Crop(double optimalFertilizer, double kcValue, int seedsPerHectare) {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(cal.get(Calendar.MONTH));
        this.optimalFertilizer = optimalFertilizer;
        this.kcValue = kcValue;
        this.optimalWater = kcValue * (EvapValues.P_VALUES[month] * (0.46 * EvapValues.T_VALUES[month] + 8.0));
        this.seedsPerHectare = seedsPerHectare;
    }
    
    public double getOptimalFertilizer() {
        return this.optimalFertilizer; 
    }
    
    public double getOptimalWater() {
        return this.optimalWater; 
    }
    
    public int getSeedsPerHectare() {
        return this.seedsPerHectare;
    }
}
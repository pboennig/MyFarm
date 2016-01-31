public class OptimalValuesCalculator {
    
    private double landArea;
    private Crop crop;                // make enum
    private double precipitationPerHectacre;

    public static void main(String... args) {
        OptimalValuesCalculator myCalc = new OptimalValuesCalculator(5.0, Crop.CORN, 1.0);
        System.out.println(myCalc.getWaterAmount());
        System.out.println(myCalc.getFertilizerAmount());
        System.out.println(myCalc.getSeedAmount());
    }
    
    public OptimalValuesCalculator(double landArea, Crop crop, double precipitationPerHectacre) {
        this.landArea = landArea; // in m^2
        this.crop = crop;
        this.precipitationPerHectacre = precipitationPerHectacre;
    }

    public double getSeedAmount() {
        return this.crop.seedsPerHectare * this.landArea / 100000; // landArea is in m^2. hectare = 100,000 m^2
    }
    
    public double getFertilizerAmount() {
        return this.crop.getOptimalFertilizer() / 100000; // to account for m^2 to hectares
    }
    
    /**
     * 
     * @return the number of liters of water necessary
     */
     
    public double getWaterAmount() {
        return this.crop.getOptimalWater() * this.landArea; // liters per day
    }
    
    public double getLandArea() {
        return this.landArea;
    }
}
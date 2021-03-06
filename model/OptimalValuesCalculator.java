public class OptimalValuesCalculator {

    private double landArea;
    private Crop crop;                // make enum
    private Fertilizer fertilizer;

    public OptimalValuesCalculator(double landArea, Crop crop, Fertilizer fertilizer) {
        this.landArea = landArea; // in m^2
        this.crop = crop; //type of crop [Corn, Rice, Wheat, Soybean]
        this.fertilizer = fertilizer; //type of fertilizer [Urea, Sheep, Swine, Cow, Chicken]
    }

    public double getSeedAmount() {
        return this.crop.seedsPerHectare * this.landArea / 100000; // landArea is in m^2. hectare = 100,000 m^2
    }

    public double getFertilizerAmount() {
        return this.getLandArea() / this.fertilizer.getNPercentage() / 100000; // to account for m^2 to hectares, in kg
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

    public int getStart() {
        return this.crop.getStart();
    }

    public int getHarvest() {
        return this.crop.getHarvest();
    }

}

public class OptimalValuesCalculatorTester {
	public static void main(String... args) {
		OptimalValuesCalculator myCalc = new OptimalValuesCalculator(5.0, Crop.CORN, Fertilizer.COW);
		System.out.println("Optimal water amt: " + myCalc.getWaterAmount()); // in liters
		System.out.println("Optimal fert amt: " + myCalc.getFertilizerAmount()); // in kg
		System.out.println("Optimal seed amt: " + myCalc.getSeedAmount()); //in seeds
	}
}

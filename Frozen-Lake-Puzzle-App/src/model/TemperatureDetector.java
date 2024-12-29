package model;

/**
 * Class representing a temperature detector.
 */
public class TemperatureDetector extends ResearcherEquipment {

    public TemperatureDetector(String field) {
        super(field, "Temperature Detector");
    }

    @Override
    public void method() {
        System.out.println("Temperature detector in use.");
    }
}


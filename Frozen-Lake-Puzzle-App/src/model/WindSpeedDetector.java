package model;

/**
 * Class representing a wind speed detector.
 */
public class WindSpeedDetector extends ResearcherEquipment {

    public WindSpeedDetector(String field) {
        super(field, "Wind Speed Detector");
    }

    @Override
    public void method() {
        System.out.println("Wind speed detector in use.");
    }
}

package model;

/**
 * Class representing a camera.
 */
public class Camera extends ResearcherEquipment {

    public Camera(String field) {
        super(field, "Camera");
    }

    @Override
    public void method() {
        System.out.println("Camera in use.");
    }
}
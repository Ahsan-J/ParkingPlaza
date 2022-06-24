package src;

public class Car {
    private String make;
    private String plate;
    private String color;

    public String getMake() {
        return make;
    }

    public String getPlate() {
        return plate;
    }

    public String getColor() {
        return color;
    }

    public Car(String plate, String make, String color) {
        this.plate = plate;
        this.make = make;
        this.color = color;
    }
    public Car(String plate, String make) {
        this.plate = plate;
        this.make = make;
    }
    public Car(String plate) {
        this.plate = plate;
    }
}

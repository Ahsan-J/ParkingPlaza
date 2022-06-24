package src;

class Main {
    public static void main(String[] args) {
        ParkingPlaza plaza = new ParkingPlaza(10);
        plaza.addParkingCar(new Car("ABC-123", "Honda", "red"));
        plaza.addParkingCar(new Car("ABC-321", "Honda", "red"));
        plaza.addParkingCar(new Car("XYZ-777", "Honda", "red"), 1);
        
        for(ParkingSpace space: plaza.getParkedSpaces()) {
            System.out.println("id: " + space.getId());
            System.out.println("place: " + space.getCar().getPlate());
        }
    }
}
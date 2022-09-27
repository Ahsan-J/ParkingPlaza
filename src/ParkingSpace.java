package src;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ParkingSpace {
    private Car car = null;
    private UUID id = UUID.randomUUID();
    private Date parkingTime = new Date();
    private Date expiryTime = new Date();

    public ParkingSpace(Car car, Date expiryDate) {
        Date currentTime = new Date();
        if (expiryDate.after(currentTime)) {
            this.car = car;
            this.expiryTime = expiryDate;
            this.parkingTime = currentTime;
        }
    }

    public Car getCar() {
        return car;
    }

    public String getId() {
        return id.toString();
    }

    public long getRemainingTime() {
        Date currentTime = new Date();
        long diff = this.expiryTime.getTime() - currentTime.getTime();
        return TimeUnit.MILLISECONDS.toSeconds(diff);
    }

    public void extendTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Date getParkingTime() {
        return parkingTime;
    }

    public boolean isVacant() {
        return this.car == null;
    }

    public void onExpiry(Runnable runnable) {
        new Thread(() -> {
            try {
                Thread.sleep(this.getRemainingTime());
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }
}

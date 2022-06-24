package src;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ParkingSpace {
    private Car car = null;
    private UUID id = UUID.randomUUID();
    private Date parkingTime = new Date();
    private Date expiryTime = new Date();
    private ExecutorService executor = Executors.newSingleThreadExecutor();

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
    
    public void onExpiry(Callable<ParkingSpace> task) throws InterruptedException, ExecutionException, TimeoutException {
        final Future<ParkingSpace> handler = executor.submit(task);
        handler.get(this.getRemainingTime(), TimeUnit.SECONDS);
    }
}

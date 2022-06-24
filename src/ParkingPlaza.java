package src;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ParkingPlaza {
    private int parkingSize = 0;
    private ParkingSpace[] spaces;
    

    public ParkingPlaza(int size) {
        this.parkingSize = size;
        spaces = new ParkingSpace[size];
    }

    public ParkingSpace addParkingCar(Car car, Date expiration) {
        for(int i = 0; i < spaces.length; i++) {
            if(spaces[i] == null || spaces[i].isVacant()) {
                spaces[i] = new ParkingSpace(car, expiration);
                return spaces[i];
            }
        }
        return null;
    }

    public ParkingSpace addParkingCar(Car car) {
        Date expiration = new Date(Calendar.getInstance().getTimeInMillis() + (30 * 60 * 1000));
        return this.addParkingCar(car, expiration);
    }
    public ParkingSpace addParkingCar(Car car, int position) {
        Date expiration = new Date(Calendar.getInstance().getTimeInMillis() + (30 * 60 * 1000));
        return this.addParkingCar(car, position, expiration);
    }

    public ParkingSpace addParkingCar(Car car, int position, Date expiration) {
        ParkingSpace space = spaces[position];

        if(space == null || space.isVacant()) {
            space = new ParkingSpace(car, expiration);
            return space;
        }
        
        return null;
    }

    public void removeParkedCar(ParkingSpace space) {
        for(int i = 0; i < spaces.length; i++) {
            ParkingSpace s = spaces[i];
            if(s.getId().equals(space.getId())) {
                spaces[i] = null;
            }
        }
    }

    public void removeParkedCar(String id) {
        for(int i = 0; i < spaces.length; i++) {
            ParkingSpace space = spaces[i];
            if(space.getId().equals(id)) {
                spaces[i] = null;
            }
        }
    }

    public int getSize() {
        return this.parkingSize;
    }

    public ParkingSpace[] getParkedSpaces() {
        ArrayList<ParkingSpace> parkedSpaces = new ArrayList<ParkingSpace>();
        for(int i = 0; i < spaces.length; i++) {
            ParkingSpace space = spaces[i];
            if(space != null && !space.isVacant()) {
                parkedSpaces.add(space);
            }
        }

        return parkedSpaces.toArray(new ParkingSpace[parkedSpaces.size()]);
    }

    public int getParkingCounts() {
        int count = 0;
        for(ParkingSpace space: spaces) {
            if(space != null && space.isVacant()) {
                ++count;
            }
        }
        return count;
    }

    public int getRemainingSize() {
        return this.parkingSize - this.getParkingCounts();
    }

    
}

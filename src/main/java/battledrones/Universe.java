package battledrones;

import battledrones.devices.Drone;

import java.util.List;

/**
 * Created by evilwasp on 03/10/15.
 */
public class Universe implements PeepHole {
    List<Drone> drones;
    boolean shutdown = false;



    public Universe(){

    }

    public List<Drone> getDrones() {
        return drones;
    }

    public void run() {
        while(!shutdown){

        }
    }
}

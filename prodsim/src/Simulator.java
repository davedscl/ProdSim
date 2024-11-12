import java.util.ArrayList;
import java.util.List;

import api.ControlSystemAPI;
import api.SensorChangeObserver;

public class Simulator implements SensorChangeObserver {
    private List<Machine> machines;
    private ControlSystemAPI api;

    public Simulator(ControlSystemAPI api) {
        this.api = api;
        this.machines = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            machines.add(new Machine(i, this));
        }

        this.api.registerEmptyPlaceObserver(this);
        this.api.registerFullPlaceObserver(this);
    }

    @Override
    public void onEmptyPlaceSensorChanged() {
        // if emptyplace == full "Leergut" available
        if (api.getEmptyPlaceSensor()) {
            System.out.println("Empty Container available - searching for machine for production");
            for (Machine machine : machines) {
                // machine not in production
                if (!machine.hasEmptyContainer() && !machine.hasFullContainer()) {
                    machine.loadEmptyContainer();
                    break;
                }
            }
        }
    }

    @Override
    public void onFullPlaceSensorChanged() {
        // if fullplace == empty "Vollgut" can be unloaded
        if (!api.getFullPlaceSensor()) {
            System.out.println("Full place available - checking machines for full containers ");
            for (Machine machine : machines) {
                if (machine.hasFullContainer()) {
                    machine.unloadFullContainer();
                    break;
                }
            }
        }
    }

    public boolean emptyContainerAvailable() {
        return api.getEmptyPlaceSensor();
    }

    public void takeEmptyContainer() {
        api.setEmptyPlaceSensor(false);
    }

    public boolean fullPlaceAvailable() {
        return !api.getFullPlaceSensor();
    }

    public void placeFullContainer() {
        api.setFullPlaceSensor(true);
    }

    public int startTimer(int seconds, Runnable callback) {
        return api.startTimer(seconds, callback);
    }

    public void killTimer(int timerId) {
        api.killTimer(timerId);
    }

    public List<Machine> getMachines() {
        return machines;
    }

}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;



public class ControlSystemAPI implements ControlSystemInterface{

    private boolean emptyPlaceSensor;
    private boolean fullPlaceSensor;
    private int nextTimerId = 0;
    private Map<Integer, TimerTask> activeTimers;
    private List<SensorChangeObserver> emptyPlaceObservers;
    private List<SensorChangeObserver> fullPlaceObservers;

    public ControlSystemAPI(){
        this.activeTimers= new HashMap<>();
        this.emptyPlaceSensor = false;
        this.fullPlaceSensor = false;
        this.emptyPlaceObservers = new ArrayList<>();
        this.fullPlaceObservers = new ArrayList<>();
    }


    public void registerEmptyPlaceObserver(SensorChangeObserver observer) {
        emptyPlaceObservers.add(observer);
    }

    public void registerFullPlaceObserver(SensorChangeObserver observer) {
        fullPlaceObservers.add(observer);
    }

    private void notifyEmptyPlaceSensorChangeObservers() {
        for (SensorChangeObserver observer : emptyPlaceObservers) {
            observer.onEmptyPlaceSensorChanged();
        }
    }

    private void notifyFullPlaceSensorChangeObservers() {
        for (SensorChangeObserver observer : fullPlaceObservers) {
            observer.onFullPlaceSensorChanged();
        }
    }

    @Override
    public void setEmptyPlaceSensor(boolean value) {
        if(this.emptyPlaceSensor != value) {
            System.out.println("ControlSystem: EmptyPlaceSensor changed: "+value);
            this.emptyPlaceSensor = value;
            notifyEmptyPlaceSensorChangeObservers();
            //simulator.onEmptyPlaceSensorChanged();
        }
    }

    @Override
    public boolean getEmptyPlaceSensor() {
        return this.emptyPlaceSensor;
    }

    @Override
    public void setFullPlaceSensor(boolean value) {
        if(this.fullPlaceSensor != value) {
            System.out.println("ControlSystem: FullPlaceSensor changed: "+value);
            this.fullPlaceSensor = value;
            notifyFullPlaceSensorChangeObservers();
            //simulator.onFullPlaceSensorChanged();
        }
    }

    @Override
    public boolean getFullPlaceSensor() {
        return this.fullPlaceSensor;
    }

    @Override
    public int startTimer(int seconds, Runnable callback) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                callback.run();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, seconds * 1000);
        int timerId = nextTimerId++;
        activeTimers.put(timerId, task);
        return timerId;
    }

    @Override
    public void killTimer(int id) {
        TimerTask task = activeTimers.get(id);
        if(task!=null){
            task.cancel();
            activeTimers.remove(id);
        }
    }

}
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    private List<Machine> machines;
    private ControlSystemAPI api;
    
    public Simulator(ControlSystemAPI api){
        this.api = api;
        this.machines = new ArrayList<>();
        for(int i = 0; i<3; i++){
            machines.add(new Machine(i,this));
        }
    }



    
    public boolean emptyContainerAvailable(){
        return api.getEmptyPlaceSensor();
    }

    public void takeEmptyContainer(){
        System.out.println("Empty container is taken");
        api.setEmptyPlaceSensor(false);
    }

    public boolean fullPlaceAvailable(){
        return !api.getFullPlaceSensor();
    }

    public void placeFullContainer(){
        System.out.println("Full container is placed");
        api.setFullPlaceSensor(true);
    }

    public int startTimer(int seconds, Runnable callback){
        return api.startTimer(seconds, callback);
    }

    public void killTimer(int timerId){
        api.killTimer(timerId);
    }



}

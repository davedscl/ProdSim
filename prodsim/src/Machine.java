public class Machine {

    private boolean hasEmptyContainer;
    private boolean hasFullContainer;
    
    private int machineId;
    private Simulator simulator;
    private int currentTimerId;

    public Machine(int ID, Simulator simulator){
        this.machineId= ID;
        this.simulator = simulator;
        this.hasEmptyContainer = false;
        this.hasFullContainer = false;
        this.currentTimerId = -1;
    }



}

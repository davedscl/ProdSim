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

    public void loadEmptyContainer(){
        // if "Leergutplatz" has empty container 
        if(simulator.emptyContainerAvailable() && !hasEmptyContainer){
            simulator.takeEmptyContainer();
            System.out.println("Machine "+machineId+": took empty container");
            hasEmptyContainer=true;
            startProduction();
        }
    }

    private void startProduction(){
        if(hasEmptyContainer && !hasFullContainer){
            currentTimerId = simulator.startTimer(20, ()->finishProduction());
        }
    }

    private void finishProduction(){
        System.out.println("Machine "+machineId+": finished production");
        hasEmptyContainer = false;
        hasFullContainer = true;
        unloadFullContainer();
    }

    public void unloadFullContainer(){
        if(hasFullContainer && simulator.fullPlaceAvailable()){
            System.out.println("Machine "+machineId+": unloads product");
            simulator.placeFullContainer();
            hasFullContainer=false;
        }
    }

    public boolean hasEmptyContainer(){
        return this.hasEmptyContainer;
    }

    public boolean hasFullContainer(){
        return this.hasFullContainer;
    }

    public int getMachineId() {
        return this.machineId;
    }

    public void cancelCurrentTimer() {
        if (currentTimerId != -1) {
            simulator.killTimer(currentTimerId);
            currentTimerId = -1;
        }
    }

}

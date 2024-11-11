public class Main {
    public static void main(String[] args) {
        ControlSystemAPI controlSystemAPI = new ControlSystemAPI();
        Simulator simulator = new Simulator(controlSystemAPI);


        controlSystemAPI.setEmptyPlaceSensor(true);
        controlSystemAPI.setFullPlaceSensor(false);
        for(int i=0; i<3; i++){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!controlSystemAPI.getEmptyPlaceSensor()){
                //place new empty container
                controlSystemAPI.setEmptyPlaceSensor(true);
            }
            if(controlSystemAPI.getFullPlaceSensor()){
                //pick up full container
                controlSystemAPI.setFullPlaceSensor(false);
            }


        }

        // Shut down the simulation
        for (Machine machine : simulator.getMachines()) {
            machine.cancelCurrentTimer();
        }
        
        System.out.println("Simulation complete.");
        
    }
}
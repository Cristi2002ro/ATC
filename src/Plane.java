public class Plane extends Thread {
    int plane_number;
    int nrOfPassengers;
    boolean embarking;

    Plane(int plane, int passengers){
        this.nrOfPassengers=passengers;
        this.plane_number=plane;
    }

    void disembark(){
        //passengers:
        System.out.println("Plane "+plane_number+": passengers disembarking...");
        /*
        for(int i=1;i<=nrOfPassengers;i++){

            new Passenger(i,false,plane_number);
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            sleep(800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--------------------------------------------\nPlane "+plane_number+": all passengers disembarked");
        */
    }

    void embark(){
        //passengers:
        System.out.println("Plane "+plane_number+": passengers embarking...");
        /*
        for(int i=1;i<=nrOfPassengers;i++){
            new Passenger(i,true,plane_number);
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            sleep(800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--------------------------------------------\nPlane "+plane_number+": all passengers embarked");
        */
    }

    public void request(){
        System.out.println("Plane "+plane_number+": asking for landing");
    }

    @Override
    public void run() {
        if(embarking)embark();
        else disembark();
    }
}

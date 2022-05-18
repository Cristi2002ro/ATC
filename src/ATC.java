import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ATC extends Thread {

    boolean runway = true; //checking if the runway is empty
    boolean gate1 = true; //for arrivals
    boolean gate2 = true; //for departures
    boolean tank = true; //for refill

    Queue<Plane> queue = new LinkedList<>(); //for the planes that want to land

    @Override
    public void run() {
        while (!queue.isEmpty()) {

            if (runway) {
                Plane current=queue.poll();
                System.out.println("ATC: The runway is free, plane "+current.plane_number+" can land on the runway");
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("ATC: The plane "+current.plane_number+" landed on the runway");
                runway=false;
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                runway=true;
                //run();
                if (gate1) {
                    gate1=false;
                    System.out.println("ATC: Plane "+current.plane_number+" has arrived to gate 1, the runway is free now");
                    current.disembark();
                    System.out.println("ATC: Plane "+current.plane_number+" is fueling and cleaning...");
                    tank=false;
                    try {
                        sleep(2000);
                        tank=true;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    gate1=true;
                    tank=true;

                    //gate 2:
                    if(gate2){
                        try {
                            System.out.println("ATC: Plane "+current.plane_number+" can go now to gate 2 for departures, gate1 is free now");
                            sleep(2000);
                            current.embark();
                            sleep(2000);
                            if (runway) {
                                gate2 = true;
                                System.out.println("ATC: The runway is free, plane "+current.plane_number+" can go");
                                runway = false;
                                sleep(2000);
                                System.out.println("ATC: Plane " + current.plane_number + " has gone");
                                runway = true;
                            }
                        }catch (Exception e){}
                    }else {
                        System.out.println("ATC: Gate 2 is ocupied now, plane "+current.plane_number+" have to wait");
                        queue.offer(current);
                    }
                } else {
                    System.out.println("ATC: Gate 1 is ocupied now, plane "+current.plane_number+" have to wait");
                    queue.offer(current);
                }


            } else {
                System.out.println("ATC: The runway is not free yet, we placed you in queue");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

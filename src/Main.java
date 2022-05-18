import java.util.ArrayList;
import java.util.Random;


public class Main {
    static ArrayList<Plane> planes = new ArrayList<>();

    public static void main(String[] args) {
        ATC atc = new ATC();
        for (int i = 1; i <= 6; i++) {
            planes.add(new Plane(i, passengerNumber()));
        }

        int index = nextPlaneRequestIndex();
        Plane requester = planes.get(index);
        planes.remove(index);
        requester.request();
        atc.queue.offer(requester);
        atc.start();

        while (!planes.isEmpty()) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                int i = nextPlaneRequestIndex();
                Plane request = planes.get(i);
                planes.remove(i);
                request.request();
                atc.queue.add(request);
                atc.run();
            } catch (IllegalArgumentException e) {
                System.out.println("ALL THE PLANES GONE");
                System.exit(0);
            }
        }
    }

    static int passengerNumber() {
        Random random = new Random();
        return random.nextInt(49) + 1;
    }

    static int nextPlaneRequestIndex() {
        Random random = new Random();
        int nr = planes.size();
        return random.nextInt(nr - 1);
    }
}

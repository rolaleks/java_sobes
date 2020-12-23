package gb.ping;

public class PingPong {


    private boolean isPing = false;


    public static void main(String[] args) {

        PingPong pingPong = new PingPong();

        (new Thread(new Ping(pingPong))).start();
        (new Thread(new Pong(pingPong))).start();
    }

    public synchronized void ping() {

        while (isPing) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("ping");
        isPing = true;
        notify();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void pong() {
        System.out.println("pong");
        isPing = false;
        notify();

        while (!isPing) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

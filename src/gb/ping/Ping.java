package gb.ping;

public class Ping implements Runnable {

    private PingPong pingPong;

    public Ping(PingPong pingPong) {
        this.pingPong = pingPong;
    }

    @Override
    public synchronized void run() {
        while (true){
            pingPong.ping();
        }
    }
}

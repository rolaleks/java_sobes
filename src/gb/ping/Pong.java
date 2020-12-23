package gb.ping;

public class Pong implements Runnable {


    private PingPong pingPong;

    public Pong(PingPong pingPong) {
        this.pingPong = pingPong;
    }

    @Override
    public synchronized void run() {
        while (true){
            pingPong.pong();
        }
    }
}

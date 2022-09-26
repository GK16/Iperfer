package guikai;

public class Client {
    private String hostName;
    private int serverPort;
    private int time;

    Client(){}
    Client(String hostName, int serverPort, int time){
        this.hostName = hostName;
        this.serverPort = serverPort;
        this.time = time;
    }
}

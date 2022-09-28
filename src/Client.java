import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private String hostName;
    private int serverPort;
    private int time;

    Client(){}

    /**
     * init Client
     *
     * @param hostName
     * @param serverPort
     * @param time time to run at client mode
     */
    Client(String hostName, int serverPort, int time){
        this.hostName = hostName;
        this.serverPort = serverPort;
        this.time = time;
    }

    /**
     * launch the client service
     */
    public void start(){
        int chunkNum = 0;
        try{
            // Create Socket
            Socket socket = new Socket (hostName, serverPort);
            // Create outputStream
            OutputStream outputStream = socket.getOutputStream();

            // Write message
            byte[] dataChunk = new byte[1000];
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < time * 1000){
                outputStream.write(dataChunk);
                chunkNum++;
            }

            // Close connection
            outputStream.close();
            socket.close();
        } catch (Exception e){
            System.err.println("Failed to connect to server");
            e.printStackTrace();
            System.exit(0);
        }
        // calculate rate
        float rate = (float) chunkNum / (time * 1000);
        System.out.printf("sent=%d KB rate=%.3f Mbps\n", chunkNum, rate);

    }
}

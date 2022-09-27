package guikai;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

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

    public void start(){
        int chunkNum = 0;
        try{
            // Create Socket
            Socket socket = new Socket (hostName, serverPort);
            // Create writer
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter (outputStream, true);

            // Write message
            byte[] dataChunk = new byte[1000];
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < time * 1000){
                out.println(dataChunk);
                chunkNum++;
            }

            // Close connection
            out.close();
            socket.close();
        } catch (Exception e){
            System.err.println("Failed to connect to server");
            e.printStackTrace();
            System.exit(0);
        }
        float rate = (float) chunkNum / time;
        System.out.printf("sent=%d KB rate=%.3f Mbps\n", chunkNum, rate);

    }
}

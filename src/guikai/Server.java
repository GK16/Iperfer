package guikai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class Server {
    private int portNum;

    Server(){}
    Server(int portNum){
        this.portNum = portNum;
    }

    public void start(){
        long byteRecieved = 0;
        long startTime = 0;
        long endTime = 0;
        System.out.println("Server Mode.");
        try{
            ServerSocket server = new ServerSocket(portNum);
            // Listen for a connection
            System.out.printf("Server is listening at port %d now.", portNum);
            Socket socket = server.accept();
            // Create BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Read message
            startTime = System.currentTimeMillis();
            String line = br.readLine();
            while (line != null){
                byteRecieved += line.getBytes(StandardCharsets.UTF_8).length;
                line = br.readLine();
            }
            endTime = System.currentTimeMillis();
            // Close connection
            br.close();
            System.out.println("Server is shutting down.");
            socket.close();
            server.close();
        } catch (IOException e){
            System.err.println("Failed to listen port");
            e.printStackTrace();
            System.exit(0);
        }
        float time = (float) (endTime - startTime) / (float)1000;
        long KbRevieved = byteRecieved / 1000;
        System.out.printf("Server recieved = %d KB rate = %.3f Mbps%n", KbRevieved, (float) KbRevieved / time);
    }
}

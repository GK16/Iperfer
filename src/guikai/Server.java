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
            // Read message
            int bytesNum = 0;
            startTime = System.currentTimeMillis();
            while (bytesNum > -1) {
                byte[] bytes = new byte[1000];
                //the total number of bytes read into the buffer
                // or -1 if there is no more data because the end of the stream has been reached.
                bytesNum = socket.getInputStream().read(bytes);
                byteRecieved +=  bytesNum;
            }

            endTime = System.currentTimeMillis();
            // Close connection
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
        System.out.printf("Server recieved = %d KB rate = %.3f Mbps%n", KbRevieved, (float) KbRevieved / (time*1000));
    }
}

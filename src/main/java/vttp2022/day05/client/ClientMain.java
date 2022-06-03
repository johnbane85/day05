package vttp2022.day05.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.Console;
import java.net.Socket;

public class ClientMain {

  public static void main(String[] args) throws IOException {

    System.out.println("Connecting to localhost at port 3000");

    //connect the server
    //127.0.0.1 - localhost
    Socket sock = new Socket("127.0.0.1", 3000);

    System.out.println("Connected ...");

    // Get the input and output stream - bytes
    // Get the input stream
    InputStream is = sock.getInputStream();
    DataInputStream dis = new DataInputStream(is);

    // Get the output stream
    OutputStream os = sock.getOutputStream();
    DataOutputStream dos = new DataOutputStream(os);

    Console cons = System.console();

    boolean isConnectionEnded = false;

    while (!isConnectionEnded){
      String input = cons.readLine("Say something to the server ");

      // Write to server
      dos.writeUTF(input);
      dos.flush();
  
      // Wait for response from server
      String response = dis.readUTF();
      System.out.printf(">> %s\n", response);

      if (response.equals("Server has been requested to end!")){
        isConnectionEnded = true;
      }
    }
    

    // close the streams
    is.close();
    os.close();

    // close the socket
    sock.close();
  }
  
}

package vttp2022.day05.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;


public class ServerMain {
  
  public static void main(String[] args) throws IOException {

    // create a server scoket and list to a port
    ServerSocket server = new ServerSocket(3000);

    System.out.println("Waiting for connection on port 3000 ...");
    Socket sock = server.accept();
    System.out.println("Connection accepted");
    System.out.println("Calculator App");

    // Get the input and output stream - bytes
    // Get the input stream
    InputStream is = sock.getInputStream();
    DataInputStream dis = new DataInputStream(is);

    // Get the output stream
    OutputStream os = sock.getOutputStream();
    DataOutputStream dos = new DataOutputStream(os);

    //   // Read request from client
    //   String request = dis.readUTF();

    //   // Perform some operationon the request
    //   request = request.toUpperCase();

    //   // Wtire back the data to the client
    //   dos.writeUTF(request);
    boolean isConnectionEnded = false;

    int result = 0;

    while (!isConnectionEnded){
      // Read request from client
      String request = dis.readUTF();
      System.out.println("Request: "+ request);

      if (request.equals("exit")){
        isConnectionEnded = true;
        dos.writeUTF("Server has been requested to end!");
      } else {

        String[] userCommand = request.split(" ");
        int operand1;
        int operand2;
        String operator;
      // for (String val : userCommand){
      //   System.out.println(val);
      // }

      try{
        operand1 = Integer.parseInt(userCommand[0]);
        operator = userCommand[1];
        operand2 = Integer.parseInt(userCommand[2]);
      } catch(Exception ex){
        dos.writeUTF("Invalid inputs supplied to calculator");
        continue;
      }
  
        switch (operator){
          case "+":
            result = operand1 + operand2;
            break;
          case "-":
            result = operand1 - operand2;
            break;
          case "*":
            result = operand1 * operand2;
            break;
          case "/":
            if (operand2 == 0){
              System.out.println("A");
              result = -1;
              break;
            } else{
              System.out.println("B");
              result = operand1 / operand2;
            }
            break;
          default: break;
        }

        // Perform some operationon the request
        // request = request.toUpperCase();

        // Wtire back the data to the client
        dos.writeUTF(Integer.toString(result));
      }
    }
   

    //close the streams
    is.close();
    os.close();

    // close the socket
    sock.close();
  }
}

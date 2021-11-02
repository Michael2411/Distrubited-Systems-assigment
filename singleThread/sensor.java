

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class sensor{
    public static void main(String[] args){
        try{
            ServerSocket sensorServer = new ServerSocket(3000);
            System.out.println("Sensor Running...");

            Socket serversocket = sensorServer.accept();
            DataInputStream serverIn = new DataInputStream(serversocket.getInputStream());
            DataOutputStream serverOut= new DataOutputStream(serversocket.getOutputStream());
            System.out.println("Sensor Connected");
            while(true){
               String Serverinput = serverIn.readUTF();
               System.out.println("Client submitted a request");
               System.out.println("[Client]:Destination is "+ Serverinput);

               String carType = serverIn.readUTF();
               System.out.println("[Client]: The Client is Driving a "+carType);

               if(carType.equalsIgnoreCase("bike")){
                serverOut.writeUTF("[App]:The best route is to turn left then right and continue straight forward");
               }
               else if(carType.equalsIgnoreCase("car")){
                serverOut.writeUTF("[App]:The best route is to continue straight forward and take the bridge");
               }
               else if(carType.equalsIgnoreCase("Bus")){
                serverOut.writeUTF("[App]:The best route is to continue straight forward and then take the first right before the traffic lights");
               }
               
            }
        }
         catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
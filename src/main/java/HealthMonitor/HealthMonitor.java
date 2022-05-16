package HealthMonitor;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class HealthMonitor {

    private DatagramSocket udp_socket;
    private final int port = 3500;

    public HealthMonitor () throws SocketException {
        this.udp_socket = new DatagramSocket(this.port);
    }

    private final int threshold = 1024;

    public void listen() throws IOException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("-- Running The Health Monitor at " + localHost.getHostAddress() + " at port : "+ port);
        List<String> batch = new ArrayList<String>();

        while (true) {

            byte[] buffer = new byte[65535];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            // blocks until a packet is received
            udp_socket.receive(packet);

            String health_msg = new String(packet.getData()).trim();
            System.out.println("Message from " + packet.getAddress().getHostAddress() + ": " + health_msg);
            System.out.println("--------------------------------");
            batch.add(health_msg);      // add the msg to the batch
            // if the messages reach a size threshold of 1024 messages
            if (batch.size() == threshold){
                ////////////// Direct the batch to hdfs ///////////{
                //URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());

                // Clear the batch
                batch.clear();
            }

            // Clear the buffer after every message.
            buffer = new byte[65535];
        }
    }

    public static void main(String[] args) throws IOException {
        HealthMonitor healthMonitor = new HealthMonitor();
        healthMonitor.listen();
    }


}
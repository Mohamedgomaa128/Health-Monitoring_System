package MicroServices;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import java.io.IOException;
import java.net.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Microservices {

    private DatagramSocket udp_socket;
    private InetAddress server_address;
    private int port;
    private final int healthMonitor_port = 3500;

    public Microservices (String address, int port) throws SocketException, UnknownHostException {
        this.server_address = InetAddress.getByName(address);
        this.port = port;
        udp_socket = new DatagramSocket(this.port);
    }

    private void start () throws IOException, InterruptedException {
        for (int i = 0; i < 20 ; i++) {
            String health_message = health_check();
            System.out.println(health_message);
            DatagramPacket packet = new DatagramPacket(health_message.getBytes(), health_message.getBytes().length, server_address, healthMonitor_port);
            System.out.println("sent a message to " + server_address + " at port " + healthMonitor_port);
            this.udp_socket.send(packet);
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Microservices microservice = new Microservices(args[0], Integer.parseInt(args[1]));
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("----- Running a microservice at " + localHost.getHostAddress());
        microservice.start();
    }

    /*
    Message Format {
        "serviceName": "[Service Name Here]",
        “Timestamp”: UNIX Timestamp of sampling,
        "CPU": value in float from 0 to 1,
        "RAM": { "Total": total RAM size in GBs, "Free": free space less than total},
        "Disk": { "Total": total Disk size in GBs, "Free": free space less than total},
    }
    Example {
        "serviceName": "Ticketing Service",
        “Timestamp”: 1646485461,
        "CPU": 0.35,
        "RAM": { "Total": 4, "Free": 1.5},
        "Disk": { "Total": 100, "Free": 75},
    }
    */
    private String health_check () {
        Map<String, java.io.Serializable> obj=new LinkedHashMap<>();

        obj.put("serviceName","Ticketing Service");
        obj.put("Timestamp",new Integer(1646485461));
        obj.put("CPU",new Float(0.35));

        JSONArray RAM_arr = new JSONArray();
        RAM_arr.add(new Float(4));
        RAM_arr.add(new Float(1.5));
        JSONArray Disk_arr = new JSONArray();
        Disk_arr.add(new Float(100));
        Disk_arr.add(new Float(75));

        obj.put("RAM",RAM_arr);
        obj.put("Disk",Disk_arr);

        String health_message = JSONValue.toJSONString(obj);
        return health_message;
    }



}
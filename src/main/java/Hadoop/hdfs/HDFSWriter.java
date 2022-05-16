package Hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.Path;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class HDFSWriter {

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void writeToHDFS(ArrayList<String> list)  { // here we get messages and reformat them
        try {
            Configuration con = new Configuration();
            final String link = "hdfs://mohamed-master:8088"; // address to send to <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            FileSystem hdfs = FileSystem.get(new URI(link), con);
            // format of date
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy");
            //get date
            LocalDateTime now = LocalDateTime.now();
            Path file = new Path(link + dtf.format(now) + ".log");
            System.out.println(dtf.format(now));

            // if the file not exist .. append to it
            if (!hdfs.exists(file))
                hdfs.create(file);
            OutputStream os = hdfs.create(file);

            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            for (String message : list)
                br.write(message + "\n");

            br.close();
            hdfs.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) throws Exception {
    }

}
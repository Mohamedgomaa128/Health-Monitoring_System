package Hadoop.Map_Reduce;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.IOException;

public class Avg_Mapper extends Mapper<Object, Text, Text, FloatWritable>
{
    //hadoop supported data types
    private Text word = new Text();
    private final static FloatWritable one = new FloatWritable(1);
    private  FloatWritable Cpu  ;
    private String ServiceName = null ;
    private class Disk { public float Total, Free;}
    private class RAM { public float Total, Free;}
    Disk disk = new Disk();
    RAM ram = new RAM();

    // map method that performs the tokenizer job and framing the initial key value pairs
    // MAP< ServiceName , CPU >
    public void map(LongWritable key, Text value, OutputCollector<Text, FloatWritable> output, Reporter reporter) throws IOException
    {

        JSONParser parser = new JSONParser();
        String line = value.toString();
        String[] messages = line.split("}}") ;
        Object obj = null;
        JSONObject jsonObject = null;
        try {
            for(String s : messages) {
                s = s + "}}";
                obj = parser.parse(s);
                jsonObject = (JSONObject) obj;
                ServiceName = (String) jsonObject.get("serviceName");

                // mean CPU utilization
                word.set(ServiceName + "_CPU");
                Cpu = (FloatWritable) jsonObject.get("CPU");
                output.collect(word, Cpu);
                // mean Disk utilization
                word.set(ServiceName + "_Disk");
                disk = (Disk) jsonObject.get("Disk");
                output.collect(word, new FloatWritable(disk.Free / disk.Total));
                // mean RAM utilization
                word.set(ServiceName + "_RAM");
                ram = (RAM) jsonObject.get("RAM");
                output.collect(word, new FloatWritable(ram.Free / ram.Total));

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
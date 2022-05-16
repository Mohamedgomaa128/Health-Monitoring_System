package Hadoop.Map_Reduce;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class Reducers {

    public static class avg_Reducer extends Reducer<Text,FloatWritable,Text,FloatWritable> {
        // reduce method accepts the Key Value pairs from mappers, do the aggregation based on keys and produce the final out put
        public void reduce(Text key, Iterator<FloatWritable> values, OutputCollector<Text, FloatWritable> output, Reporter reporter) throws IOException {
            int count = 0; //number of services
            float sum = 0;

        /* iterates through all the values available with a key and add them together and give the
           final result as the key and sum of its values*/
            while (values.hasNext()) {
                sum += values.next().get();
                count++;
            }
            output.collect(key, new FloatWritable(sum / count));

        }
    }

    public static class counter_Reducer extends Reducer<Text,FloatWritable,Text,FloatWritable> {
        // reduce method accepts the Key Value pairs from mappers, do the aggregation based on keys and produce the final out put
        public void reduce(Text key, Iterator<FloatWritable> values, OutputCollector<Text, FloatWritable> output, Reporter reporter) throws IOException {
            int sum = 0;

        /* iterates through all the values available with a key and add them together and give the
           final result as the key and sum of its values*/
            while (values.hasNext()) {
                sum += values.next().get();
            }
            output.collect(key, new FloatWritable(sum));

        }
    }

}

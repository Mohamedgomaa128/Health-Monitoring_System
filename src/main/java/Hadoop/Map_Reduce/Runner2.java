package Hadoop.Map_Reduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Runner2 extends Configured implements Tool {

    public int run(String[] args) throws Exception {
        //creating a JobConf object and assigning a job name for identification purposes
        JobConf conf = new JobConf(getConf(), Runner2.class);
        conf.setJobName("WordCount");
        //Setting configuration object with the Data Type of output Key and Value
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(FloatWritable.class);
        //Providing the mapper and reducer class names
        conf.setMapperClass((Class<? extends Mapper>) Count_Mapper.class);
        conf.setReducerClass((Class<? extends Reducer>) Reducers.counter_Reducer.class);
        //the hdfs input and output directory to be fetched from the command line
        FileInputFormat.addInputPath(Job.getInstance(conf), new Path(args[0]));
        FileOutputFormat.setOutputPath(Job.getInstance(conf), new Path(args[1]));
        JobClient.runJob(conf);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new Runner2(), args);
        System.exit(res);
    }

}

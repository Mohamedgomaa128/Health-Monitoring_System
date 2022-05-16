package Hadoop.Map_Reduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Runner {
    public static void run_avg() throws Exception {

        Configuration conf = new Configuration();

        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        Path coreSite = new Path("/usr/local/hadoop/etc/hadoop/core-site.xml");
        Path hdfsSite = new Path("/usr/local/hadoop/etc/hadoop/hdfs-site.xml");
        conf.addResource(coreSite);
        conf.addResource(hdfsSite);

        Job job = Job.getInstance(conf, "Avg");
        job.setJarByClass(Runner.class);
        job.setMapperClass(Avg_Mapper.class);
        job.setReducerClass(Reducers.avg_Reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FloatWritable.class);
        FileInputFormat.addInputPath(job, new Path("/"));
        FileOutputFormat.setOutputPath(job, new Path("/output"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    public static void run_count() throws Exception {

        Configuration conf = new Configuration();

        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        Path coreSite = new Path("/usr/local/hadoop/etc/hadoop/core-site.xml");
        Path hdfsSite = new Path("/usr/local/hadoop/etc/hadoop/hdfs-site.xml");
        conf.addResource(coreSite);
        conf.addResource(hdfsSite);

        Job job = Job.getInstance(conf, "Count");
        job.setJarByClass(Runner.class);
        job.setMapperClass(Count_Mapper.class);
        job.setReducerClass(Reducers.counter_Reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FloatWritable.class);
        FileInputFormat.addInputPath(job, new Path("/"));
        FileOutputFormat.setOutputPath(job, new Path("/output2"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    public static void main(String[] args) throws Exception {
        //run_avg();
        run_count();
    }

}

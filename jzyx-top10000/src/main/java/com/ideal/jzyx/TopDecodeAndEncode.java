package com.ideal.jzyx;

import org.apache.commons.io.LineIterator;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import com.ideal.jzyx.EnDecode;
/**
 * Created by Administrator on 2015/11/27.
 */
public class TopDecodeAndEncode extends Configured implements Tool {
    public static class MyMap extends Mapper<LongWritable, Text, Text, Text> {
        private Text textOfAd = new Text();
        private Text textOfTag = new Text();
        private Map<String,String> map=new HashMap<String, String>();
        private EnDecode de1;
        @Override
        protected void setup(Context context) throws IOException {
            Configuration conf= context.getConfiguration();
            String pathOfCompare=conf.get("mingmi");
            String keygen=conf.get("keygen");
            try {
                de1=new EnDecode(keygen);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            System.out.println("========================================================================================================path of mm "+pathOfCompare);
            FileSystem fs=FileSystem.get(conf);
           FileStatus[] fileStatuses=fs.listStatus(new Path(pathOfCompare), new PathFilter() {
               public boolean accept(Path path) {
                   if(path == null ||path.getName().equals("_SUCCESS") ){
                        return false;
                   }else{
                       return true;
                   }
               }
           });
            if(fileStatuses!=null&&fileStatuses.length!=0) {
                for (FileStatus fileStatus : fileStatuses) {
                    Path p = fileStatus.getPath();
                    if(p==null)
                        continue;
                    System.out.println("processFile:"+p.toString());
                    FSDataInputStream in=null;
                    try{
                        in=fs.open(p);
                        processFile(in,map);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        IOUtils.closeStream(in);
                    }

                }
            }
        }
        public static void processFile(InputStream in,Map<String,String> map) throws IOException {
            if(in==null||map==null)
                return;
            for(LineIterator it= org.apache.commons.io.IOUtils.lineIterator(in,"utf-8");it.hasNext();){
                String s=it.next();
                String[] arr=s.split("\t");
                if(arr==null||arr.length<4){
                    continue;
                }
                String ming=arr[0];
                String mi=arr[3];
                map.put(mi,ming);
            }
        }
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            Configuration conf=context.getConfiguration();
            String keygen=conf.get("keygen");
            int no = 10000;
            String mes = value.toString();
            String[] info = mes.split("\t");
            //��ȡtag
            String tag = info[0];
            //��ȡ����ad:Ȩ��ֵ
            String ad = info[1];
            //��ȡÿ���û�ad��Ȩ��
            String[] ads = ad.split(",");

            int len = ads.length - 1;
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < no; i++) {
                if (i > len) {
                    break;
                }
                Ad adObject=Ad.parseAd(ads[i]);
                if(adObject==null)
                    continue;
                String adOfAd=adObject.getAd();
                String mingAd=map.get(adOfAd);
                if(mingAd==null)
                    continue;
                //加密算法
                String encode=null;
                String decode=null;
                try {
                    encode=de1.Encrytor(mingAd);
                    decode=de1.Decryptor(encode);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                adObject.setAd(encode);
                buffer.append(adObject.toString());
            }
            if(buffer.length()==0)
                return ;
                int index = buffer.lastIndexOf(",");
                buffer.delete(index, index + 1);

            textOfTag.set(tag);
            textOfAd.set(buffer.toString());
            context.write(textOfTag, textOfAd);
        }
    }
    public static class MyReduce extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for(Text value:values){
                context.write(key,value);
            }
        }
    }
    public int run(String[] args) throws Exception {
        Configuration conf=getConf();
        conf.set("mingmi",args[2]);
        conf.set("keygen",args[3]);
        System.out.println("================================================================================================");
        System.out.println(args[2]);
        System.out.println(args[3]);
        Job job=Job.getInstance(conf);

        job.setJarByClass(TopDecodeAndEncode.class);
        job.setJobName("TopDecodeAndEncode");

        job.setMapperClass(MyMap.class);
        job.setReducerClass(MyReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));


        boolean success=job.waitForCompletion(true);
        return success?0:1;
    }

    public static void main(String[] args) throws Exception {
        int a=ToolRunner.run(new TopDecodeAndEncode(),args);
        System.exit(a);
    }

}

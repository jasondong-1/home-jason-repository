package com.ideal.formatTransfer;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2015/9/23.
 */
public class DataTransfer extends Configured implements Tool{
    public static class MyMap extends Mapper<LongWritable,Text,Text,Text>{
        private Map<String,String> map=new HashMap<String, String>();
        private Text tagKey=new Text();
        private Text adValue=new Text();
        /**
         * ������������ڼ��
         * @param key
         * @param value
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //��valueת��Ϊ�ַ���
            String info=value.toString().trim();//022AB97C0905EBC3C7072CE6A610A36F        IT,IT��Դ:1;������Ѷ,������Ѷ:24;
            //������оͽ�������
            if(StringUtils.isEmpty(info)){
                return;
            }
            //����\tΪ�ָ����ָ�info���ָ�Ϊad�ͱ�ǩȨ��ֵ
            String[] splitInfo=info.split("\t");
            //��ȡad
            String ad=splitInfo[0];//022AB97C0905EBC3C7072CE6A610A36F
            //��ȡ��ǩ��Ϣ
            String tagMes=splitInfo[1];//IT,IT��Դ:1;������Ѷ,������Ѷ:24
            //meid
            String meid;
            if(splitInfo.length==3){
                meid=splitInfo[2];
                if("\\N".equals(meid)){
                    meid="";
                }
            }else{
                meid="";
            }
            //ad=ad+"+"+meid;
            ad="".equals(meid)?ad:ad+"+"+meid;
            ad=ad.replaceAll(",","");
            //��ȡÿ����ǩ����Ȩ�ص�����
            String[] allTagMes=tagMes.split(";");//["IT,IT��Դ:1","������Ѷ,������Ѷ:24"];
            //����allTagMes,��
            handleAllTagMes(allTagMes);
            Set<String> sets=map.keySet();
            for(String set:sets){
                String no=map.get(set);
                String adNo=ad+":"+no+",";
                tagKey.set(set);
                adValue.set(adNo);
                context.write(tagKey, adValue);
                //context.write(new Text(set),new Text(adNo));
            }
            map.clear();
         }

        /**
         * ���ڴ���allTagMes
         * 1���Ա�ǩ����Ϊ���ݶԱ�ǩ���з��鴦��
         * 2���Ա�ǩΪkey��Ȩ��Ϊvalue�������ݴ���mao
         * ����������["IT,IT��Դ:1","������Ѷ,������Ѷ:24"]
         * @param allTagMes
         */
        public void handleAllTagMes(String[] allTagMes){
            for(String everyTagMes:allTagMes){
                //IT,IT��Դ:1
                String[] tagAndQuantity=everyTagMes.split(":");
                //��ȡ������ǩ
                if(tagAndQuantity.length!=2){
                    continue;
                }
                String tags=tagAndQuantity[0];//IT,IT��Դ
                //��ȡȨ��
                String quantity=tagAndQuantity[1];//1
                //��ȡÿһ����ǩ
                String[] everyTag=tags.split(",");
                if(everyTag==null||everyTag.length==0){
                    continue;
                }
                //
                StringBuilder buffer=new StringBuilder();
                for(int i=0;i<everyTag.length;i++){
                    if(i<(everyTag.length-1)){
                        buffer.append(everyTag[i]+",");
                    }else{
                        buffer.append(everyTag[i]);
                    }
                }
                //���ձ�ǩ���𽫱�ǩ����洢��map-
                for(int i=0;i<everyTag.length;i++){
                    String mes=buffer.toString();
                    String no=map.get(mes);
                    if(no==null){
                        map.put(mes,quantity);
                    }else{
                        double d=Double.parseDouble(no);
                        double d2=Double.parseDouble(quantity);
                        double sum=d+d2;
                        String total=String.valueOf(sum);
                        map.remove(buffer);
                        map.put(mes,total);
                    }
                    int index=buffer.lastIndexOf(",");
                    if(index>0)
                        buffer.delete(index,buffer.length());
                }
                buffer=null;
            }

        }
    }
    public static class MyReduce extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        	List<String> list=new ArrayList<String>();
            StringBuilder buffer=new StringBuilder();
//            for(Text val:values){
//                buffer.append(val.toString());
//            }
        	for(Text val:values){
        		String s=val.toString();
        		list.add(s);
        	}
        	System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        	Collections.sort(list, new Comparator<String>() {

        		public int compare(String o1, String o2) {
        			String s1=o1.trim();
        			String s2=o2.trim();
        			if("".equals(s1)&&!"".equals(s2)){
        				return 1;
        			}
        			if("".equals(s2)&&!"".equals(s1)){
        				return -1;
        			}
        			if("".equals(s1)&&"".equals(s2)){
        				return 0;
        			}
        			int i10=o1.lastIndexOf(":");
        			int i11=o1.lastIndexOf(",");
        			String o1no=o1.substring(i10+1,i11);
        			double d1=Double.parseDouble(o1no);
        			
        			int i20=o2.lastIndexOf(":");
        			int i21=o2.lastIndexOf(",");
        			String o2no=o2.substring(i20+1,i21);
        			double d2=Double.parseDouble(o2no);
//        			if((d1-d2)>=0){
//        				return -1;
//        			}else{
//        				return 1;
//        			}
        			if(d1>d2){
        				return -1;
        			}
        			if(d1==d2){
        				return 0;
        			}else{
        				return 1;
        			}

        		}
			});
        	for(String s:list){
        		buffer.append(s);
        	}
            int i=buffer.lastIndexOf(",");
            buffer.delete(i,i+1);
            context.write(key,new Text(buffer.toString()));
            buffer=null;
            list=null;
        }
    }

    public int run(String[] args) throws Exception {
        Job job=new Job(getConf());
        job.setJarByClass(DataTransfer.class);
        job.setJobName("dataTransfer");

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setMapperClass(MyMap.class);
        job.setReducerClass(MyReduce.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean success=job.waitForCompletion(true);
        return success? 0:1;
    }

    public static void main(String[] args) throws Exception {
        int a= ToolRunner.run(new DataTransfer(), args);
        System.exit(a);

    }
}



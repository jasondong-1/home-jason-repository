package com.ideal.jzyx.decode;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/29.
 */
public class DecodeMain extends Configured {

    private static void checkArgs(String[] args) {
        if(args == null || args.length != 3) {
            System.err.println("Error args's number! Format:[（本地）输入路径] [（本地）输出路径] [（hdfs）对照表路径]");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        checkArgs(args);

        Configuration config = new Configuration();
        Decoder decoder = new Decoder(new File(args[0]), new File(args[1]), args[2], config, new Mingmi());

        try {
            decoder.init();
            decoder.decode();
        } catch (LoadMemoryException e){
            e.printStackTrace();
            System.exit(-1);
        } catch (DecodeException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }
}

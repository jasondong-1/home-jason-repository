package com.ideal.jzyx.decode;


import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 获取密文明文map<mi,ming>
 * Created by Administrator on 2015/10/29.
 */
public class Mingmi {
    private Map<String, String> maps;

    public Mingmi() {
        this.maps = new HashMap<String, String>();
    }

    public void processOneFile(Map<String, String> maps, InputStream in) throws IOException {
        int total = 0;

        for (LineIterator it = IOUtils.lineIterator(in, "utf-8"); it.hasNext(); ) {
            String line = it.next();
            String[] parts = line.split("\t");
            //解密字段待定
            if (parts.length < 6 || StringUtils.isBlank(parts[0]) || StringUtils.isBlank(parts[3])) {
                continue;
            }
            total++;
            maps.put(parts[3].trim(), parts[0].trim());
        }
        System.out.println("total:" + total);
    }

    public Map<String, String> process(Configuration config, String path) throws LoadMemoryException {
        try {
            FileSystem fs = FileSystem.get(config);
            FileStatus[] fileStatuses = fs.listStatus(new Path(path), new PathFilter() {
                public boolean accept(Path path) {
                    if (path == null || path.getName().equals("_SUCCESS")) {
                        return false;
                    } else {
                        return true;
                    }
                }
            });
            if (fileStatuses != null && fileStatuses.length != 0) {
                for (FileStatus fileStatus : fileStatuses) {
                    String filePath = fileStatus.getPath().toUri().getPath();
                    System.out.print("process file:" + filePath);
                    InputStream in = null;
                    try {
                        in = fs.open(fileStatus.getPath());
                        processOneFile(maps, in);
                    } catch (IOException e) {
                        throw new LoadMemoryException("Failed to process file:" + filePath, e);
                    } finally {
                        IOUtils.closeQuietly(in);
                    }
                }
            }
        } catch (IOException e) {
            throw new LoadMemoryException("Failed to load mingmi into the memory.\n" + e.getMessage(), e);
        }
        return maps;
    }

    public Map<String, String> getMaps() {
        return maps;
    }

    //    public static void main(String[] args) throws IOException {
//        Mingmi gg = new Mingmi();
//        Map<String, String> maps = gg.process(new Configuration(), args[0]);
//
//        Set<Entry<String, String>> sets = maps.entrySet();
//        for(Entry entry : sets) {
//            System.out.println(entry.getKey() + "||" + entry.getValue());
//        }
//
//    }
}

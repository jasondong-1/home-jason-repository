package com.ideal.jzyx.decode;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/30.
 */
public class Decoder {
    private File inFile;
    private File outFile;
    private String mingmiPath;
    private Configuration config;
    private Mingmi mingmi;

    public Decoder(File inFile, File outFile, String mingmiPath, Configuration config, Mingmi mingmi) {
        this.inFile = inFile;
        this.outFile = outFile;
        this.mingmiPath = mingmiPath;
        this.config = config;
        this.mingmi = mingmi;
    }

    public void init() throws LoadMemoryException {
        if(mingmi == null || StringUtils.isBlank(mingmiPath)) {
            throw new LoadMemoryException("Cannot find the appropriate mingmi(Object) or mingmiPath(String)");
        }
        mingmi.process(config, mingmiPath);
    }

    public void decode() throws DecodeException {
        FileInputStream in = null;
        FileOutputStream out = null;
        List<String> newLines = Lists.newArrayList();
        try {
            in = new FileInputStream(inFile);
            out = new FileOutputStream(outFile);
            Map<String, String> maps = mingmi.getMaps();
            if (maps == null || maps.isEmpty()) {
                throw new IOException("Cannot load valid mingm information into the memory.");
            }

            for (LineIterator it = IOUtils.lineIterator(in, "utf-8"); it.hasNext(); ) {
                StringBuilder newLine = new StringBuilder();
                //从加密文件读取一行数据
                String line = it.next();
                //按照 \t 将每行分为两个字段，
                String[] parts = line.split("\t", -1);
                if (parts == null || parts.length != 2) {
                    continue;
                }

                newLine.append(parts[0]  + "\t");

                String ads = parts[1];
                String[] arrAds = ads.split(",", -1);//ad:xx ad:xx

                if (arrAds == null || arrAds.length == 0) {
                    continue;
                }
                for (int i = 0; i < arrAds.length; i++) {
                    //获取ad和权重，单个用户
                    String adWeight = arrAds[i];
                    //获取密文和meid号
                    String CiphertextAndMeid = adWeight.split(":", -1)[0];
                    //获取ad和meid
                    String[] adAndMeid=CiphertextAndMeid.split("\\+",-1);
                    //获取密文
                    String Ciphertext=adAndMeid[0];
                    // 获取Meid
                    String meids="";
                    String sign="";
                    if(adAndMeid.length==2){
                        meids=CiphertextAndMeid.split("\\+",-1)[1];
                        sign="+";
                    }


                    //将密文转换为明文
                    //String plaintext = maps.get(Ciphertext)==null?Ciphertext:maps.get(Ciphertext);
                    String plaintext=maps.get(Ciphertext);
                    if(StringUtils.isEmpty(plaintext))
                        continue;

                    newLine.append(StringUtils.trimToEmpty(plaintext)+sign+meids + ":" + adWeight.split(":")[1] + ",");

                }
                newLine.deleteCharAt(newLine.length()-1);
                newLines.add(newLine.toString());
                newLine=null;
            }
            IOUtils.writeLines(newLines, String.valueOf(CharUtils.LF), out, "utf-8");
            out.flush();
        } catch (IOException e) {
            throw new DecodeException("Failed to decode the data!", e);
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }

    }

}

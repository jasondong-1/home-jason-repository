package com.ideal.jzyx;

/**
 * Created by Administrator on 2015/11/27.
 */
public class Ad {
    private String ad;
    private String meid;
    private String no;
    private String adMeidsplitSign="_AM_";
    public Ad() {
    }

    public Ad(String ad, String meid, String no) {
        this.ad = ad;
        this.meid = meid;
        this.no = no;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getMeid() {
        return meid;
    }

    public void setMeid(String meid) {
        this.meid = meid;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public String toString() {
        meid=meid.trim();
        if("".equals(meid)){
            return ad+":"+no+",";
        }else {
            return ad+adMeidsplitSign+meid+":"+no+",";
        }
    }
    public static Ad parseAd(String s){
        if(s==null||s.trim().length()==0||s.indexOf(":")==-1){
            return null;
        }
        String[] arr=s.split(":");
        String adAndMeid=arr[0];
        String no=arr[1];
        String[] arradmeid=adAndMeid.split("\\+");
        int len=arradmeid.length;
        String ad=arradmeid[0];
        String meid="";
        if(len==2){
            meid=arradmeid[1];
        }
        return new Ad(ad,meid,no);

    }

}

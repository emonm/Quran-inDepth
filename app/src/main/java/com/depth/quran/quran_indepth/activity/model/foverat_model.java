package com.depth.quran.quran_indepth.activity.model;

/**
 * Created by DAFFODIL on 8/24/2017.
 */

public class foverat_model {
    String ayeat ;
    String  arabic_details;
//    String  bangla_details;
//    String  english_details;
    private String chapter_id;
//    private String chapter_bangla;
//    private String chapter_english;
    private String chapter_arabic;
//    private String mideafile;
//    private String node;


    public String getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
    }


    public String getChapter_arabic() {
        return chapter_arabic;
    }

    public void setChapter_arabic(String chapter_arabic) {
        this.chapter_arabic = chapter_arabic;
    }

    public String getAyeat() {
        return ayeat;
    }

    public void setAyeat(String ayeat) {
        this.ayeat = ayeat;
    }

    public String getArabic_details() {
        return arabic_details;
    }

    public void setArabic_details(String arabic_details) {
        this.arabic_details = arabic_details;
    }

}


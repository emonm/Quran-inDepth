package com.depth.quran.quran_indepth.activity.model;

/**
 * Created by admin on 23/03/2017.
 */

public class ChapterListModel {

    private String chapter_id;
    private String chapter_english;
    private String chapter_arabic;
    private String Revelation_Number;
    private String Ruku_Count;
    private String Verses;
    private String Paras;
    private String Muqattaat;
    private String Cum_Verses;
    private int id;
    private String SajdaVerses;
    private String Mtid;


    public ChapterListModel(int id,String chapter_english){
        this.id=id;
        this.chapter_english=chapter_english;
    }
    public ChapterListModel(){

    }
    public String getMtid() {
        return Mtid;
    }

    public void setMtid(String mtid) {
        Mtid = mtid;
    }

    private String chapter_leater;
    private String chapter_leater_id;

    private String chapter_leater_arbi;
    private String chapter_leater_enhlish;

    public String getChapter_leater() {
        return chapter_leater;
    }

    public void setChapter_leater(String chapter_leater) {
        this.chapter_leater = chapter_leater;
    }

    public String getChapter_leater_id() {
        return chapter_leater_id;
    }

    public void setChapter_leater_id(String chapter_leater_id) {
        this.chapter_leater_id = chapter_leater_id;
    }

    public String getChapter_leater_arbi() {
        return chapter_leater_arbi;
    }

    public void setChapter_leater_arbi(String chapter_leater_arbi) {
        this.chapter_leater_arbi = chapter_leater_arbi;
    }

    public String getChapter_leater_enhlish() {
        return chapter_leater_enhlish;
    }

    public void setChapter_leater_enhlish(String chapter_leater_enhlish) {
        this.chapter_leater_enhlish = chapter_leater_enhlish;
    }

    public String getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getChapter_english() {
        return chapter_english;
    }

    public void setChapter_english(String chapter_english) {
        this.chapter_english = chapter_english;
    }

    public String getChapter_arabic() {
        return chapter_arabic;
    }

    public void setChapter_arabic(String chapter_arabic) {
        this.chapter_arabic = chapter_arabic;
    }
    public String getRevelation_Number() {
        return Revelation_Number;
    }

    public void setRevelation_Number(String revelation_Number) {
        Revelation_Number = revelation_Number;
    }

    public String getRuku_Count() {
        return Ruku_Count;
    }

    public void setRuku_Count(String ruku_Count) {
        Ruku_Count = ruku_Count;
    }
    public String getVerses() {
        return Verses;
    }

    public void setVerses(String verses) {
        Verses = verses;
    }

    public String getParas() {
        return Paras;
    }

    public void setParas(String paras) {
        Paras = paras;
    }

    public String getMuqattaat() {
        return Muqattaat;
    }

    public void setMuqattaat(String muqattaat) {
        Muqattaat = muqattaat;
    }

    public String getCum_Verses() {
        return Cum_Verses;
    }

    public void setCum_Verses(String cum_Verses) {
        Cum_Verses = cum_Verses;
    }

    public String getSajdaVerses() {
        return SajdaVerses;
    }

    public void setSajdaVerses(String sajdaVerses) {
        SajdaVerses = sajdaVerses;
    }

}

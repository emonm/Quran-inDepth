package com.depth.quran.quran_indepth.activity.model;

/**
 * Created by DAFFODIL on 7/25/2017.
 */

public class QuranListModel {

    String VerseId;
    String ChapSerialNumber;
    String VerseAr;
    String VerseEn;
    private String chapter_id;
    private String chapter_english;
    private String chapter_arabic;
    private String Revelation_Number;
    private String Ruku_Count;
    private String Verses;
    private String Paras;
    private String Muqattaat;
    private String Cum_Verses;
    private String SajdaVerses;

    public String getVerseId() {
        return VerseId;
    }

    public void setVerseId(String verseId) {
        VerseId = verseId;
    }
    public String getChapSerialNumber() {
        return ChapSerialNumber;
    }

    public void setChapSerialNumber(String chapSerialNumber) {
        ChapSerialNumber = chapSerialNumber;
    }

    public String getVerseAr() {
        return VerseAr;
    }

    public void setVerseAr(String verseAr) {
        VerseAr = verseAr;
    }

    public String getVerseEn() {
        return VerseEn;
    }

    public void setVerseEn(String verseEn) {
        VerseEn = verseEn;
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

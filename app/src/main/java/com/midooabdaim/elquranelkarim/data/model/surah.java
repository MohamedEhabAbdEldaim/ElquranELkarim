package com.midooabdaim.elquranelkarim.data.model;

public class surah {

    int index;
    String name;
    String besmallah;
    String content;

    public surah() {
    }

    public surah(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public surah(int index, String name, String besmallah, String content) {
        this.index = index;
        this.name = name;
        this.besmallah = besmallah;
        this.content = content;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBesmallah() {
        return besmallah;
    }

    public void setBesmallah(String besmallah) {
        this.besmallah = besmallah;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

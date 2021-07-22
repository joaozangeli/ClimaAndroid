package br.com.egidioo.projetoclimaandroid.models;

import com.orm.SugarRecord;

public class Clima extends SugarRecord{
    public Clima results;

    private int temp;

    public Clima(){

    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}

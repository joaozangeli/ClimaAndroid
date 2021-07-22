package br.com.egidioo.projetoclimaandroid.models;

import com.orm.SugarRecord;

public class Clima extends SugarRecord{
    private String by;
    private boolean valid_key;
    private Results results;
    private double execution_time;
    private boolean from_cache;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public boolean isValid_key() {
        return valid_key;
    }

    public void setValid_key(boolean valid_key) {
        this.valid_key = valid_key;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public double getExecution_time() {
        return execution_time;
    }

    public void setExecution_time(double execution_time) {
        this.execution_time = execution_time;
    }

    public boolean isFrom_cache() {
        return from_cache;
    }

    public void setFrom_cache(boolean from_cache) {
        this.from_cache = from_cache;
    }
}

package com.xmenms.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StatsResponse {

    public StatsResponse(){
        this.countHumanDna = 0;
        this.countMutatnDna = 0;
        this.ratio = 0f;
    }

    private int countMutatnDna;

    private int countHumanDna;

    private double ratio;

    public int getCountMutatnDna() { return countMutatnDna; }

    public void setCountMutatnDna(int countMutatnDna) {
        this.countMutatnDna = countMutatnDna;
    }

    public int getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(int countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) { this.ratio = ratio; }
}

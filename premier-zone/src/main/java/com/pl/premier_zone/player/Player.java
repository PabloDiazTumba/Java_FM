package com.pl.premier_zone.player;

import jakarta.persistence.*;

@Entity
@Table(name = "player_statistic")
public class Player {
    @Id
    @Column(name = "player_name", unique = true) // Se till att kolumnnamnet matchar databasen
    private String name;

    private String nation;
    @Column(name = "position")
    private String pos;
    private Integer age;

    @Column(name = "matches_played") // matcha databaskolumnnamnet om det inte är korrekt
    private Integer mp = 0; // Sätt ett standardvärde här

    private Integer starts;
    private Double min;
    private Double gls;
    private Double ast;
    private Double pk;
    private Double crdy;
    private Double crdr;
    private Double xg;
    private Double xag;

    @Column(name = "team_name") // matcha med databasens kolumnnamn
    private String team;

    // Konstruktorer och getters/setters
    public Player() {
    }

    public Player(String name, String nation, String pos, Integer age, Integer mp, Integer starts, Double min,
                  Double gls, Double ast, Double pk, Double crdy, Double crdr, Double xg, Double xag, String team) {
        this.name = name;
        this.nation = nation;
        this.pos = pos;
        this.age = age;
        this.mp = mp;
        this.starts = starts;
        this.min = min;
        this.gls = gls;
        this.ast = ast;
        this.pk = pk;
        this.crdy = crdy;
        this.crdr = crdr;
        this.xg = xg;
        this.xag = xag;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public int getAge() {
        return age != null ? age : 0;  // Om age är null, sätt 0 som standard
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getStarts() {
        return starts;
    }

    public void setStarts(int starts) {
        this.starts = starts;
    }

    public double getMin() {
        return min != null ? min : 0.0;  // Om min är null, sätt 0 som standard
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getGls() {
        return gls != null ? gls : 0.0;
    }

    public void setGls(double gls) {
        this.gls = gls;
    }

    public double getAst() {
        return ast != null ? ast : 0.0;
    }

    public void setAst(double ast) {
        this.ast = ast;
    }

    public double getPk() {
        return pk != null ? pk : 0.0;
    }

    public void setPk(double pk) {
        this.pk = pk;
    }

    public double getCrdy() {
        return crdy != null ? crdy : 0.0;
    }

    public void setCrdy(double crdy) {
        this.crdy = crdy;
    }

    public double getCrdr() {
        return crdr != null ? crdr : 0.0;
    }

    public void setCrdr(double crdr) {
        this.crdr = crdr;
    }

    public double getXg() {
        return xg != null ? xg : 0.0;
    }

    public void setXg(double xg) {
        this.xg = xg;
    }

    public double getXag() {
        return xag != null ? xag : 0.0;
    }

    public void setXag(double xag) {
        this.xag = xag;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", nation='" + nation + '\'' +
                ", pos='" + pos + '\'' +
                ", age=" + age +
                ", mp=" + mp +
                ", starts=" + starts +
                ", min=" + min +
                ", gls=" + gls +
                ", ast=" + ast +
                ", pk=" + pk +
                ", crdy=" + crdy +
                ", crdr=" + crdr +
                ", xg=" + xg +
                ", xag=" + xag +
                ", team='" + team + '\'' +
                '}';
    }
}
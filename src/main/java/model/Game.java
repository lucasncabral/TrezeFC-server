package model;

public class Game {
    private String home, visit, time, date, local;
    private Integer id;

    public Game(Integer id, String home, String visit, String time, String date, String local){
        this.id = id;
        this.home = home;
        this.visit = visit;
        this.time = time;
        this.date = date;
        this.local = local;
    }

    public Integer getId() {
        return id;
    }

    public String getHome() {
        return home;
    }

    public String getVisit() {
        return visit;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getLocal() {
        return local;
    }
}
package model;

/**
 * Created by Lucas on 05/09/2016.
 */
public class Marker {
    private int id;
    private String name;
    private String descricao;
    private String photo;
    private double lat;
    private double log;
    private double distance;
    private double evaluation = 0.0;
    private String category;
    private String status;

    public Marker(int id, String type, String name, String info1, String info2, double lat, double log, double distance, double evaluation, String status){
        this.id = id;
        this.name = name;
        this.category = type;
        this.descricao = info1;
        this.photo = info2;
        this.lat = lat;
        this.log = log;
        this.distance = distance;
        this.evaluation = evaluation;
        this.status = status;
    }

    public Marker(double latitude, double longitude){
        this.name = "Name";
        this.category = "Category";
        this.descricao = "Descricao";
        this.photo = "Photo";
        this.evaluation = 0;
        this.lat = latitude;
        this.log = longitude;
    }


    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getLat() {
        return lat;
    }

    public double getLog() {
        return log;
    }

    public int getId() {
        return id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }
}

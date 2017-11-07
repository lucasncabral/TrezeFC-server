package model;

public class Player{
    public String name, photo, position, status;
    public int age, gols, yellowCards,jogos, minutesPlay;

    public Player(String name, String photo, String position, String status, int age){
        this.name = name;
        this.photo = photo;
        this.position = position;
        this.status = status;
        this.age = age;
    }

    @Override
    public String toString() {
        return this.getName() + " - " + this.getJogos() + "  " +this.getGols() + "  " +this.getMinutesPlay() + "  " +this.getYellowCards();
    }

    public void setGols(int gols) {
        this.gols = gols;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setJogos(int jogos) {
        this.jogos = jogos;
    }

    public void setMinutesPlay(int minutesPlay) {
        this.minutesPlay = minutesPlay;
    }

    public Player(String name, String link){
        this.name = name;
        this.photo = link;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getJogos() {
        return jogos;
    }

    public Player(String name, String photo, String position, String status, int age, int gols, int yellowCards, int jogos, int minutesPlay){
        this.name = name;
        this.photo = photo;
        this.position = position;
        this.jogos = jogos;
        this.status = status;
        this.age = age;
        this.gols = gols;
        this.yellowCards = yellowCards;
        this.minutesPlay = minutesPlay;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public int getGols() {
        return gols;
    }

    public int getMinutesPlay() {
        return minutesPlay;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public String getStatus() {
        return status;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}


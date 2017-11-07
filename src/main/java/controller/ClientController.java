package controller;

import dao.ClientDAO;
import model.Game;
import model.Notice;
import model.Player;
import model.Team;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
public class ClientController {

    private static final int TAM_MAX_BUFFER = 10240;

    public boolean createTable(){
        return ClientDAO.getInstance().createTable();
    }

    public boolean deleteTable(){
        return ClientDAO.getInstance().dropTable();
    }

    // TODO TEAM
    public boolean addTeam(String name, String abr, int victorys, int losers, int tie, int sg) {
        try {
            return ClientDAO.getInstance().addTeam(name, abr,victorys, losers,tie, sg);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removePlayers(){
        return ClientDAO.getInstance().addPlayexrS();
    }

    public boolean addPlayer(String name, String position,String image, int cards, int goals, int matchs, int time) {
        try {
            return ClientDAO.getInstance().addPlayer(name,position, image,cards, goals,matchs,time);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Team> getTeams() {
        return ClientDAO.getInstance().getTeams();
    }

    public boolean addGameResult(String teamHome, int goalsHome, int goalsVisit, String teamVisit) {
        return ClientDAO.getInstance().addGameResult(teamHome,goalsHome, goalsVisit, teamVisit);
    }

    public boolean addGame(String teamHome, String teamVisit, String date, String local, String time) {
        return ClientDAO.getInstance().addGame(teamHome, teamVisit, date, local, time);
    }

    public List<Game> getGames() {
        return ClientDAO.getInstance().getGames();
    }

    public boolean editGame(int id, String teamHome, String teamVisit, String date, String local, String time) {
        return ClientDAO.getInstance().editGame(id,teamHome, teamVisit, date, local, time);

    }

    public void updateNotices(List<Notice> notices) {
        ClientDAO.getInstance().updateNotices(notices);
    }

    public List<Notice> getNotices() {
        return ClientDAO.getInstance().getNotices();
    }

    public List<Player> getPlayers() {
        return ClientDAO.getInstance().getPlayers();
    }

    public boolean setToken(String token, String notGoal, String notMatch, String notPena, String notChanges) {
        return ClientDAO.getInstance().editNotifications(token,notGoal,notMatch,notPena,notChanges);
    }

    public boolean sendNotification(String type, String title, String description) {
        List<String> tokens = null;
        switch (type){
            case "gol":
                tokens = ClientDAO.getInstance().getUsersNotification("goalsNotification");
                break;
            case "partida":
                tokens = ClientDAO.getInstance().getUsersNotification("matchNotification");
                break;
            case "penalti":
                tokens = ClientDAO.getInstance().getUsersNotification("penaNotification");
                break;
            case "substituicao":
                tokens = ClientDAO.getInstance().getUsersNotification("changNotification");
                break;
        }
        sendPushNotification(tokens, description,title);
        return true;
    }

    public List<String> getAllTokens(){
        return ClientDAO.getInstance().getTokens();
    }

    public final void sendPushNotification(List<String> tokenGCM, String descricao, String title) {
        FCMService FCM = new FCMService();
        for (String s : tokenGCM){
            try {
                FCM.sendPushNotification(s, title,descricao);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean atualizaClassificacao() {
        return ClientDAO.getInstance().atualizaClassificacao();
    }
}
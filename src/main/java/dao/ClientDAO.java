package dao;

import data.MySQLContract;
import factory.ConnectionFactory;
import model.Game;
import model.Notice;
import model.Player;
import model.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClientDAO extends ConnectionFactory {
    private static ClientDAO instance;
    private static MySQLContract mySQLContract = new MySQLContract();
    private List<Notice> notices;
    private List<Player> players;

    public static ClientDAO getInstance() {
        if (instance == null) {
            instance = new ClientDAO();
            connect();
        }
        return instance;
    }

    public boolean createTable() {
        boolean result = true;
        try {
            /**
            getStatement().executeUpdate(MySQLContract.sql_create_table_Notifications());
            getStatement().executeUpdate(MySQLContract.sql_create_table_Player());
            getStatement().executeUpdate(MySQLContract.sql_create_table_Game());
            getStatement().executeUpdate(MySQLContract.sql_create_table_Notice());
             */
            getStatement().executeUpdate(MySQLContract.sql_create_table_Team());
        } catch (SQLException e) {
            result = false;
            System.out.println("Erro ao criar uma das tabelas");
            e.printStackTrace();
        }
        return result;
    }

    public boolean dropTable() {
        boolean result = true;
        try {
            //getStatement().executeUpdate(MySQLContract.sql_drop_table_game());
            //getStatement().executeUpdate(MySQLContract.sql_drop_table_notice());
            getStatement().executeUpdate(MySQLContract.sql_drop_table_team());
            //getStatement().executeUpdate(MySQLContract.sql_drop_table_notifications());
        } catch (SQLException e) {
            result = false;
            System.out.println("Erro ao dropar uma das tabelas");
            e.printStackTrace();
        }
        return result;
    }

    // TODO TEAM
    public boolean addTeam(String name, String abr, int victorys, int losers, int tie, int sg) {
        boolean result = false;
        try {
            getStatement().executeUpdate(MySQLContract.sql_insert_team(name, abr, victorys, losers, tie, sg));
            result = true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir um time!");
            e.printStackTrace();
        }
        return result;
    }

    public boolean addPlayer(String name, String position,String image, int cards, int goals, int matchs, int time) {
        boolean result = false;
        try {
            getStatement().executeUpdate(MySQLContract.sql_insert_player(name,position, image, goals,cards,matchs,time));
            result = true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir um time!");
            e.printStackTrace();
        }
        return result;
    }

    public List<Team> getTeams() {
        ResultSet resultSet;
        List<Team> result = new ArrayList<>();

        try {
            resultSet = getStatement().executeQuery(MySQLContract.sql_get_teams());
            while (resultSet.next()) {
                int victorys = resultSet.getInt("victorysTeam");
                int ties = resultSet.getInt("tieTeam");
                result.add(new Team(resultSet.getInt("idTeam"),resultSet.getString("nameTeam"), resultSet.getString("abrTeam")
                        ,(victorys * 3) + (ties) ,victorys, ties,resultSet.getInt("losersTeam"),
                        resultSet.getInt("sgTeam")));
            }
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar times");
            e.printStackTrace();
        }
        return result;
    }

    public boolean addGameResult(String teamHome, int goalsHome, int goalsVisit, String teamVisit) {
        boolean result = false;
        try {
            if (goalsHome == goalsVisit) {
                getStatement().executeUpdate(MySQLContract.sql_update_team_tie(teamHome, teamVisit));
            } else if (goalsHome > goalsVisit) {
                getStatement().executeUpdate(MySQLContract.sql_update_team_win(teamHome, (goalsHome - goalsVisit)));
                getStatement().executeUpdate(MySQLContract.sql_update_team_losers(teamVisit, (goalsHome - goalsVisit)));
            } else {
                getStatement().executeUpdate(MySQLContract.sql_update_team_win(teamVisit, (goalsVisit - goalsHome)));
                getStatement().executeUpdate(MySQLContract.sql_update_team_losers(teamHome, (goalsVisit - goalsHome)));
            }
            result = true;
        } catch (Exception e){
            System.out.println("Erro ao atualizar tabela");
            e.printStackTrace();
        }
        return result;
    }

    //  TODO GAME
    public boolean addGame(String teamHome, String teamVisit, String date, String local, String time) {boolean result = false;
        try {
            getStatement().executeUpdate(MySQLContract.sql_insert_game(teamHome, teamVisit, date, local, time));
            result = true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir um jogo!");
            e.printStackTrace();
        }
        return result;
    }


    public void updateNotices(List<Notice> notices) {
        List<Notice> noticesFinal = new ArrayList<>();
        for (Notice n : notices){
            Boolean result = false;
            try {
                n.setDescription(n.getDescription().replace("'", ""));
                ResultSet resultSet = getStatement().executeQuery(MySQLContract.sql_get_notice(n.getDescription()));
                if (resultSet.first()) {
                    if(resultSet.getString("descriptionNotice").equals(n.getDescription()))
                        result = true;
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }

            if(!result) {
                if (n.getDate() == null) {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    n.setDate(dateFormat.format(date));
                }

                if (n.getLinkImage() == null) {
                    n.setLinkImage("");
                }

                noticesFinal.add(n);
            }
        }

        for (Notice n : noticesFinal){
            try {
                System.out.println(n.toString());
                getStatement().executeUpdate(MySQLContract.sql_insert_notice(n.getDescription(), n.getLinkNotice(), n.getPublishedBy(),n.getDate(),n.getLinkImage()));
            } catch (SQLException e) {
                System.out.println("Erro ao inserir uma noticia!");
                e.printStackTrace();
            }

        }
    }

    public List<Game> getGames() {
        ResultSet resultSet;
        List<Game> result = new ArrayList<>();

        try {
            resultSet = getStatement().executeQuery(MySQLContract.sql_get_games());
            while (resultSet.next()) {
                result.add(new Game(resultSet.getInt("idGame"), resultSet.getString("homeGame"), resultSet.getString("visitGame"),
                        resultSet.getString("timeGame"), resultSet.getString("dateGame"), resultSet.getString("localGame")));

            }
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar jogos");
            e.printStackTrace();
        }
        return result;
    }

    public List<Notice> getNotices() {
        ResultSet resultSet;
        List<Notice> result = new ArrayList<>();
        int count = 0;
        try {
            resultSet = getStatement().executeQuery(MySQLContract.sql_get_all_notices());
            while (resultSet.next()) {
                count ++;
                Notice notice = new Notice(resultSet.getInt("idNotice"),resultSet.getString("descriptionNotice")
                        ,resultSet.getString("imageNotice"), resultSet.getString("publishedNotice"),
                        resultSet.getString("linkNotice"), resultSet.getString("dateNotice"));
                result.add(notice);
            }

            // Sorting
            result = mySort(result);
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar noticias");
            e.printStackTrace();
        }
        return result;
    }


    public List<String> getTokens() {
        ResultSet resultSet;
        List<String> result = new ArrayList<>();
        int count = 0;
        try {
            resultSet = getStatement().executeQuery(MySQLContract.sql_get_all_tokens());
            while (resultSet.next()) {
                count ++;
                result.add(resultSet.getString("tokenNotification"));
            }
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar tokens");
            e.printStackTrace();
        }
        return result;
    }


    private List<Notice> mySort(List<Notice> resultLi) {
        Collections.sort(resultLi, new Comparator<Notice>() {
            @Override
            public int compare(Notice n1, Notice n2)
            {
                String date1 = n1.getDate();
                String date2 = n2.getDate();

                int day1 = Integer.parseInt(date1.substring(0,2));
                int month1 = Integer.parseInt(date1.substring(3,5));
                int year1 = Integer.parseInt(date1.substring(6,10));

                int day2 = Integer.parseInt(date2.substring(0,2));
                int month2 = Integer.parseInt(date2.substring(3,5));
                int year2 = Integer.parseInt(date2.substring(6,10));

                int result = 0;
                if(year1 > year2) {
                    result = -1;
                } else if (year1 < year2){
                    result = 1;
                } else {
                    if(month1 > month2) {
                        result = -1;
                    } else if (month1 < month2)
                        result = 1;
                    else {
                        if(day1 > day2) {
                            result = -1;
                        } else if (day1 < day2)
                            result = 1;
                        else {
                            if(n1.getPublishedBy().equals(n2.getPublishedBy()))
                                result = 0;
                            else if(n1.getPublishedBy().equals("ig") || n1.getPublishedBy().equals("ge"))
                                result = 1;
                            else
                                result = -1;
                        }
                    }
                }
                return result;
            }
        });
        return resultLi.subList(0,30);
    }

    public boolean editGame(int id, String teamHome, String teamVisit, String date, String local, String time) {
        boolean result = false;
        try {
            getStatement().executeUpdate(MySQLContract.sql_update_game(id,teamHome, teamVisit, date, local, time));
            result = true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar um jogo!");
            e.printStackTrace();
        }
        return result;
    }

    public boolean addPlayexrS() {
        boolean result = false;
        try {
            getStatement().executeUpdate(MySQLContract.sql_delete_player());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Player> getPlayers() {
        ResultSet resultSet;
        List<Player> result = new ArrayList<>();
        try {
            resultSet = getStatement().executeQuery(MySQLContract.sql_get_players());
            while (resultSet.next()) {
                Player p = new Player(resultSet.getString("namePlayer"),resultSet.getString("imagePlayer"),resultSet.getString("positionPlayer")
                ,"ok",30,resultSet.getInt("goalsPlayer"),resultSet.getInt("cardsPlayer"),resultSet.getInt("matchsPlayer"),resultSet.getInt("timePlayer"));
                result.add(p);
            }

            // Sorting
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar noticias");
            e.printStackTrace();
        }
        return result;
    }

    public boolean editNotifications(String token, String notGoal, String notMatch, String notPena, String notChanges) {
        boolean result = false;
        try {
            ResultSet resultSet = getStatement().executeQuery(MySQLContract.sql_get_notification(token));
            if (resultSet.next()) {
                if(resultSet.getString("tokenNotification").equals(token)) {
                    getStatement().executeUpdate(MySQLContract.sql_update_notification(token, notGoal, notMatch, notPena, notChanges));
                    result = true;
                }
            } else {
                getStatement().executeQuery(MySQLContract.sql_insert_notification(token,notGoal,notMatch, notPena, notChanges));
                result = true;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public List<String> getUsersNotification(String notificationGoal) {
        List<String> result = new ArrayList<>();

        try {
            ResultSet resultSet = getStatement().executeQuery(MySQLContract.sql_get_notifications(notificationGoal));
            while (resultSet.next()) {
                String victorys = resultSet.getString("tokenNotification");
                result.add(victorys);
            }
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar tokens");
            e.printStackTrace();
        }
        return result;
    }

    public boolean atualizaClassificacao() {
        boolean result = true;
        try {
            getStatement().executeUpdate(MySQLContract.sql_drop_table_team());
            getStatement().executeUpdate(MySQLContract.sql_create_table_Team());
            // TODO TEAMS
            getStatement().executeUpdate(MySQLContract.sql_insert_team("Botafogo-PB", "BOT", 13, 4, 1, 16));
            getStatement().executeUpdate(MySQLContract.sql_insert_team("Campinense", "CAM", 10, 3, 5, 18));
            getStatement().executeUpdate(MySQLContract.sql_insert_team("Treze", "TRZ", 7, 3, 8, 8));
            getStatement().executeUpdate(MySQLContract.sql_insert_team("Atletico-PB", "ATL", 7, 5, 6, 6));
            getStatement().executeUpdate(MySQLContract.sql_insert_team("Serrano-PB", "SER", 6, 8, 4, -7));
            getStatement().executeUpdate(MySQLContract.sql_insert_team("Auto Esporte-PB", "AUT", 7, 7, 4, 2));
            getStatement().executeUpdate(MySQLContract.sql_insert_team("Sousa", "SOU", 5, 7, 6, -2));
            getStatement().executeUpdate(MySQLContract.sql_insert_team("CSP", "CSP", 5, 10, 3, -10));
            getStatement().executeUpdate(MySQLContract.sql_insert_team("Internacional-PB", "INT", 3, 8, 7, -12));
            getStatement().executeUpdate(MySQLContract.sql_insert_team("Paraiba", "PRB", 1, 9, 8, -14));

        } catch (SQLException e) {
            result = false;
            System.out.println("Erro ao dropar uma das tabelas");
            e.printStackTrace();
        }
        return result;
    }
}

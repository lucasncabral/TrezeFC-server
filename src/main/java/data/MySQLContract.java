package data;

public class MySQLContract {


    private abstract static class Team {
        static final String TABLE_NAME = "Team";
        static final String COLUMN_ID = "idTeam";
        static final String COLUMN_NAME = "nameTeam";
        static final String COLUMN_ABR = "abrTeam";
        static final String COLUMN_VICTORYS = "victorysTeam";
        static final String COLUMN_LOSERS = "losersTeam";
        static final String COLUMN_TIES = "tieTeam";
        static final String COLUMN_SG = "sgTeam";
    }

    private abstract static class Notifications {
        static final String TABLE_NAME = "Notifications";
        static final String COLUMN_ID = "idNotification";
        static final String COLUMN_TOKEN = "tokenNotification";
        static final String COLUMN_GOALS = "goalsNotification";
        static final String COLUMN_MATCH = "matchNotification";
        static final String COLUMN_PENA = "penaNotification";
        static final String COLUMN_CHANG = "changNotification";
    }

    private abstract static class Notice {
        static final String TABLE_NAME = "Notice";
        static final String COLUMN_ID = "idNotice";
        static final String COLUMN_DESCRIPTION = "descriptionNotice";
        static final String COLUMN_IMAGE = "imageNotice";
        static final String COLUMN_PUBLISHED = "publishedNotice";
        static final String COLUMN_LINK = "linkNotice";
        static final String COLUMN_DATE = "dateNotice";
    }

    private abstract static class Game {
        static final String TABLE_NAME = "Game";
        static final String COLUMN_ID = "idGame";
        static final String COLUMN_HOME = "homeGame";
        static final String COLUMN_VISIT = "visitGame";
        static final String COLUMN_TIME = "timeGame";
        static final String COLUMN_DATE = "dateGame";
        static final String COLUMN_LOCAL = "localGame";
    }

    private abstract static class Player {
        static final String TABLE_NAME = "Player";
        static final String COLUMN_ID = "idPlayer";
        static final String COLUMN_NAME = "namePlayer";
        static final String COLUMN_POSITION = "positionPlayer";
        static final String COLUMN_TIME = "timePlayer";
        static final String COLUMN_MATCHS = "matchsPlayer";
        static final String COLUMN_CARDS = "cardsPlayer";
        static final String COLUMN_GOALS = "goalsPlayer";
        public static final String COLUMN_IMAGE = "imagePlayer";
    }

    // CONSTRUCTORS
    public static String sql_create_table_Team() {
        return "CREATE TABLE " + Team.TABLE_NAME +
                "(" + Team.COLUMN_ID + " serial," +
                " " + Team.COLUMN_NAME + " VARCHAR(50)," +
                " " + Team.COLUMN_ABR + " VARCHAR(5)," +
                " " + Team.COLUMN_VICTORYS + " INT," +
                " " + Team.COLUMN_LOSERS + " INT," +
                " " + Team.COLUMN_TIES + " INT," +
                " " + Team.COLUMN_SG + " INT);";
    }

    public static String sql_create_table_Notifications(){
        return "CREATE TABLE " + Notifications.TABLE_NAME +
                "(" + Notifications.COLUMN_ID + " serial," +
                " " + Notifications.COLUMN_TOKEN + " VARCHAR(500)," +
                " " + Notifications.COLUMN_GOALS + " VARCHAR(10)," +
                " " + Notifications.COLUMN_MATCH + " VARCHAR(10)," +
                " " + Notifications.COLUMN_PENA + " VARCHAR(10)," +
                " " + Notifications.COLUMN_CHANG + " VARCHAR(10));";
    }

    public static String sql_create_table_Player() {
        return "CREATE TABLE " + Player.TABLE_NAME +
                "(" + Player.COLUMN_ID + " serial," +
                " " + Player.COLUMN_NAME + " VARCHAR(50)," +
                " " + Player.COLUMN_POSITION + " VARCHAR(50)," +
                " " + Player.COLUMN_IMAGE + " VARCHAR(500)," +
                " " + Player.COLUMN_GOALS + " INT," +
                " " + Player.COLUMN_CARDS + " INT," +
                " " + Player.COLUMN_MATCHS + " INT," +
                " " + Player.COLUMN_TIME + " INT);";
    }



    public static String sql_create_table_Notice() {
        return "CREATE TABLE " + Notice.TABLE_NAME +
                "(" + Notice.COLUMN_ID + " serial," +
                " " + Notice.COLUMN_DESCRIPTION + " VARCHAR(500)," +
                " " + Notice.COLUMN_IMAGE + " VARCHAR(500)," +
                " " + Notice.COLUMN_PUBLISHED + " VARCHAR(50)," +
                " " + Notice.COLUMN_DATE + " VARCHAR(50)," +
                " " + Notice.COLUMN_LINK + " VARCHAR(500));";
    }

    public static String sql_create_table_Game() {
        return "CREATE TABLE " + Game.TABLE_NAME +
                "(" + Game.COLUMN_ID + " serial," +
                " " + Game.COLUMN_HOME + " VARCHAR(30)," +
                " " + Game.COLUMN_VISIT + " VARCHAR(30)," +
                " " + Game.COLUMN_TIME + " VARCHAR(30)," +
                " " + Game.COLUMN_DATE + " VARCHAR(30)," +
                " " + Game.COLUMN_LOCAL + " VARCHAR(30));";
    }

    // DROPS
    public static String sql_drop_table_game() {
        return "DROP TABLE " + Game.TABLE_NAME;
    }

    public static String sql_drop_table_team() {
        return "DROP TABLE " + Team.TABLE_NAME;
    }

    public static String sql_drop_table_notice() {
        return "DROP TABLE " + Notice.TABLE_NAME;
    }

    public static String sql_drop_table_notifications() {
        return "DROP TABLE " + Notifications.TABLE_NAME;
    }

    // DELETE
    public static String sql_delete_game() { return "TRUNCATE " + Game.TABLE_NAME; }

    public static String sql_delete_player() { return "TRUNCATE " + Player.TABLE_NAME; }

    public static String sql_delete_team() {
        return "TRUNCATE " + Team.TABLE_NAME;
    }

    public static String sql_delete_notice() {
        return "TRUNCATE " + Notice.TABLE_NAME;
    }

    // INSERTS
    public static String sql_insert_team(String name, String abr, int victory, int losers, int ties, int sg) {
        return "INSERT INTO " + Team.TABLE_NAME +
                "(" + Team.COLUMN_NAME + ", " + Team.COLUMN_ABR + ", " + Team.COLUMN_VICTORYS + ", " +
                Team.COLUMN_LOSERS + ", " + Team.COLUMN_TIES + ", " + Team.COLUMN_SG + ") VALUES ('"
                + name + "', '" + abr + "', '" + victory + "', '" + losers + "','" + ties + "', '" + sg + "');";
    }

    public static String sql_insert_notification(String token, String not1, String not2, String not3, String not4){
        return "INSERT INTO " + Notifications.TABLE_NAME +
                "(" + Notifications.COLUMN_TOKEN + ", " + Notifications.COLUMN_GOALS + ", " + Notifications.COLUMN_MATCH + ", " +
                Notifications.COLUMN_PENA + ", " + Notifications.COLUMN_CHANG + ") VALUES ('"
                + token + "', '" + not1 + "', '" + not2 + "', '" + not3 + "','" + not4 + "');";

    }

    public static String sql_insert_player(String name, String position, String image, int goals, int cards, int matchs, int time){
        return "INSERT INTO " + Player.TABLE_NAME +
                "(" + Player.COLUMN_NAME + ", " + Player.COLUMN_POSITION + ", " + Player.COLUMN_IMAGE  + ", "+ Player.COLUMN_GOALS + ", " +
                Player.COLUMN_CARDS + ", " + Player.COLUMN_MATCHS + ", " + Player.COLUMN_TIME + ") VALUES ('"
                + name + "', '" + position + "', '" + image  + "', '" + goals + "', '" + cards + "','" + matchs + "', '" + time + "');";

    }


    public static String sql_insert_game(String teamHome, String teamVisit, String date, String local, String time) {
        return "INSERT INTO " + Game.TABLE_NAME +
                "(" + Game.COLUMN_LOCAL + ", " + Game.COLUMN_DATE + ", " + Game.COLUMN_TIME + ", " +
                Game.COLUMN_VISIT + ", " + Game.COLUMN_HOME + ") VALUES ('"
                + local + "', '" + date + "', '" + time + "', '" + teamVisit + "','" + teamHome + "');";
    }

    public static String sql_insert_notice(String description, String link, String published, String date, String imagelink) {
        return "INSERT INTO " + Notice.TABLE_NAME +
                "(" + Notice.COLUMN_DESCRIPTION + ", " + Notice.COLUMN_LINK + ", " + Notice.COLUMN_PUBLISHED + ", " +
                Notice.COLUMN_DATE + ", " + Notice.COLUMN_IMAGE + ") VALUES ('"
                + description + "', '" + link + "', '" + published + "', '" + date + "','" + imagelink + "');";
    }





    // DELETES

    // UPDATES
    public static String sql_update_team_win(String name, int sg) {
        return "UPDATE " + Team.TABLE_NAME + " SET " + Team.COLUMN_VICTORYS + " = " +
                Team.COLUMN_VICTORYS + " + 1, " + Team.COLUMN_SG + " = " + Team.COLUMN_SG + " + " + sg
                + " WHERE " + Team.COLUMN_ABR + " = '" + name + "';";
    }

    public static String sql_update_notification(String token, String not1, String not2, String not3, String not4) {
        return "UPDATE " + Notifications.TABLE_NAME + " SET " + Notifications.COLUMN_GOALS + " = " + not1 + " , " +
                Notifications.COLUMN_MATCH + " = " + not2 + " , " + Notifications.COLUMN_PENA + " = " + not3 + ", " +
                Notifications.COLUMN_CHANG + " = " + not4
                + " WHERE " + Notifications.COLUMN_TOKEN + " = '" + token + "';";

    }

    public static String sql_update_player(String name, int time, int goals, int cards, int matchs) {
        return "UPDATE " + Player.TABLE_NAME + " SET " + Player.COLUMN_TIME + " = " +
                Player.COLUMN_TIME + " + " + time + ", " + Player.COLUMN_GOALS + " = " + Player.COLUMN_GOALS  + " + " + goals + ", " +
                Player.COLUMN_CARDS + " = " + Player.COLUMN_CARDS + " + " + cards + ", "
                + Player.COLUMN_MATCHS + " = " + Player.COLUMN_MATCHS  + " + " + goals
                + " WHERE " + Player.COLUMN_NAME + " = '" + name + "';";
    }

    public static String sql_update_team_tie(String name, String name2) {
        return "UPDATE " + Team.TABLE_NAME + " SET " + Team.COLUMN_TIES + " = " +
                Team.COLUMN_TIES + " + 1 WHERE " + Team.COLUMN_ABR + " = '" + name + "' OR " + Team.COLUMN_ABR + " = '" + name2 + "' ;";
    }

    public static String sql_update_team_losers(String name, int sg) {
        return "UPDATE " + Team.TABLE_NAME + " SET " + Team.COLUMN_LOSERS + " = " +
                Team.COLUMN_LOSERS + " + 1, " + Team.COLUMN_SG + " = " + Team.COLUMN_SG + " - " + sg
                + " WHERE " + Team.COLUMN_ABR + " = '" + name + "';";
    }

    public static String sql_update_game(int id, String teamHome, String teamVisit, String date, String local, String time) {
        return "UPDATE " + Game.TABLE_NAME + " SET " + Game.COLUMN_HOME + " = '" + teamHome +
                "', " + Game.COLUMN_LOCAL + " = '" + local + "', " + Game.COLUMN_VISIT + " = '" + teamVisit + "', "
                + Game.COLUMN_TIME + " = '" + time + "', " + Game.COLUMN_DATE + " = '" + date +
                "' WHERE " + Game.COLUMN_ID + " = '" + id + "'";
    }

    // GETS
    public static String sql_get_teams() {
        return "SELECT * FROM " + Team.TABLE_NAME;
    }
    public static String sql_get_games() { return "SELECT * FROM " + Game.TABLE_NAME; }
    public static String sql_get_players() {return "SELECT * FROM " + Player.TABLE_NAME;    }
    public static String sql_get_all_notices() { return "SELECT * FROM " + Notice.TABLE_NAME; }
    public static String sql_get_all_tokens() {  return "SELECT * FROM " + Notifications.TABLE_NAME; }
    public static String sql_get_notifications(String column) {
        return "SELECT * FROM " + Notifications.TABLE_NAME + " WHERE " + column + " = 'true'"; }

    public static String sql_get_notification(String token){
        return "SELECT * FROM " + Notifications.TABLE_NAME + " WHERE " + Notifications.COLUMN_TOKEN + " = '" + token + "'";
    }

    public static String sql_get_notice(String description) { return "SELECT * FROM " + Notice.TABLE_NAME
            + " WHERE " + Notice.COLUMN_DESCRIPTION  + " = '" + description + "'";}
}

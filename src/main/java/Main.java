import controller.ClientController;
import factory.testWebService;
import factory.testWebServicePlayers;
import model.Game;
import model.Notice;
import model.Player;
import model.Team;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Timer;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/hello", (req, res) -> "TREZE FC server is online");

        post("/createTable", (req, res) -> new ClientController().createTable());

        post("/deleteTable", (req, res) -> new ClientController().deleteTable());

        post("/updateNotices", (req, res) -> {
            new testWebService().updateNotices();
            return true;
        });

        post("/updatePlayers", (req, res) -> {
            new testWebServicePlayers().update();
            return true;
        });

        // TODO TEAM
        post("/addTeam", (request, response) -> {
            String name = request.queryParams("name");
            String abr = request.queryParams("abr");
            int victorys = Integer.parseInt(request.queryParams("victorys"));
            int losers = Integer.parseInt(request.queryParams("losers"));
            int tie = Integer.parseInt(request.queryParams("tie"));
            int sg = Integer.parseInt(request.queryParams("sg"));
            boolean result = new ClientController().addTeam(name, abr, victorys, losers, tie, sg);
            response.status(201);
            JSONObject resp = new JSONObject();
            resp.put("response", result);
            return resp;
        });

        post("/addPlayer", (request, response) -> {
            String name = request.queryParams("name");
            String position = request.queryParams("position");
            String image = request.queryParams("image");
            int goals = Integer.parseInt(request.queryParams("goals"));
            int cards = Integer.parseInt(request.queryParams("cards"));
            int matchs = Integer.parseInt(request.queryParams("matchs"));
            int time = Integer.parseInt(request.queryParams("time"));
            boolean result = new ClientController().addPlayer(name,position,image, goals,cards,matchs,time);
            response.status(201);
            JSONObject resp = new JSONObject();
            resp.put("response", result);
            return resp;
        });

        get("/getTeams", (req, res) -> {
            List<Team> result = new ClientController().getTeams();
            res.status(201);
            JSONObject resp = new JSONObject();
            JSONArray array = new JSONArray(result);
            resp.put("result", array);
            return resp;
        });

        get("/getPlayers", (req, res) -> {
            List<Player> result = new ClientController().getPlayers();
            res.status(201);
            JSONObject resp = new JSONObject();
            JSONArray array = new JSONArray(result);
            resp.put("result", array);
            return resp;
        });

        post("/atualizaClassificacao", (request,response) -> {
            return new ClientController().atualizaClassificacao();
        });

        post("/updateToken", (request,response) -> {
            String token = dadoJson(request.body(), "token");
            String notGoal = dadoJson(request.body(), "notificationGoal");
            String notMatch = dadoJson(request.body(), "notificationMatch");
            String notPena = dadoJson(request.body(), "notificationPenaltie");
            String notChanges = dadoJson(request.body(), "notificationChanges");
           return new ClientController().setToken(token,notGoal,notMatch,notPena,notChanges);
        });

        post("/sendNotification", (request,response) -> {
            String type = request.queryParams("type");
            String title = request.queryParams("title");
            String description = request.queryParams("description");
            return new ClientController().sendNotification(type,title,description);
        });

        get("/getNotices", (req, res) -> {
            List<Notice> result = new ClientController().getNotices();
            res.status(201);
            JSONObject resp = new JSONObject();
            JSONArray array = new JSONArray(result);
            resp.put("result", array);
            return resp;
        });

        get("/getTokens", (req, res) -> {
            List<String> result = new ClientController().getAllTokens();
            res.status(201);
            JSONObject resp = new JSONObject();
            JSONArray array = new JSONArray(result);
            resp.put("result", array);
            return resp;
        });

        post("/addGameResult", (request, response) -> {
            String teamHome = request.queryParams("teamHome");
            String teamVisit = request.queryParams("teamVisit");
            int goalsHome = Integer.parseInt(request.queryParams("goalsHome"));
            int goalsVisit = Integer.parseInt(request.queryParams("goalsVisit"));
            boolean result = new ClientController().addGameResult(teamHome, goalsHome, goalsVisit, teamVisit);
            response.status(201);
            JSONObject resp = new JSONObject();
            resp.put("response", result);
            return resp;
        });

        // TODO GAME
        post("/addGame", (request, response) -> {
            String teamHome = request.queryParams("teamHome");
            String teamVisit = request.queryParams("teamVisit");
            String time = request.queryParams("time");
            String date = request.queryParams("date");
            String local = request.queryParams("local");
            boolean result = new ClientController().addGame(teamHome, teamVisit, date, local,time);
            response.status(201);
            JSONObject resp = new JSONObject();
            resp.put("response", result);
            return resp;
        });

        post("/editGame", (request, response) -> {
            int id = Integer.parseInt(request.queryParams("idGame"));
            String teamHome = request.queryParams("teamHome");
            String teamVisit = request.queryParams("teamVisit");
            String time = request.queryParams("time");
            String date = request.queryParams("date");
            String local = request.queryParams("local");
            boolean result = new ClientController().editGame(id,teamHome, teamVisit, date, local,time);
            response.status(201);
            JSONObject resp = new JSONObject();
            resp.put("response", result);
            return resp;
        });

        get("/getGames", (req, res) -> {
            List<Game> result = new ClientController().getGames();
            res.status(201);
            JSONObject resp = new JSONObject();
            JSONArray array = new JSONArray(result);
            resp.put("result", array);
            return resp;
        });


        Timer t = new Timer();
        int seconds = 60 * 60;
        t.scheduleAtFixedRate(new testWebService(), 0, seconds * 1000);
    }

    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }


    private static String dadoJson (String json, String data) {
        String result;
        JSONObject jsonObject = new JSONObject(json);
        result = jsonObject.get(data).toString();
        return result;
    }

}

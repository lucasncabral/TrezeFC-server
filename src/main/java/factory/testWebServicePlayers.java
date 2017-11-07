package factory;

import controller.ClientController;
import model.Notice;
import model.Player;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.text.html.parser.Parser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class testWebServicePlayers {

    private Player players;

    public testWebServicePlayers(){
    }

    public static void update() {
        List<Player> players = new ArrayList<>();
        try {
            players.addAll(getPlayers());
        } catch (IOException e) {
            e.printStackTrace();
        }

        new ClientController().removePlayers();
        for (Player n : players) {
            try {
                n = getInformations(n);
            } catch (IOException e) {
                e.printStackTrace();
            }
            new ClientController().addPlayer(n.getName(), n.getPosition(),n.getPhoto(), n.getYellowCards(), n.getGols(), n.getJogos(), n.getMinutesPlay());
        }
    }

    private static List<Player> getPlayers() throws  IOException {
        String url = "http://www.ogol.com.br/team_players.php?id=3499&epoca_stats_id=146";
        print("Fetching %s...", url);

        org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        // Elements links = doc.select("table");

        Elements links2 = doc.select("td");

        List<Player> players = new ArrayList<>();
        List<String> d = new ArrayList<>();
        d.add("Leo Kanu"); d.add("Ronaldo"); d.add("Jaime"); d.add("Murilo Ceará"); d.add("Dorgival");
        d.add("Julinho"); d.add("Santos"); d.add("Kaique"); d.add("Thiago Costa"); d.add("Geraldo");
        d.add("Jefferson"); d.add("Andrey"); d.add("Otacilio Neto");
        for (Element link : links) {
            if(link.attr("abs:href").contains("jogador") && !link.attr("abs:href").contains("xray") && !link.attr("abs:href").contains("raiox")) {
                    players.add(new Player(link.text(), link.attr("abs:href")));
            }
        }

        List<Player> playersFinal = players;

        int countPlayer = 0;
        int countValues = 0;
        for (Element link : links2) {
            try {
                int attr = 0;
                if(link.text().equals("-") && countValues == 4){
                    attr = 0;
                  //  if(countValues != 4)
                  //      attr = (Integer.parseInt(link.text()));
                } else {
                    attr = (Integer.parseInt(link.text()));
                }

                switch (countValues){
                    case 0:
                        playersFinal.get(countPlayer).setJogos(attr);
                        break;
                    case 1:
                        playersFinal.get(countPlayer).setGols(attr);
                        break;
                    case 3:
                        if(attr > 44)
                            playersFinal.get(countPlayer).setMinutesPlay(attr);
                        break;
                    case 4:
                        playersFinal.get(countPlayer).setYellowCards(attr);
                        break;
                    default:
                        break;
                }

                if(countValues == 4){
                    countValues = 0;
                    countPlayer++;
                } else {
                    if(countValues == 3 && attr < 44){
                         // test
                    } else {
                        countValues++;
                    }
                }
            } catch (Exception ex){
                // nothin
            }
        }


        players = new ArrayList<>();
        for (Player player : playersFinal){
            if(!d.contains(player.getName()) && !player.getName().contains("Murilo"))
                players.add(player);
        }

        return players;
    }

    private static Player getInformations(Player p) throws IOException {
        String url2 = p.getPhoto();
        System.out.println(p.getName());
        switch(p.getName()){
            case "Laerte":
                p.setJogos(p.getJogos() + 4);
                p.setMinutesPlay(p.getMinutesPlay() + 360);
                p.setYellowCards(p.getYellowCards() + 1);
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Laerte.jpg");
                p.setPosition("Zagueiro");
                break;
            case "Bruno Fuso":
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Bruno-Fuso.jpg");
                p.setPosition("Goleiro");
                p.setJogos(p.getJogos() + 4);
                p.setMinutesPlay(p.getMinutesPlay() + 360);
                break;
            case "Dico":
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Dico.jpg");
                p.setPosition("Atacante");
                p.setGols(p.getGols() + 2);
                p.setJogos(p.getJogos() + 4);
                p.setMinutesPlay(p.getMinutesPlay() + 270);
                break;
            case "Leo Kanu":
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Le%CC%81o-Kanu.jpg");
                p.setPosition("Zagueiro");
                break;
            case "Everaldo":
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Everaldo.jpg");
                p.setPosition("Lateral");
                break;
            case "Patrick":
                p.setJogos(p.getJogos() + 5);
                p.setMinutesPlay(p.getMinutesPlay() + 450);
                p.setYellowCards(p.getYellowCards() + 1);
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Patrick.jpg");
                p.setPosition("Volante");
                break;
            case "Robson":
                p.setJogos(p.getJogos() + 3);
                p.setMinutesPlay(p.getMinutesPlay() + 270);
                p.setYellowCards(p.getYellowCards() + 1);
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Robson.jpg");
                p.setPosition("Volante");
                break;
            case "Geraldo":
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Geraldo.jpg");
                p.setPosition("Atacante");
                break;
            case "Thiago Costa":
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Thiago-Costa.jpg");
                p.setPosition("Meia");
                break;
            case "Italo":
                p.setJogos(p.getJogos() + 5);
                p.setMinutesPlay(p.getMinutesPlay() + 450);
                p.setYellowCards(p.getYellowCards() + 1);
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Italo.jpg");
                p.setPosition("Meia");
                break;
            case "Jefferson Sandes":
                p.setJogos(p.getJogos() + 1);
                p.setMinutesPlay(p.getMinutesPlay() + 90);
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Jefferson-Sandes.jpg");
                p.setPosition("Lateral");
                break;
            case "Ferreira":
                p.setJogos(p.getJogos() + 5);
                p.setMinutesPlay(p.getMinutesPlay() + 384);
                p.setGols(p.getGols() + 1);
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Ferreira.jpg");
                p.setPosition("Lateral");
                break;
            case "Otacilio Neto":
                p.setJogos(p.getJogos() + 3);
                p.setMinutesPlay(p.getMinutesPlay() + 201);
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Otaci%CC%81lio-Neto.jpg");
                p.setPosition("Atacante");
                break;
            case "Fernando Lopes":
                p.setJogos(p.getJogos() + 4);
                p.setMinutesPlay(p.getMinutesPlay() + 360);
                p.setYellowCards(p.getYellowCards() + 2);
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Fernando-Lopes.jpg");
                p.setPosition("Zagueiro");
                break;
            case "Jacson":
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2016/12/Jacson.jpg");
                p.setPosition("Atacante");
                break;
            case "Edson":
                p.setJogos(p.getJogos() + 4);
                p.setMinutesPlay(p.getMinutesPlay() + 235);
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Edson-Reis.jpg");
                p.setPosition("Atacante");
                break;
            case "Diego":
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Diego.jpg");
                p.setPosition("Goleiro");
                break;
            case "Fabiano Buchecha":
                p.setPhoto("http://i64.tinypic.com/4iofsx.jpg");
                p.setPosition("Meia");
                break;
            case "Matheus":
                p.setPhoto("http://i67.tinypic.com/10mvc7s.jpg");
                p.setPosition("Goleiro");
                break;
            case "Rael":
                p.setPhoto("http://i67.tinypic.com/ebdcee.jpg");
                p.setPosition("Atacante");
                break;
            case "Samuel":
                p.setPhoto("http://i64.tinypic.com/jakllj.jpg");
                p.setPosition("Zagueiro");
                break;
            case "Jean Carlos":
                p.setPosition("Atacante");
                p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Marcefuglinho.jpg");
                p.setJogos(p.getJogos() + 1);
                p.setMinutesPlay(p.getMinutesPlay() + 90);

            default:
                org.jsoup.nodes.Document doc = Jsoup.connect(url2).get();
                Elements urls = doc.select("meta[property]");
                for (Element data : urls) {
                    if(data.attr("content").contains(".png") || data.attr("content").contains(".jpg")) {
                        p.setPhoto(data.attr("content"));
                        break;
                    }
                }

                if(p.getPhoto().equals("http://www.ogol.com.br/img/jogadores/52/354052__20160904134806_marcelinho_paraiba.jpg")){
                    p.setMinutesPlay(p.getMinutesPlay() + 345);
                    p.setJogos(p.getJogos() + 4);
                    p.setGols(p.getGols() + 1);
                    p.setYellowCards(p.getYellowCards() + 1);
                    p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Marcelinho.jpg");
                    p.setPosition("Meia");
                } else if (p.getName().equals("Julio")) {
                    p.setJogos(p.getJogos() + 4);
                    p.setMinutesPlay(p.getMinutesPlay() + 157);
                    p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Ju%CC%81lio-Barboza.jpg");
                    p.setPosition("Atacante");
                } else if (p.getPhoto().equals("http://www.ogol.com.br/img/jogadores/09/134109_pri_anderson.jpg")){
                    p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Feija%CC%83o.jpg");
                    p.setJogos(p.getJogos() + 3);
                    p.setMinutesPlay(p.getMinutesPlay() + 72);
                    p.setPosition("Meia");
                } else if (p.getPhoto().equals("http://www.ogol.com.br/img/jogadores/26/349526__20160729081315_erico_junior.jpg")){
                    p.setJogos(p.getJogos() + 3);
                    p.setMinutesPlay(p.getMinutesPlay() + 57);
                    p.setGols(p.getGols() + 1);
                    p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Erico-Ju%CC%81nior.jpg");
                    p.setPosition("Atacante");
                } else if (p.getPhoto().equals("http://www.ogol.com.br/img/jogadores/50/348150__20160720095855_roger_gaucho.jpg") || p.getName().contains("Roger")){
                    p.setJogos(p.getJogos() + 2);
                    p.setMinutesPlay(p.getMinutesPlay() + 161);
                    p.setPhoto("http://oi65.tinypic.com/1zx5o3r.jpg");
                    p.setPosition("Meia");
                } else if (p.getPhoto().equals("http://www.ogol.com.br/img/jogadores/59/88459_pri__20160229120539_dede.jpg")) {
                    p.setJogos(p.getJogos() + 5);
                    p.setMinutesPlay(p.getMinutesPlay() + 388);
                    p.setPhoto("http://trezefc.com.br/wp-content/uploads/2017/02/Dede%CC%81.jpg");
                    p.setPosition("Volante");
                }
                break;
        }


        return p;
    }

    private static void print(String msg, Object... args) {
            System.out.println(String.format(msg, args));
        }

        private static String trim(String s, int width) {
            if (s.length() > width)
                return s.substring(0, width-1) + "";
            else
                return s;
        }
}


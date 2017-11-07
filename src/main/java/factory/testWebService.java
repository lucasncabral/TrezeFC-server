package factory;

import controller.ClientController;
import model.Notice;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;


public class testWebService extends TimerTask{

        public testWebService(){
        }

        public void run() {
            updateNotices();
        }

        public void updateNotices(){
            List<Notice> notices = new ArrayList<>();
            try {
                // notices = vozDaTorcida();
                notices = trz();
                // notices.addAll(trz());
                notices.addAll(pbe());
                notices.addAll(ge());
                notices.addAll(updateIg("trezefcoficial"));
                notices.addAll(updateIg("trezefc"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            new ClientController().updateNotices(notices);
            //for (Notice n : notices) {
            //   print(n.toString());
            //}
        }

    private static List<Notice> pbe() throws IOException {
        String url = "http://pbesportes.net/treze-fc/";
        print("Fetching %s...", url);
        // gdlr-standard-style

        org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
        Elements notice1 = doc.select("img[src]");
        Elements notice2 = doc.select("a[href]");
        // Elements notice2 = doc.select("a[class=title]");
        Notice n1 = new Notice(0,"description","linkImage", "pbe", "linkNotice",null);
        Notice n2 = new Notice(0,"description","linkImage", "pbe", "linkNotice",null);
        Notice n3 = new Notice(0,"description","linkImage", "pbe", "linkNotice",null);

        List<Notice> notices = new ArrayList<>();
        int count = 0;
        for (Element link : notice2) {
            if(link.attr("abs:href").contains("http://pbesportes.net/2017/") && !link.text().equals("") && link.text().length() !=11){
                String date = link.attr("abs:href").substring(30,33) + link.attr("abs:href").substring(27,29) + "/2017";
                if(count == 0){
                    n1.setLinkNotice(link.attr("abs:href"));
                    n1.setDescription(link.text());
                    n1.setDate(null);
                } else if (count == 1){
                    n2.setLinkNotice(link.attr("abs:href"));
                    n2.setDescription(link.text());
                    n2.setDate(null);
                } else {
                    n3.setLinkNotice(link.attr("abs:href"));
                    n3.setDescription(link.text());
                    n3.setDate(null);
                }
                count++;
            }
        }


        for (int i = 13; i < 16;i++) {
            String result = notice1.get(i).toString();
            String linkImage =notice1.get(i).attr("abs:src");
            if(i == 0){
                n1.setLinkImage(linkImage);
            } else if (i == 1){
                n2.setLinkImage(linkImage);
            } else {
                n3.setLinkImage(linkImage);
            }
        }
        notices.add(n1);
        notices.add(n2);
        notices.add(n3);

        return notices;
    }

    private static List<Notice> trz() throws IOException {
        String url = "http://trezefc.com.br/categoria/noticias/";
        print("Fetching %s...", url);

        org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
        Elements notice1 = doc.select("figure[class]");
        Elements notice2 = doc.select("a[class=title]");
        Notice n1 = new Notice(0,"description","linkImage", "trz", "linkNotice",null);
        Notice n2 = new Notice(0,"description","linkImage", "trz", "linkNotice",null);

        Notice n3 = new Notice(0,"description","linkImage", "trz", "linkNotice",null);

        List<Notice> notices = new ArrayList<>();
        //for (Element link : notice1) {
        for (int i = 0; i < 3;i++) {
            String result = notice1.get(i).toString();
            int inicio = result.indexOf("(") + 1;
            int last = result.indexOf(")");
            String linkImage = result.substring(inicio,last);
            int j = result.lastIndexOf("href=");
            int k = result.indexOf("></a");
            String linkNotice = result.substring(j+6,k-1);

            String description = notice2.get(i).text();

            String date = linkNotice.substring(30,32) + "/" + linkNotice.substring(27,29) + "/2017";

            if(i == 0){
                n1.setLinkImage(linkImage);
                n1.setLinkNotice(linkNotice);
                n1.setDescription(description);
                n1.setDate(null);
            } else if (i == 1){
                n2.setLinkImage(linkImage);
                n2.setLinkNotice(linkNotice);
                n2.setDescription(description);
                n2.setDate(null);
            } else {
                n3.setLinkImage(linkImage);
                n3.setLinkNotice(linkNotice);
                n3.setDescription(description);
                n3.setDate(null);
            }

        }
        notices.add(n1);
        notices.add(n2);
        notices.add(n3);

        return notices;
    }

    private static List<Notice> ge() throws IOException {
        String url = "http://globoesporte.globo.com/pb/futebol/times/treze/";
        print("Fetching %s...", url);

        org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
        Elements media = doc.select("[src]");
        Elements links = doc.select("a[href]");

        List<Notice> notices = new ArrayList<>();
        for (Element link : links) {
            if(link.attr("abs:href").contains("2017")) {
                // print(" * a: <%s>  (%s)", link.attr("abs:href"), link.text());
                if (!(link.text()).equals("") && !(link.text()).equals("veja como foi")) {
                    notices.add(new Notice(0, link.text(), null, "ge", link.attr("abs:href").toString(), null));
                }
            }
        }

        int i = 0;
        for (Element src : media) {
            // link images
            if (src.tagName().equals("img") && src.attr("abs:src").contains("2017")) {
                notices.get(notices.size() - (i+1)).setLinkImage(src.tagName());
            }
        }
        return notices;
    }

    private static List<Notice> vozDaTorcida() throws IOException {
        String url = "http://www.vozdatorcida.com/times/treze/";
        print("Fetching %s...", url);

        org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");

        List<Notice> notices = new ArrayList<>();
        for (Element link : links) {
            if(link.attr("abs:href").contains("2017")) {
                // print(" * a: <%s>  (%s)", link.attr("abs:href"), link.text());
                String[] info = link.text().split(" - ");
                String date = info[0].substring(0,info[0].length());
                String title = info[1].substring(0, info[1].length() - 1);
                notices.add(new Notice(0,title,null,"vt", link.attr("abs:href").toString(),null));
            }
        }
        return notices;
    }

    private static void print(String msg, Object... args) {
            System.out.println(String.format(msg, args));
        }

    private static List<Notice> updateIg(String link2) throws IOException {
        String url = link2;
        print("Fetching %s...", url);

        org.jsoup.nodes.Document doc = Jsoup.connect("https://www.instagram.com/" + url + "/").get();
        Elements media = doc.select("[src]");
        // System.out.println(doc);
        Elements links = doc.select("script[type=text/javascript]");

        List<Notice> notices = new ArrayList<>();
        for (Element link : links) {
            if(trim(link.dataNodes().toString(), 14).equals("[window._shar")){
                String node = link.dataNodes().get(0).getWholeData();
                JSONObject json = new JSONObject(node.substring(21));

                JSONObject json2 = (JSONObject) json.getJSONObject("entry_data").getJSONArray("ProfilePage").get(0);
                JSONArray postagens = json2.getJSONObject("user").getJSONObject("media").getJSONArray("nodes");
                for (int i = 0; i < 3;i++){
                    JSONObject postagem = (JSONObject) postagens.get(i);
                    String description = postagem.getString("caption");
                    if(description.length() > 50){
                        description = trim(postagem.getString("caption"),50) + "...";
                    }
                    Notice notice = new Notice(0,description,postagem.getString("display_src"),"ig","https://www.instagram.com/p/" + postagem.getString("code") + "/?taken-by=" + url,null);
                    notices.add(notice);
                }
            }
        }
        return notices;
    }

        private static String trim(String s, int width) {
            if (s.length() > width)
                return s.substring(0, width-1) + "";
            else
                return s;
        }

}


package model;

/**
 * Created by Lucas on 15/02/2017.
 */
public class Team {
    private String nome, abr;
    private int pontuation, victorys, losers, tie, sg;
    private Integer id;

    public Team(int id, String nome, String abr, int pontuation, int victorys, int tie, int losers, int sg){
        this.id = id;
        this.nome = nome;
        this.abr = abr;
        this.pontuation = pontuation;
        this.victorys = victorys;
        this.losers = losers;
        this.tie = tie;
        this.sg = sg;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getPontuation() {
        return pontuation;
    }

    public int getVictorys() {
        return victorys;
    }

    public int getLosers() {
        return losers;
    }

    public int getTie() {
        return tie;
    }

    public int getSg() {
        return sg;
    }

    public String getAbr() {
        return abr;
    }

    public String toStringValues() {
        String result = "      ";
        result += pontuation;
        result += verificaTamanho(pontuation);
        result += victorys;
        result += verificaTamanho(victorys);
        result += tie;
        result += verificaTamanho(tie);
        result += losers;
        result += verificaTamanho(losers) + " ";
        result += sg;
        result += verificaTamanho(sg);
        return result;
    }

    private String verificaTamanho(int pontuation) {
        if(pontuation > 9 || pontuation < 0)
            return "     ";
        else
            return "      ";
    }
}
package GUI_p02;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;


public class Ranking {
    Gracz[] top10;
    private Gracz actual_player;
    private int score_len;


    public Ranking(){
        top10 = new Gracz[10];
        for(int i = 0; i< top10.length; i++){
            top10[i] = new Gracz();
            top10[i].setNick("bez nazwy");
            top10[i].setScore(0);
        }
        this.score_len = top10.length;
    }

    public void checkScore(Gracz actual_player){
        int score = actual_player.getScore();
             for(int i=0; i<top10.length; i++){
                 if(top10[i] == null){
                     top10[i] = actual_player;
                 }else{
                     if(score >= top10[i].getScore()){
                         for(int j=top10.length; j<i; j--){
                             top10[j] = top10[j-1];
                         }
                         top10[i] = actual_player;
                     }
                 }
             }
    }

    public boolean isTop10Full(){
        for(Gracz gracz : top10){
            if(gracz == null){
                return false;
            }
        }
        return true;
    }

    public boolean isScoreBetter(int score){
        for(Gracz gracz : top10){
            if(score > gracz.getScore()){
                return true;
            }
        }
        return false;
    }

    public void addPlayer(Gracz player){
        int index = 0;
        for(int i = 0; i < top10.length; i++){
            if(top10[i] == null || player.getScore() > top10[i].getScore()){
                index += 1;
                break;
            }
        }

        for(int i = top10.length - 1; i > index; i--){
            top10[i] = top10[i-1];
        }

        top10[index] = player;
    }

    public void readFile(Gracz[] ranking, String file_path){
        BufferedReader br = null;
        String linia = "";
        String separator = ",";
        try {
            br = new BufferedReader(new FileReader(file_path));
            int indeks = 0;
            while ((linia = br.readLine()) != null && indeks < ranking.length) {
                String[] dane = linia.split(separator);
                if (dane.length >= 2) {
                    String nick = dane[0];
                    int score = Integer.parseInt(dane[1]);
                    ranking[indeks] = new Gracz();
                    ranking[indeks].setNick(nick);
                    ranking[indeks].setScore(score);
                    indeks++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void saveToFile(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("top10.txt"))){
            for(Gracz gracz : top10){
                if(gracz != null){
                    String line = gracz.getNick() + "," + gracz.getScore();
                    bw.write(line);
                    bw.newLine();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getTop10(){
        StringBuilder sb = new StringBuilder();
        sb.append("TOP 10 PLAYERS:\n");
        for (int i = 0; i < top10.length; i++) {
            Gracz gracz = top10[i];
            sb.append(i + 1).append(". ").append(gracz.getNick()).append(" - ").append(gracz.getScore()).append(" punktów\n").append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

}

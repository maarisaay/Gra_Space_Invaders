package GUI_p02;

public class Gracz {
    String nickname;
    int score;

    public void Gracz(String nickname, int score){
        this.nickname = nickname;
        this.score = score;
    }

    public void setNick(String nick){
        this.nickname = nick;
    }

    public String getNick(){
        return nickname;
    }

    public void setScore(int sc){
        this.score = sc;
    }

    public int getScore(){
        return score;
    }
}

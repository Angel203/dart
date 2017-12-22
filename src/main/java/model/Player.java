package model;


import lombok.Getter;
import lombok.Setter;

public class Player {

    private @Getter @Setter int id=-1;
    private @Getter String name="";
    private @Getter String surname="";
    private @Getter String nickname="";

    public Player (String name, String surname, String nickname)
    {
        this.name = name;
        this.surname = surname;
        this.nickname= nickname;
    }
    public Player(int id, String name, String surname, String nickname) {
        this( name, surname, nickname);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

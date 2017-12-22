package model;

import lombok.Getter;

public class Throws {
    private @Getter int id=-1;
    private @Getter int player_id=-1;
    private @Getter int value=-1;

    public Throws(int id, int player_id, int value){
        this.id = id;
        this.player_id = player_id;
        this.value = value;
    }
}

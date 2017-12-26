package model;

import lombok.Getter;

public class Set {
    private int id;
    @Getter
    private int wins_needed;

    public Set(int id, int wins_needed){
        this.id = id;
        this.wins_needed = wins_needed;
    }

    @Override
    public String toString() {
        return "Set{" +
                "id=" + id +
                ", wins_needed=" + wins_needed +
                '}';
    }
}

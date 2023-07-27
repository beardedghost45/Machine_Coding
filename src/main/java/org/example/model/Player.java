package org.example.model;

import lombok.Getter;

public class Player {

    @Getter
    private String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 0;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

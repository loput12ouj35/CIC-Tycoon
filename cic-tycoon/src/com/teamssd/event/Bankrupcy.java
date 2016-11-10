package com.teamssd.event;


import com.teamssd.GameContext;
import com.teamssd.scene.GameOver;


public class Bankrupcy implements Event {

    @Override
    public boolean check() {
        return (GameContext.f.get_Money() < 0);
    }

    @Override
    public double getProb() {
        return 1.0;
    }

    @Override
    public void run() {
        GameContext.setScene(new GameOver());
    }

}

package com.teamssd.ui;


import com.badlogic.gdx.Gdx;


public class TimedMessageBox implements MessageBox {
    private float proc_time;
    private String str;
    
    private float end_time;
    
    public TimedMessageBox(String str) {
        this.proc_time = 0;
        this.str = str;
	
        this.end_time = 3f;
    }
    
    @Override
    public boolean closed() {
        if (proc_time > end_time) {
            return true;
        }
	
        return false;
    }

    @Override
    public String content() {
        proc_time += Gdx.graphics.getDeltaTime();
	
        return this.str;
    }
}

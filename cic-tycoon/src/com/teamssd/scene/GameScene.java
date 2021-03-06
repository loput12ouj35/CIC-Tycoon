package com.teamssd.scene;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamssd.GameContext;
import com.teamssd.ui.Menu;
import com.teamssd.ui.StatusBar;
import com.teamssd.ui.SubMenu.BUTTON;


public class GameScene implements Scene {
    private StatusBar	status;
    private Menu		menu;
    private Music		bgm;
	
    private boolean		stop;
	
    public GameScene() {
        status = new StatusBar();
        menu = new Menu();
        bgm = Gdx.audio.newMusic(Gdx.files.internal("data/bgm.mp3"));
		
        bgm.setLooping(true);
        bgm.play();
    }

    @Override
    public void render(SpriteBatch batch) {
        GameContext.b.render(batch);
		
        status.render(batch);
        menu.render(batch);
    }

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        BUTTON b;
	    	
        switch (button) {
        case Buttons.LEFT:
            b = BUTTON.LEFT;
            break;

        default:
            b = BUTTON.RIGHT;
        }
	    
        if (menu.click(screenX, screenY, b)) {
            return true;
        }
		
        if (GameContext.b.click(screenX, screenY)) {
            return true;
        }
		
        if (GameContext.b.destruct(screenX, screenY)) {
            return true;
        }
		
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (screenX < 0) {
            Gdx.input.setCursorPosition(0,
                    (int) (GameContext.h - Gdx.input.getY(0)));
        }
	    	
        if (screenX > GameContext.w) {
            Gdx.input.setCursorPosition((int) GameContext.w,
                    (int) (GameContext.h - Gdx.input.getY(0)));
        }
	    	
        if (screenY < 0) {
            Gdx.input.setCursorPosition(Gdx.input.getX(0), (int) GameContext.h);
        }
	    	
        if (screenY > GameContext.h) {
            Gdx.input.setCursorPosition(Gdx.input.getX(0), 0);
        }
	    
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return menu.scroll(amount);
    }

    @Override
    public void set() {
        bgm.play();
	    
        stop = false;
	    
        GameContext.start(this);
	    
        new Thread(GameContext.t).start();
    }

    @Override
    public void unset() {
        stop = false;
	    
        bgm.stop();
    }

    public boolean stopped() {
        return stop;
    }
}

package com.teamssd.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamssd.GameContext;
import com.teamssd.GameContext.CURSOR;
import com.teamssd.building.room.Buildable;
import com.teamssd.building.room.Class1x1;
import com.teamssd.building.room.Class1x2;
import com.teamssd.building.room.Class2x1;
import com.teamssd.building.room.Lab;
import com.teamssd.building.room.Lounge;
import com.teamssd.building.room.Room;


public class Build implements SubMenu {
    private enum State {
        MSHOW, MBUILD,
    }
	
    private State state;
    private Buildable cur;
	
    private Sprite line;
    private Sprite white;
	
    private Sprite c11;
    private Sprite c12;
    private Sprite c21;
	
    private Sprite l11;
    private Sprite L2;
	
    private Sprite E;
	
    public Build() {
        line = new Sprite(new Texture(Gdx.files.internal("data/line.png")));
        white = new Sprite(new Texture(Gdx.files.internal("data/white.png")));
		
        line.setPosition(0, GameContext.h - 170);
        line.setSize(GameContext.w, 1);
		
        white.setPosition(0, GameContext.h - 170);
        white.setSize(GameContext.w, 95);
		
        c11 = GameContext.sprite("data/build/c11.png");
        c11.setPosition(0 + 10, GameContext.h - 170 + 10);
		
        c12 = GameContext.sprite("data/build/c12.png");
        c12.setPosition(75 + 10, GameContext.h - 170 + 10);
		
        c21 = GameContext.sprite("data/build/c21.png");
        c21.setPosition(150 + 10, GameContext.h - 170 + 10);

        l11 = GameContext.sprite("data/build/l11.png");
        l11.setPosition(225 + 10, GameContext.h - 170 + 10);
		
        L2 = GameContext.sprite("data/build/L2.png");
        L2.setPosition(300 + 10, GameContext.h - 170 + 10);
		
        E = GameContext.sprite("data/build/extend.png");
        E.setPosition(375 + 10, GameContext.h - 170 + 10);
    }
	
    private void drawCenter(BitmapFont f, int offset, String str, SpriteBatch batch) {
        float x = 75 * offset + 10 + 30;
        float y = GameContext.h - 170 + 75;
	    
        x -= f.getBounds(str).width / 2;
	    
        f.draw(batch, str, x, y);
    }
	
    private void renderShow(SpriteBatch batch) {
        BitmapFont f = GameContext.font;
	    
        white.draw(batch);
        line.draw(batch);
        c11.draw(batch);
        c12.draw(batch);
        c21.draw(batch);
        l11.draw(batch);
        L2.draw(batch);
        E.draw(batch);
		
        drawCenter(f, 0, "$10", batch);
        drawCenter(f, 1, "$20", batch);
        drawCenter(f, 2, "$20", batch);
        drawCenter(f, 3, "$7", batch);
        drawCenter(f, 4, "$12", batch);
        drawCenter(f, 5,
                "$"
                + ((int) GameContext.f.get_Cost_make_floor(
                        GameContext.b.maxFloor())),
                        batch);
    }

    @Override
    public void render(SpriteBatch batch) {
        switch (state) {
        case MSHOW:
            renderShow(batch);
            break;

        case MBUILD:
            break;
        }
    }
	
    private int calcID(int x, int y) {
        if (y < 30 || y > GameContext.h - 75) {
            return -1;
        }
		
        y -= 30;
		
        int idx = x / 215;
        int idy = y / 145;
		
        System.out.println(idx + " " + idy);
		
        return idy * 4 + idx;
    }
	
    private boolean click_mshow(int x, int y) {
        if (y < GameContext.h - 150) {
            return false;
        }
		
        switch (x / 75) {
        case 0:
            cur = new Class1x1();
            break;

        case 1:
            cur = new Class1x2();
            break;

        case 2:
            cur = new Class2x1();
            break;

        case 3:
            cur = new Lab();
            break;

        case 4:
            cur = new Lounge();
            break;

        case 5:
            GameContext.b.extend();
            return true;

        default:
            return false;
        }
		
        state = State.MBUILD;
        GameContext.setCursor(CURSOR.BUILD);
		
        return false;
    }
	
    private boolean click_mbuild(int x, int y, BUTTON b) {
        if (b == BUTTON.RIGHT) {
            return true;
        }
	    
        int id = calcID(x, y);

        System.out.println("ddd");
		
        if (id < 0) {
            return false;
        }

        Room r = cur.build(id);
		
        if (!Room.check(r)) {
            return false;
        }
		
        GameContext.b.getFloor().build(r);
		
        return false;
    }

    @Override
    public boolean click(int x, int y, BUTTON b) {
        switch (state) {
        case MSHOW:
            return click_mshow(x, y);

        case MBUILD:
            return click_mbuild(x, y, b);
        }
		
        return false;
    }

    @Override
    public void show() {
        state = State.MSHOW;
    }

    @Override
    public void hide() {
        GameContext.setCursor(CURSOR.NORMAL);
    }

    @Override
    public boolean scroll(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

}

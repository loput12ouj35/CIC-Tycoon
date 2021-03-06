package com.teamssd.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamssd.GameContext;
import com.teamssd.Renderable;
import com.teamssd.ui.SubMenu.BUTTON;


public class Menu implements Renderable {
    private enum State {
        NONE, BUILD, EMPLOYEE, FINANCE,
    }
	
    private State state;
    private Sprite line;
	
    private Sprite uup;
    private Sprite up;
    private Sprite ddown;
    private Sprite down;
	
    private Sprite[] buttons;
	
    private SubMenu subBuild;
    private SubMenu subFinance;
    private SubMenu subEmployee;
	
    private SubMenu curMenu;
	
    public Menu() {
        state = State.NONE;
		
        line = new Sprite(new Texture(Gdx.files.internal("data/line.png")));
        line.setPosition(0, GameContext.h - 75);
        line.setSize(GameContext.w, 1);
		
        Sprite[] buttons = {
            GameContext.sprite("data/build.png"),
            GameContext.sprite("data/employee.png"),
            GameContext.sprite("data/finance.png"),
        };
		
        for (int i = 0; i < buttons.length; ++i) {
            buttons[i].setPosition(i * 150 + 10, GameContext.h - 67);
        }
		
        this.buttons = buttons;
        ;
		
        subBuild = new Build();
        subFinance = new Finance();
        subEmployee = new Employee();
		
        up = GameContext.sprite("data/up.png");
        uup = GameContext.sprite("data/uup.png");
        down = GameContext.sprite("data/down.png");
        ddown = GameContext.sprite("data/ddown.png");
		
        up.setPosition(680, GameContext.h - 67);
        uup.setPosition(750, GameContext.h - 67);
        down.setPosition(560, GameContext.h - 67);
        ddown.setPosition(490, GameContext.h - 67);
    }
	
    private boolean click_none(int x, int y) {
        if (y < GameContext.h - 75) {
            return false;
        }
		
        if (up.getBoundingRectangle().contains(x, y)) {
            GameContext.b.move(1);
            return true;
        }
		
        if (down.getBoundingRectangle().contains(x, y)) {
            GameContext.b.move(-1);
            return true;
        }
		
        if (uup.getBoundingRectangle().contains(x, y)) {
            GameContext.b.move(5);
            return true;
        }
		
        if (ddown.getBoundingRectangle().contains(x, y)) {
            GameContext.b.move(-5);
            return true;
        }
		
        int offset = x / 150;
		
        switch (offset) {
        case 0:
            state = State.BUILD;
            subBuild.show();
            curMenu = subBuild;
            return true;

        case 1:
            state = State.EMPLOYEE;
            subEmployee.show();
            curMenu = subEmployee;
            return true;

        case 2:
            state = State.FINANCE;
            subFinance.show();
            curMenu = subFinance;
            return true;

        default:
            state = State.NONE;
            return true;
        }		
    }
	
    private boolean click_sub(int x, int y, BUTTON b) {
        if (y > GameContext.h - 75) {
            subBuild.hide();
            state = State.NONE;
            return true;
        }
		
        if (curMenu.click(x, y, b)) {
            curMenu.hide();
            state = State.NONE;
            return true;
        }
		
        return true;
    }
	
    public boolean click(int x, int y, BUTTON b) {
        System.out.println("wahaha");
		
        switch (state) {
        case NONE:
            System.out.println("n");
            return click_none(x, y);

        case BUILD:
        case EMPLOYEE:
        case FINANCE:
            System.out.println("b");
            return click_sub(x, y, b);

        default:
            break;
        }
		
        return false;
    }
	
    private void renderFloor(SpriteBatch batch) {
        String floorId = GameContext.b.curFloor() + "/"
                + GameContext.b.maxFloor();
	    
        GameContext.font.draw(batch, floorId, 640, GameContext.h - 45);
	    
        up.draw(batch);
        uup.draw(batch);
        down.draw(batch);
        ddown.draw(batch);
    }

    @Override
    public void render(SpriteBatch batch) {
        line.draw(batch);
		
        for (Sprite s : buttons) {
            s.draw(batch);
        }
		
        renderFloor(batch);
		
        switch (state) {
        case BUILD:
        case EMPLOYEE:
        case FINANCE:
            curMenu.render(batch);
            break;

        default:
            break;
        }
    }

    public boolean scroll(int amount) {
        switch (state) {
        case BUILD:
        case EMPLOYEE:
        case FINANCE:
            System.out.println("b");
            return curMenu.scroll(amount);

        default:
            break;
        }
		
        return false;
    }
}

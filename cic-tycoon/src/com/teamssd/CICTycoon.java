package com.teamssd;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class CICTycoon implements ApplicationListener {
    private SpriteBatch batch;
    private OrthographicCamera camera;
	
    @Override
    public void create() {		
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
		
        GameContext.init();
		
        camera.position.set(400, 300, 0);
        camera.setToOrtho(true, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
		
        Gdx.input.setCursorCatched(true);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void render() {		
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        GameContext.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}

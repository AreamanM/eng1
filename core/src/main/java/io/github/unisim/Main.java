package io.github.unisim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter implements InputProcessor {
    private SpriteBatch batch;
    private Texture image;
    private Texture accom;
    private int mouseX;
    private int mouseY;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        image = new Texture("map.jpg");
        accom = new Texture("accom.jpg");
        mouseX = -1;
        mouseY = -1;
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLUE);
        batch.begin();
        batch.draw(image, 0, 0);
        if (mouseX >= 0 && mouseY >= 0) {
            batch.draw(accom, mouseX, mouseY);
        }
        batch.end();
}

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    // InputProcessor
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mouseX = screenX;
        // flip y because opengl
        mouseY = Gdx.graphics.getHeight() - screenY;
        return true; // Returning true means the event was handled
    }

    @Override public boolean keyDown(int keycode) { return false; }
    @Override public boolean keyUp(int keycode) { return false; }
    @Override public boolean keyTyped(char character) { return false; }
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    @Override public boolean touchCancelled(int a, int b, int c, int d) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(float amountX, float amountY) { return false; }

}

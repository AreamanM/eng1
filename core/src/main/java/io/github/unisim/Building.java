package io.github.unisim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Building {
    private Texture texture;
    private String type;
    private float x, y;
    private float scale;

    public Building(String type, Texture texture, float x, float y, float scale) {
        this.type = type;
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    public String getType() {
        return type;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, texture.getWidth() * scale, texture.getHeight() * scale);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean contains(float clickX, float clickY) {
        return clickX >= x && clickX <= x + texture.getWidth() * scale &&
            clickY >= y && clickY <= y + texture.getHeight() * scale;
    }

    public void dispose() {
        texture.dispose();
    }
}

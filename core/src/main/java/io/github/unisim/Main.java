package io.github.unisim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter implements InputProcessor {
    private SpriteBatch batch;
    private Texture mapTexture;
    private BuildingManager buildingManager;
    private int mouseX, mouseY;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        mapTexture = new Texture("map.jpg");
        buildingManager = new BuildingManager();
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLUE);
        batch.begin();
        batch.draw(mapTexture, 0, 0); // Drawing the png map
        buildingManager.render(batch); // Draw all buildings
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        mapTexture.dispose();
        buildingManager.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mouseX = screenX;
        mouseY = Gdx.graphics.getHeight() - screenY; // Flip the Y-axis coordinates

        if (button == Input.Buttons.LEFT) { // Left click to place the building
            if (!buildingManager.isAllBuildingsPlaced()) { // Check if there are any unplaced buildings
                Building newBuilding = buildingManager.createBuilding(mouseX, mouseY);
                if (newBuilding != null) { // Building created successfully
                    buildingManager.addBuilding(newBuilding);
                }
            }
        } else if (button == Input.Buttons.RIGHT) { // Right click to delete the building
            Building buildingToRemove = buildingManager.getBuildingAt(mouseX, mouseY);
            if (buildingToRemove != null) {
                buildingManager.removeBuilding(buildingToRemove);
            }
        }
        return true;
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

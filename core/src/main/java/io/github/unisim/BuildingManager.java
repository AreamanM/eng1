package io.github.unisim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.HashMap;

public class BuildingManager {
    private ArrayList<Building> buildings = new ArrayList<>();  // Saves all placed building objects
    private HashMap<String, Texture> textures = new HashMap<>(); // Store textures for different types of buildings
    private HashMap<String, Float> scales = new HashMap<>(); // Stores the scale of different building types
    private String[] buildingTypes = {"classroom", "dorm", "cafeteria"}; // A fixed order of building types
    private int currentBuildingIndex = 0;  // Index of the type of building currently to be placed
    private boolean allBuildingsPlaced = false; // Whether all buildings have been placed

    public BuildingManager() {
        // Load the texture of the building
        textures.put("classroom", new Texture("classroom.png"));
        textures.put("dorm", new Texture("dorm.png"));
        textures.put("cafeteria", new Texture("cafeteria.png"));

        // Set the scale of each building
        scales.put("classroom", 0.3f);
        scales.put("dorm", 0.4f);
        scales.put("cafeteria", 0.2f);
    }

    public Building createBuilding(float x, float y) {
        if (allBuildingsPlaced) {
            return null; // All buildings are placed and no new buildings are created
        }

        String type = buildingTypes[currentBuildingIndex];
        Texture texture = textures.get(type);
        float scale = scales.getOrDefault(type, 0.3f);

        Building newBuilding = new Building(type, texture, x, y, scale);

        currentBuildingIndex++;
        if (currentBuildingIndex >= buildingTypes.length) {
            allBuildingsPlaced = true; // All buildings have been placed
        }

        return newBuilding;
    }

    public boolean isAllBuildingsPlaced() {
        return allBuildingsPlaced;
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
        // Do not release textures after deleting buildings
        allBuildingsPlaced = false; // Ensure that users can continue to install the deleted buildings

        // Update 'currentBuildingIndex' to the deleted building type index
        currentBuildingIndex = java.util.Arrays.asList(buildingTypes).indexOf(building.getType());
        if (currentBuildingIndex == -1) {
            currentBuildingIndex = 0; // If a type is not found, the default is to start over from the first type
        }
    }

    public Building getBuildingAt(float x, float y) {
        for (Building building : buildings) {
            if (building.contains(x, y)) {
                return building;
            }
        }
        return null;
    }

    public void render(SpriteBatch batch) {
        for (Building building : buildings) {
            building.render(batch);
        }
    }

    public void dispose() {
        for (Texture texture : textures.values()) {
            texture.dispose();
        }
        for (Building building : buildings) {
            // building.dispose(); // There is no need to release textures individually
        }
    }
}

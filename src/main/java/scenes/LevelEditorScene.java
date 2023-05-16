package scenes;

import components.Rigidbody;
import components.Sprite;
import components.SpriteRenderer;
import components.Spritesheet;
import imgui.ImGui;
import imgui.ImVec2;
import jade.*;
import org.joml.Vector2f;
import org.joml.Vector4f;
import util.AssetPool;

public class LevelEditorScene extends Scene {

    private GameObject object;
    private Spritesheet sprites;
    //SpriteRenderer object1Sprite;

    public LevelEditorScene() {

    }

    @Override
    public void init() {
        loadResources();

        this.camera = new Camera(new Vector2f(-250, 0));
        sprites = AssetPool.getSpritesheet("assets/images/spritesheets/decorationsAndBlocks.png");

        if (levelLoaded) {
            this.activeGameObject = gameObjects.get(0);
            return;
        }

        // WARNING: use AssetPool.getTexture() instead of new Texture() to make sure performance don't break

        object = new GameObject("Object 1",
                new Transform(new Vector2f(200, 100), new Vector2f(256, 256)), 3);
        SpriteRenderer objectSprite = new SpriteRenderer();
        objectSprite.setColor(new Vector4f(1f, 0f, 0f, 1f));
        object.addComponent(objectSprite);
        object.addComponent(new Rigidbody());
        this.addGameObjectToScene(object);

        GameObject object1 = new GameObject("Object 2",
                new Transform(new Vector2f(400, 100), new Vector2f(256, 256)), 2);
        SpriteRenderer object1SpriteRenderer = new SpriteRenderer();
        Sprite object1Sprite = new Sprite();
        object1Sprite.setTexture(AssetPool.getTexture("assets/images/blendImage1.png"));
        object1SpriteRenderer.setSprite(object1Sprite);
        object1.addComponent(object1SpriteRenderer);
        this.addGameObjectToScene(object1);

    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpritesheet("assets/images/spritesheets/decorationsAndBlocks.png", new Spritesheet(
                AssetPool.getTexture("assets/images/spritesheets/decorationsAndBlocks.png"),
                16, 16, 81, 0));

        AssetPool.getTexture("assets/images/blendImage1.png");
    }

    @Override
    public void update(float dt) {
        //System.out.println("FPS: " + (1.0f / dt));

        MouseListener.getOrthoX();

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }

    @Override
    public void imgui() {
        ImGui.begin("Test window");

        ImVec2 windowPos = new ImVec2();
        ImGui.getWindowPos(windowPos);
        ImVec2 windowSize = new ImVec2();
        ImGui.getWindowSize(windowSize);
        ImVec2 itemSpacing = new ImVec2();
        ImGui.getStyle().getItemSpacing(itemSpacing);

        float windowX2 = windowPos.x + windowSize.x;
        for (int i = 0; i < sprites.size(); i ++) {
            Sprite sprite = sprites.getSprite(i);
            float spriteWidth = sprite.getWidth() * 2;
            float spriteHeight = sprite.getHeight() * 2;
            int id = sprite.getTexId();
            Vector2f[] texCoords = sprite.getTexCoords();

            ImGui.pushID(i);
            if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[0].x, texCoords[0].y, texCoords[2].x, texCoords[2].y)) {
                System.out.println("Button " + i + " clicked");
            }
            ImGui.popID();

            ImVec2 lastButtonPos = new ImVec2();
            ImGui.getItemRectMax(lastButtonPos);
            float lastButtonX2 = lastButtonPos.x;
            float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
            if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                ImGui.sameLine();
            }
        }

        ImGui.end();
    }
}
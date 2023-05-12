package jade;

import components.Sprite;
import components.SpriteRenderer;
import components.Spritesheet;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Texture;
import util.AssetPool;

public class LevelEditorScene extends Scene {

    private GameObject object;
    private Spritesheet sprites;

    public LevelEditorScene() {

    }

    @Override
    public void init() {
        loadResources();

        this.camera = new Camera(new Vector2f(0, 0));

        sprites = AssetPool.getSpritesheet("assets/images/spritesheet.png");

        GameObject object1 = new GameObject("Object 2",
                new Transform(new Vector2f(400, 100), new Vector2f(256, 256)), 2);
        object1.addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture("assets/images/blendImage1.png"))));
        this.addGameObjectToScene(object1);

        object = new GameObject("Object 1",
                new Transform(new Vector2f(200, 100), new Vector2f(256, 256)), 2);
        object.addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture("assets/images/blendImage2.png"))));
        this.addGameObjectToScene(object);

        GameObject object2 = new GameObject("Object 3",
                new Transform(new Vector2f(10, 100), new Vector2f(256, 256)), 1);
        object2.addComponent(new SpriteRenderer(sprites.getSprite(0)));
        this.addGameObjectToScene(object2);

    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpritesheet("assets/images/spritesheet.png", new Spritesheet(
                AssetPool.getTexture("assets/images/spritesheet.png"),
                16, 16, 25, 1));
    }

    @Override
    public void update(float dt) {
        //System.out.println("FPS: " + (1.0f / dt));

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }
}
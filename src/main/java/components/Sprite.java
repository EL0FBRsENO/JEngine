package components;

import lombok.Setter;
import org.joml.Vector2f;
import renderer.Texture;

public class Sprite extends Component {

    private float width, height;

    private @Setter Texture texture = null;
    private @Setter Vector2f[] texCoords = {
            new Vector2f(1, 1),
            new Vector2f(1, 0),
            new Vector2f(0, 0),
            new Vector2f(0, 1)
    };

    public Texture getTexture() {
        return this.texture;
    }

    public Vector2f[] getTexCoords() {
        return this.texCoords;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getTexId(){
        return texture == null ? -1 : texture.getId();
    }
}

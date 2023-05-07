package jade;

abstract public class Scene {

    protected Camera camera;

    public Scene() {

    }

    public void init() {

    }

    public abstract void update(float dt);

}

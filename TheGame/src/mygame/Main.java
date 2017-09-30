package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    
    OurPlayer player = new OurPlayer();
    private BulletAppState bulletAppState;

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(100);
        
        // Activate physics
        bulletAppState = new BulletAppState();
        // Attach it to the SimpleApplication’s AppState manager
        stateManager.attach(bulletAppState);
        
        // Load islands
        Node islandsNode = new Node();
        rootNode.attachChild(islandsNode);
        LoadScene scene = new LoadScene(assetManager);
        bulletAppState.getPhysicsSpace().add(scene.loadIsland1(islandsNode));
        bulletAppState.getPhysicsSpace().add(scene.loadIsland2(islandsNode));
        bulletAppState.getPhysicsSpace().add(scene.loadIsland3(islandsNode));
        bulletAppState.getPhysicsSpace().add(scene.loadIsland4(islandsNode));
        bulletAppState.getPhysicsSpace().add(scene.loadIsland5(islandsNode));

   
        // Light to see the scene
        DirectionalLight dl = new DirectionalLight();
        dl.setColor(ColorRGBA.White);
        dl.setDirection(new Vector3f(2.8f, -2.8f, -2.8f).normalizeLocal());
        rootNode.addLight(dl);
        
        // Some objects for the player to collide with
        Node collidables = new Node();
        rootNode.attachChild(collidables);
        bulletAppState.getPhysicsSpace().add(scene.simpleBox(2, 1, 0, collidables));
        scene.simpleBox(-1, 2, -60, collidables);
        scene.simpleBox(5, 0, -120, collidables);
        scene.simpleBox(6, 4, -180, collidables);
        scene.simpleBox(6, 2, -240, collidables); 
        //bulletAppState.getPhysicsSpace().add(map.loadOurScene(rootNode));
      
        // Add player to the physicsSpace         //init player()
        bulletAppState.getPhysicsSpace().add(player.initPlayer(viewPort, flyCam));
        player.setUpKeys(inputManager);
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        player.simpleUpdate(tpf, cam, listener);
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}

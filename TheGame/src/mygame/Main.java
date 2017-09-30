package mygame;

import com.jme3.app.SimpleApplication;
<<<<<<< HEAD
import com.jme3.asset.AssetManager;
=======
import com.jme3.audio.AudioNode;
>>>>>>> 4d9ddbb7624fb91a437d0f5bc43d1b012eb1dbf5
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.collision.CollisionResults;
import com.jme3.input.ChaseCamera;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.LodControl;
import com.jme3.scene.shape.Box;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    //OurPlayer player1 = new OurPlayer();
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    

    OurPlayer player = new OurPlayer(assetManager);
    private BulletAppState bulletAppState;

    @Override
    public void simpleInitApp() {
        
        Box b = new Box(1, 1, 1); // create cube shape
        Geometry geom = new Geometry("Box", b);  // create cube geometry from the shape
        Material mat = new Material(assetManager,
          "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        mat.setColor("Color", ColorRGBA.Blue);   // set color of material to blue
        geom.setMaterial(mat);                   // set the cube's material
        rootNode.attachChild(geom);              // make the cube 
        
       //Attaches the Geomtry to the chase camera.
        ChaseCamera chaseCam = new ChaseCamera(cam,geom,inputManager);
        
        //Really need to figure out how to get it mapping to the inputManager, tried all night and i'am stumped HAZ
        //Goodluck.
        
        geom.setLocalTranslation(2,2,-20);
        flyCam.setEnabled(false);
        flyCam.setMoveSpeed(100);
        chaseCam.setSmoothMotion(true);
        chaseCam.setMaxDistance(10);
        chaseCam.setMinVerticalRotation(-FastMath.PI / 2);
        viewPort.setBackgroundColor(ColorRGBA.DarkGray);
        
        
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        BloomFilter bf = new BloomFilter(BloomFilter.GlowMode.Objects);
        bf.setBloomIntensity(2.0f);
        bf.setExposurePower(1.3f);
        fpp.addFilter(bf);
        viewPort.addProcessor(fpp);
        
        
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
        CharacterControl wot = player.initPlayer(viewPort, flyCam);
        bulletAppState.getPhysicsSpace().add(wot);
        player.setUpKeys(inputManager);
        
        // Play Music, Just a place holder until we get Nic's music
        Audio audio = new Audio(assetManager);
        audio.playEnviro("Sounds/SC.ogg");
        
        
        
        Node playersNode = new Node();
        
        
        CollisionResults results = new CollisionResults();
        //collidables.collideWith(playersNode, results);
        
        
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

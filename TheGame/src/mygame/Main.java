package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.collision.CollisionResults;
import com.jme3.cursors.plugins.JmeCursor;
import com.jme3.font.BitmapText;
import com.jme3.input.ChaseCamera;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
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
import com.jme3.ui.Picture;
import java.util.ArrayList;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication implements AnalogListener,ActionListener {
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    
    private ArrayList<JmeCursor> cursors = new ArrayList<JmeCursor>();
    private long sysTime;
    private int count = 0;
    protected int score = 0;
    public Geometry playerGeom;
    private ChaseCamera chaseCam;
    //OurPlayer player = new OurPlayer(assetManager);
    private BulletAppState bulletAppState;

    @Override
    public void simpleInitApp() {
        
        /*Box b = new Box(1, 1, 1); // create cube shape
        Geometry geom = new Geometry("Box", b);  // create cube geometry from the shape
        Material mat = new Material(assetManager,
          "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        mat.setColor("Color", ColorRGBA.Blue);   // set color of material to blue
        geom.setMaterial(mat);                   // set the cube's material
        rootNode.attachChild(geom);              // make the cube 
        */
       
        playerGeom = (Geometry) assetManager.loadModel("Models/Teapot/Teapot.obj");
        Material mat_tea = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        playerGeom.setMaterial(mat_tea);
        rootNode.attachChild(playerGeom);
        flyCam.setEnabled(false);
        chaseCam = new ChaseCamera(cam, playerGeom, inputManager);
        chaseCam.setSmoothMotion(true);
        chaseCam.setMaxDistance(10);
        chaseCam.setMinVerticalRotation(-FastMath.PI / 2);
        //flyCam.setEnabled(false);
        //flyCam.setMoveSpeed(100);
            
           /* Cursor Control */
            inputManager.setCursorVisible(true);
        //cursors.add((JmeCursor) assetManager.loadAsset(""));
        //cursors.add((JmeCursor) assetManager.loadAsset(""));
        //cursors.add((JmeCursor) assetManager.loadAsset(""));

        //sysTime = System.currentTimeMillis();
               //inputManager.setMouseCursor(cursors.get(count));
               
        
        //Turns off Diagnostics
        
        //setDisplayStatView(false);
        setDisplayFps(false);
        
        //Addas Test HUD Overlay for future implentation call variable in update loop and set state.
        //For both Score and Health, any modifications needed can just be run from there make sure to set picture state.
       Picture Health = new Picture("Health Bar");
        Health.setImage(assetManager, "HUD/HealthBar.png", true);
        Health.setWidth(settings.getWidth()/4);
        Health.setHeight(settings.getHeight()/4);
        Health.setPosition(settings.getWidth()/9, settings.getHeight()/1);
        Health.setLocalTranslation(5f,5f,0f);
        guiNode.attachChild(Health); 
        
        
        BitmapText Score = new BitmapText(guiFont, false);          
        Score.setSize(guiFont.getCharSet().getRenderedSize());      // font size
        Score.setColor(ColorRGBA.Blue);                             // font color
        Score.setText("Score " + score);             // the text
        Score.setLocalTranslation(100, Score.getLineHeight(), 0); // position
        guiNode.attachChild(Score);
        
        
        flyCam.setEnabled(false);
        flyCam.setMoveSpeed(100);
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
        //CharacterControl wot = player.initPlayer(viewPort, flyCam);
        //bulletAppState.getPhysicsSpace().add(wot);
        //player.setUpKeys(inputManager);
        
        // Play Music, Just a place holder until we get Nic's music
        Audio audio = new Audio(assetManager);
        audio.playEnviro("Sounds/pi.ogg");
        
        
        
        //Node playersNode = new Node();
        
        
        CollisionResults results = new CollisionResults();
        //collidables.collideWith(playersNode, results);
        
        
        registerInput();
        
    }
    
    public void registerInput() {
    inputManager.addMapping("moveForward", new KeyTrigger(keyInput.KEY_UP), new KeyTrigger(keyInput.KEY_W));
    inputManager.addMapping("moveBackward", new KeyTrigger(keyInput.KEY_DOWN), new KeyTrigger(keyInput.KEY_S));
    inputManager.addMapping("moveRight", new KeyTrigger(keyInput.KEY_RIGHT), new KeyTrigger(keyInput.KEY_D));
    inputManager.addMapping("moveLeft", new KeyTrigger(keyInput.KEY_LEFT), new KeyTrigger(keyInput.KEY_A));
    inputManager.addMapping("displayPosition", new KeyTrigger(keyInput.KEY_P));
    inputManager.addMapping("Jump", new KeyTrigger(keyInput.KEY_SPACE));
    inputManager.addListener(this, "moveForward", "moveBackward", "moveRight", "moveLeft", "Jump");
    inputManager.addListener(this, "displayPosition");
  }

  public void onAnalog(String name, float value, float tpf) {
    if (name.equals("moveForward")) {
      playerGeom.move(0, 0, -5 * tpf);
    }
    if (name.equals("moveBackward")) {
      playerGeom.move(0, 0, 5 * tpf);
    }
    if (name.equals("moveRight")) {
      playerGeom.move(5 * tpf, 0, 0);
    }
    if (name.equals("moveLeft")) {
      playerGeom.move(-5 * tpf, 0, 0);
      
   if(name.equals("Jump")) {
       //playerGeom.
   }

    }

  }

  public void onAction(String name, boolean keyPressed, float tpf) {
    if (name.equals("displayPosition") && keyPressed) {
      playerGeom.move(10, 10, 10);

    }
  }
    

    @Override
    public void simpleUpdate(float tpf) {

        //player.simpleUpdate(tpf, cam, listener);
        super.simpleUpdate(tpf);
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}

package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.ui.Picture;

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
    
    OurPlayer player = new OurPlayer();
    private BulletAppState bulletAppState;
    public static Geometry boxFoot;
    protected int score = 0;
    CollisionResults results;
    Ray ray;
    Node playersNode;
    Node collidables;
    

    @Override
    public void simpleInitApp() {
        
        initCrossHairs();
        flyCam.setMoveSpeed(100);
        
        // Activate physics
        bulletAppState = new BulletAppState();
        // Attach it to the SimpleApplication’s AppState manager
        stateManager.attach(bulletAppState);
        
        // Load islands
        Node islandsNode = new Node();
        rootNode.attachChild(islandsNode);
        LoadScene scene = new LoadScene(assetManager);
        /*        bulletAppState.getPhysicsSpace().add(scene.loadIsland1(islandsNode));
        bulletAppState.getPhysicsSpace().add(scene.loadIsland2(islandsNode));
        bulletAppState.getPhysicsSpace().add(scene.loadIsland3(islandsNode));
        bulletAppState.getPhysicsSpace().add(scene.loadIsland4(islandsNode));*/
        bulletAppState.getPhysicsSpace().add(scene.loadIsland5(islandsNode));
   
        // Light to see the scene
        DirectionalLight dl = new DirectionalLight();
        dl.setColor(ColorRGBA.White);
        dl.setDirection(new Vector3f(2.8f, -2.8f, -2.8f).normalizeLocal());
        rootNode.addLight(dl);
        
        //HUD
        
        Picture hpBar = new Picture("Health Bar");
        hpBar.setImage(assetManager, "Textures/HealthBar.png", true);
        hpBar.setWidth(settings.getWidth()/4);
        hpBar.setHeight(settings.getHeight()/4);
        hpBar.setPosition(settings.getWidth()/5, settings.getHeight()/5);
        guiNode.attachChild(hpBar);
        
        BitmapText scoreDisplay = new BitmapText(guiFont, false);          
        scoreDisplay.setSize(guiFont.getCharSet().getRenderedSize());      // font size
        scoreDisplay.setColor(ColorRGBA.Blue);                             // font color
        scoreDisplay.setText("Score: "+ score );             // the text
        scoreDisplay.setLocalTranslation(500, scoreDisplay.getLineHeight(), 5); // position
        guiNode.attachChild(scoreDisplay);
        
        
        
        // Some objects for the player to collide with
        collidables = new Node();
        rootNode.attachChild(collidables);
        bulletAppState.getPhysicsSpace().add(scene.simpleBox(2, 1, 0, collidables));
        bulletAppState.getPhysicsSpace().add(scene.simpleBox(-1, 2, -60, collidables));
        bulletAppState.getPhysicsSpace().add(scene.simpleBox(5, 0, -120, collidables));
        bulletAppState.getPhysicsSpace().add(scene.simpleBox(6, 4, -180, collidables));
        bulletAppState.getPhysicsSpace().add(scene.simpleBox(6, 2, -240, collidables)); 
        //bulletAppState.getPhysicsSpace().add(map.loadOurScene(rootNode));
      
        
        // Add player to the physicsSpace         //init player()
        CharacterControl wot = player.initPlayer(viewPort, flyCam);
        bulletAppState.getPhysicsSpace().add(wot);
        player.setUpKeys(inputManager);
        
        // Play Music
        Audio audio = new Audio(assetManager);
        audio.playEnviro("Sounds/pi.ogg");
        
        
        
         playersNode = new Node();
        playersNode.setLocalTranslation(0f, 0f, 0f);
        //rootNode.attachChild(playersNode);
        playersNode.addControl(wot);
        
        // Box at the players foot for detecting collisions
        Box box = new Box(1f, 1f, 1f);
        boxFoot = new Geometry("boxFoot", box);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.randomColor());
        boxFoot.setMaterial(mat);
        playersNode.attachChild(boxFoot);
        CollisionShape colShape = CollisionShapeFactory.createMeshShape(boxFoot);
        RigidBodyControl boxRigid = new RigidBodyControl(colShape, 0);
        boxFoot.addControl(boxRigid);
        bulletAppState.getPhysicsSpace().add(boxFoot);
        
        
        
        
        
        

        
    }
        
        
      protected void initCrossHairs() {
        setDisplayStatView(false);
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText ch = new BitmapText(guiFont, false);
        ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        ch.setText("+"); // crosshairs
        ch.setLocalTranslation( // center
      settings.getWidth() / 2 - ch.getLineWidth()/2, settings.getHeight() / 2 + ch.getLineHeight()/2, 0);
    guiNode.attachChild(ch);
  }


    @Override
    public void simpleUpdate(float tpf) {
        player.simpleUpdate(tpf, cam, listener);
        
        ray = new Ray(cam.getLocation(), cam.getDirection());
        // Print the results so we see what is going on
        results = new CollisionResults();
         for (int i = 0; i < results.size(); i++) {
           // For each “hit”, we know distance, impact point, geometry.
           float dist = results.getCollision(i).getDistance();
           Vector3f pt = results.getCollision(i).getContactPoint();
           String target = results.getCollision(i).getGeometry().getName();
           System.out.println("Selection #" + i + ": " + target + " at " + pt + ", " + dist + " WU away.");
           
            int ii = collidables.collideWith(ray, results);
            System.out.println(ii);
         }
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}

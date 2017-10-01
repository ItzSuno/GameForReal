
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/*
 * Load a .j3o scene and any required models
 * @author Harry-3d
 */

public class LoadScene {
    
    AssetManager assetManager;
    LoadScene(AssetManager assMgr){
        assetManager = assMgr;  
    }  
    
    public Spatial loadIsland1(Node node){
        Spatial island1 = assetManager.loadModel("Scenes/untitled.j3o");
        island1.setLocalTranslation(0, 0, 0);
        node.attachChild(island1);
         CollisionShape islandShape1 = CollisionShapeFactory.createMeshShape(island1);
        RigidBodyControl ground1 = new RigidBodyControl(islandShape1, 0);
        island1.addControl(ground1);
        return island1;
    }
    
    public Spatial loadIsland2(Node node){
        Spatial island2 = assetManager.loadModel("Scenes/scene/Desert.j3o");
        island2.setLocalTranslation(0, -2, -60);
        node.attachChild(island2);
         CollisionShape islandShape2 = CollisionShapeFactory.createMeshShape(island2);
        RigidBodyControl ground2 = new RigidBodyControl(islandShape2, 0);
        island2.addControl(ground2);
        return island2;
    }
    
    public Spatial loadIsland3(Node node){
        Spatial island3 = assetManager.loadModel("Scenes/scene/Desert.j3o");
        island3.setLocalTranslation(0, -2, -120);
        node.attachChild(island3);
         CollisionShape islandShape3 = CollisionShapeFactory.createMeshShape(island3);
        RigidBodyControl ground3 = new RigidBodyControl(islandShape3, 0);
        island3.addControl(ground3);
        return island3;
    }
        
    public Spatial loadIsland4(Node node){
        Spatial island4 = assetManager.loadModel("Scenes/scene/Desert.j3o");
        island4.setLocalTranslation(0, -2, -180);
        node.attachChild(island4);
         CollisionShape islandShape4 = CollisionShapeFactory.createMeshShape(island4);
        RigidBodyControl ground4 = new RigidBodyControl(islandShape4, 0);
        island4.addControl(ground4);
        return island4;
    }
            
    public Spatial loadIsland5(Node node){
        Spatial island5 = assetManager.loadModel("Scenes/problem solved/Desert.j3o");
        island5.setLocalTranslation(0, -2, -240);
        node.attachChild(island5);
         CollisionShape islandShape5 = CollisionShapeFactory.createMeshShape(island5);
        RigidBodyControl ground5 = new RigidBodyControl(islandShape5, 0);
        island5.addControl(ground5);
        return island5;
    }
  
    public RigidBodyControl simpleBox(float xPos, float yPos, float zPos, Node node){
        Box box = new Box(1f, 1f, 1f);
        Geometry boxGeo = new Geometry("box", box);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.randomColor());
        boxGeo.setMaterial(mat);
        node.attachChild(boxGeo);
        boxGeo.setLocalTranslation(xPos, yPos, zPos);
        CollisionShape colShape = CollisionShapeFactory.createMeshShape(boxGeo);
        RigidBodyControl boxRigid = new RigidBodyControl(colShape, 0);
        boxGeo.addControl(boxRigid);
        return boxRigid;
    }
    
    public RigidBodyControl simpleBox(Vector3f vec, Node node){
        Box box = new Box(1f, 1f, 1f);
        Geometry boxGeo = new Geometry("box", box);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.randomColor());
        boxGeo.setMaterial(mat);
        node.attachChild(boxGeo);
        boxGeo.setLocalTranslation(vec.x, vec.y, vec.z);
        CollisionShape colShape = CollisionShapeFactory.createMeshShape(boxGeo);
        RigidBodyControl boxRigid = new RigidBodyControl(colShape, 0);
        boxGeo.addControl(boxRigid);
        return boxRigid;
    }
}


package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
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
        node.attachChild(island1);
        RigidBodyControl ground = new RigidBodyControl(0f);
        island1.addControl(ground);
        return island1;
    }
}

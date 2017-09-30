/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.CC;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;

/*
 Chase camera (aka 3rd person camera) example
 Based on official TestQ3.java

 @author Alex Cham aka Jcrypto
 */
public class SceneBodyManager extends AbstractPhysicsBodyContext
{

    //private final ScenePhysicsBodyContext spbc;


/**

    @param stateManager
    @param am
    @param rootNode
    */
    public SceneBodyManager(AppStateManager stateManager, AssetManager am, Node rootNode)
    {



        //this.spbc = new ScenePhysicsBodyContext(ssbc.getScene());
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app)
    {

        //PhysicsSpace Initialization
        attachBulletAppstate(stateManager);
//
       // stateManager.attach(this.ssbc);
        //stateManager.attach(this.spbc);

    }
}
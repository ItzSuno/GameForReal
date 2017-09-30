/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.CC;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
//import org.jmonkey.utils.Debug;

/*
 Chase camera (aka 3rd person camera) example
 Based on official TestQ3.java

 @author Alex Cham aka Jcrypto
 */
public class ApplicationContext extends AbstractAppState
{

    private final Node rootNode;
//
    private final CameraContext sceneCameraContext;
    private final AvatarBodyManager avatarBodyManager;
    private final SceneBodyManager sceneBodyManager;

/**

    @param stateManager
    @param am
    @param settings
    @param inputManager
    @param rootNode
    @param cam
    @param flyByCam
    */
    public ApplicationContext(AppStateManager stateManager, AssetManager am, AppSettings settings, InputManager inputManager, Node rootNode, Camera cam, FlyByCamera flyByCam)
    {

        this.rootNode = rootNode;
        this.sceneCameraContext = new CameraContext(settings, inputManager, cam, flyByCam);
        this.sceneBodyManager = new SceneBodyManager(stateManager, am, rootNode);
        this.avatarBodyManager = new AvatarBodyManager(am, rootNode, sceneCameraContext);
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app)
    {
        //super.initialize(stateManager, app);
        //TODO: initialize your AppState, e.g. attach spatials to rootNode
        //this is called on the OpenGL thread after the AppState has been attached

//
        stateManager.attach(this.sceneCameraContext);
        stateManager.attach(this.sceneBodyManager);//initialize physic spacein constructor
        stateManager.attach(this.avatarBodyManager);
        //
        Debug.showNodeAxes(app.getAssetManager(), this.rootNode, 1024.0f);
        Debug.attachWireFrameDebugGrid(app.getAssetManager(), rootNode, Vector3f.ZERO, 2048, ColorRGBA.DarkGray);
    }

    @Override
    public void update(float tpf)
    {

    }
}

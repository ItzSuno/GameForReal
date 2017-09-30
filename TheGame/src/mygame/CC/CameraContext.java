/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.CC;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.ChaseCamera;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.renderer.Camera;
import com.jme3.system.AppSettings;

/*
 Chase camera (aka 3rd person camera) example
 Based on official TestQ3.java

 @author Alex Cham aka Jcrypto
 */
public class CameraContext extends AbstractAppState
{

    private final AppSettings settings;
    private final InputManager inputManager;
    /*
     http://hub.jmonkeyengine.org/javadoc/com/jme3/renderer/Camera.html
     public class Camera
     extends java.lang.Object
     implements Savable, java.lang.Cloneable

     Width and height are set to the current Application's settings.getWidth() and settings.getHeight() values.
     Frustum Perspective:
     Frame of view angle of 45Â° along the Y axis
     Aspect ratio of width divided by height
     Near view plane of 1 wu
     Far view plane of 1000 wu
     Start location at (0f, 0f, 10f).
     Start direction is looking at the origin.
     */
    private final Camera cam;
    /*
     http://hub.jmonkeyengine.org/javadoc/com/jme3/input/ChaseCamera.html
     public class ChaseCamera
     extends java.lang.Object
     implements ActionListener, AnalogListener, Control

     A camera that follows a spatial and can turn around it by dragging the mouse
     Constructs the chase camera, and registers inputs if you use this
     constructor you have to attach the cam later to a spatial doing
     spatial.addControl(chaseCamera);
     */
    private final ChaseCamera chaseCam;
    private final FlyByCamera flyByCam;

/**

    @param settings
    @param inputManager
    @param cam
    @param flyByCam
    */
    public CameraContext(AppSettings settings, InputManager inputManager, Camera cam, FlyByCamera flyByCam)
    {

        assert (settings != null);
        this.settings = settings;
        assert (inputManager != null);
        this.inputManager = inputManager;
        assert (cam != null);
        this.cam = cam;
        assert (flyByCam != null);
        this.flyByCam = flyByCam;
        this.chaseCam = new ChaseCamera(this.cam, this.inputManager);
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app)
    {
        super.initialize(stateManager, app);
        //TODO: initialize your AppState, e.g. attach spatials to rootNode
        //this is called on the OpenGL thread after the AppState has been attached

        this.cam.setFrustumPerspective(116.0f, (settings.getWidth() / settings.getHeight()), 1.0f, 2000.0f);
        //this.flyByCam.setMoveSpeed(100);
        this.flyByCam.setEnabled(false);
    }

    /**
     @return the cam
     */
    public Camera getCam()
    {
        return cam;
    }

    /**
     @return the chaseCam
     */
    public ChaseCamera getChaseCam()
    {
        return chaseCam;
    }
}

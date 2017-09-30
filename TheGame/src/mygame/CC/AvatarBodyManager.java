/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.CC;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.objects.PhysicsCharacter;
import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
//import org.jme3.utils.Debug;

/*
 Chase camera (aka 3rd person camera) example
 Based on official TestQ3.java

 @author Alex Cham aka Jcrypto
 */
public class AvatarBodyManager extends AbstractPhysicsBodyContext
{

    private InputManager inputManager;
    private final Node rootNode;
    //
    private final CameraContext cc;
    private final Camera cam;
    private final ChaseCamera chaseCam;
    //
    private final AvatarPhysicsBodyContext apbc;
    private final AvatarSpatialBodyContext asbc;
    //
    private final PhysicsCharacter physicBody;
    private final Node avatar;
    private final BetterCharacterControl bcc;
    //

    private final PlayerInputActionListener playerInputListener;

/**

    @param am
    @param rootNode
    @param cc
    */
    public AvatarBodyManager(AssetManager am, Node rootNode, CameraContext cc)
    {

        //
        this.rootNode = rootNode;
        //
        this.asbc = new AvatarSpatialBodyContext(am, rootNode);
        this.apbc = new AvatarPhysicsBodyContext();
        //
        this.physicBody = apbc.getPhysicBody();

        this.avatar = asbc.getAvatar();
        this.bcc = new BetterCharacterControl(AvatarConstants.COLLISION_SHAPE_RADIUS, AvatarConstants.COLLISION_SHAPE_RADIUS * 2, AvatarConstants.PHYSIC_BODY_MASS);
        //
        this.playerInputListener = new PlayerInputActionListener(this.physicBody, this.asbc.getAvatarMesh());
        //
        this.cc = cc;
        this.cam = cc.getCam();
        this.chaseCam = cc.getChaseCam();
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app)
    {
        //TODO: initialize your AppState, e.g. attach spatials to rootNode
        //this is called on the OpenGL thread after the AppState has been attached

        stateManager.attach(this.asbc);
        stateManager.attach(this.apbc);
        stateManager.attach(this.playerInputListener);


//
        this.avatar.addControl(new AvatarBodyMoveControl(playerInputListener, physicBody, cam));
        this.avatar.addControl(chaseCam);
        this.avatar.addControl(bcc);

        //DEBUG
        //Debug.showNodeAxes(app.getAssetManager(), avatar, 4);
        //getBulletAppState().getPhysicsSpace().enableDebug(app.getAssetManager());
    }



    @Override
    public void update(float tpf)
    {
        //assert (sceneCameraContext != null);

        //correctDirectionVectors(cam.getDirection(), cam.getLeft());

    }
}
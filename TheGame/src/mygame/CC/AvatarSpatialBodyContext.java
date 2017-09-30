/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.CC;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/*
 Chase camera (aka 3rd person camera) example
 Based on official TestQ3.java

 @author Alex Cham aka Jcrypto
 */
public class AvatarSpatialBodyContext extends AbstractSpatialBodyContext
{

    //
    private final Node rootNode;
    //
    private final Node avatar;
    private final Spatial avatarMesh;
    private final Vector3f correction;
/**

    @param am
    @param rootNode
    */
    public AvatarSpatialBodyContext(AssetManager am, Node rootNode)
    {
        this.rootNode = rootNode;
        //
        this.avatar = new Node();
        this.avatarMesh = am.loadModel("Models/Ninja/Ninja.mesh.xml");
        this.correction = new Vector3f(
                0,
                AvatarConstants.COLLISION_SHAPE_CENTERAL_POINT - AvatarConstants.COLLISION_SHAPE_RADIUS,
                0);
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app)
    {


        this.avatarMesh.setLocalScale(new Vector3f(0.05f, 0.05f, 0.05f));//Trouble with scales?
        this.avatarMesh.setLocalTranslation(this.correction);
        this.avatar.attachChild(this.avatarMesh);
        this.rootNode.attachChild(this.avatar);

        //super.initialize(stateManager, app); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     @return the avatar
     */
    public Node getAvatar()
    {
        return avatar;
    }

    /**
     * @return the avatarMesh
     */
    public Spatial getAvatarMesh()
    {
        return avatarMesh;
    }
}
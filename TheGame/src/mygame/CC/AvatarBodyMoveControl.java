/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.CC;

import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.objects.PhysicsCharacter;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/*
 Chase camera (aka 3rd person camera) example
 Based on official TestQ3.java

 @author Alex Cham aka Jcrypto
 */
public class AvatarBodyMoveControl extends AbstractControl
{
    private final Camera cam;
    private final PhysicsCharacter physicBody;
    private final PlayerInputActionListener pial;
/**

    @param pial
    @param physicBody
    @param cam
    */
    public AvatarBodyMoveControl(PlayerInputActionListener pial, PhysicsCharacter physicBody, Camera cam)
    {
        this.pial = pial;
        this.physicBody = physicBody;
        this.cam = cam;
    }
    private final Vector3f walkDirection = new Vector3f();

    @Override
    protected void controlUpdate(float tpf)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        correctDirectionVectors();
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


        /**

     @param camDir
     @param camLeft
     */
    public void correctDirectionVectors()
    {
//        assert (camDir != null);
//        assert (camLeft != null);
//        assert (walkDirection != null);
        //Affect forward, backward move speed 0.6f lower - 1.0f faster
        Vector3f camDirVector = cam.getDirection().clone().multLocal(AvatarConstants.FORWARD_MOVE_SPEED);
        //Affect left, right move speed 0.6f lower - 1.0f faster
        Vector3f camLeftVector = cam.getLeft().clone().multLocal(AvatarConstants.SIDEWARD_MOVE_SPEED);

        walkDirection.set(0, 0, 0);//critical
        if (pial.isLeftward())
        {
            walkDirection.addLocal(camLeftVector);
        }
        if (pial.isRightward())
        {
            walkDirection.addLocal(camLeftVector.negate());
        }
        if (pial.isForward())
        {
            walkDirection.addLocal(camDirVector);
        }
        if (pial.isBackward())
        {
            //@TODO Bug if cam direction (0, -n, 0) - character fly upwards ;)
            walkDirection.addLocal(camDirVector.negate());
        }
        physicBody.setWalkDirection(walkDirection);//Critical



        //Avoid vibration
        spatial.setLocalTranslation(physicBody.getPhysicsLocation());
        //Translate Node accordingly
        spatial.getControl(BetterCharacterControl.class).warp(physicBody.getPhysicsLocation());
        //Rotate Node accordingly to camera
        spatial.getControl(
                BetterCharacterControl.class).setViewDirection(
                cam.getDirection().negate());

    }
}

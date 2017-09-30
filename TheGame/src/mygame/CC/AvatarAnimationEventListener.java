/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.CC;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.objects.PhysicsCharacter;
import com.jme3.scene.Spatial;

/*
 Chase camera (aka 3rd person camera) example
 Based on official TestQ3.java

 @author Alex Cham aka Jcrypto
 */
public class AvatarAnimationEventListener extends AbstractAppState implements AnimEventListener
{

    private final AnimChannel channel;
    private final AnimControl control;
    private final PlayerInputActionListener pial;
    private final AvatarAnimationHelper animHelper;
    private final PhysicsCharacter physicBody;
/**

    @param pial
    @param pc
    @param avatarMesh
    */
    public AvatarAnimationEventListener(PlayerInputActionListener pial, PhysicsCharacter pc, Spatial avatarMesh)
    {
        this.pial = pial;
        this.control = avatarMesh.getControl(AnimControl.class);
        assert (this.control != null);
        this.channel = this.control.createChannel();
        this.physicBody = pc;
        this.animHelper = new AvatarAnimationHelper(this.physicBody, this.channel);
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app)
    {
        this.control.addListener(this);
        this.channel.setAnim("Idle1");
        this.channel.setSpeed(0.5f);
    }

    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void onAnimChange(AnimControl control, AnimChannel channel, String animName)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    /**
     @return the channel
     */
    protected AnimChannel getChannel()
    {
        return channel;
    }

    /**
     @return the control
     */
    protected AnimControl getControl()
    {
        return control;
    }

    /**
     * @return the animHelper
     */
    protected AvatarAnimationHelper getAnimHelper()
    {
        return animHelper;
    }
}

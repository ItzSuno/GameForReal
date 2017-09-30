/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.CC;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.objects.PhysicsCharacter;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.scene.Spatial;

/*
 Chase camera (aka 3rd person camera) example
 Based on official TestQ3.java

 @author Alex Cham aka Jcrypto
 */
public class PlayerInputActionListener extends AbstractAppState implements ActionListener
{

    private final PhysicsCharacter physicBody;
//
    private boolean leftward = false;
    private boolean rightward = false;
    private boolean forward = false;
    private boolean backward = false;
    private boolean jump = false;
    private final AvatarAnimationEventListener aael;
/**

    @param pc
    @param avatar
    */
    public PlayerInputActionListener(PhysicsCharacter pc, Spatial avatar)
    {
        this.physicBody = pc;
        this.aael = new AvatarAnimationEventListener(this, this.physicBody, avatar);
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app)
    {
        stateManager.attach(this.aael);
        //
        app.getInputManager().addMapping("LEFTWARD", new KeyTrigger(KeyInput.KEY_A));
        app.getInputManager().addMapping("RIGHTWARD", new KeyTrigger(KeyInput.KEY_D));
        app.getInputManager().addMapping("FORWARD", new KeyTrigger(KeyInput.KEY_W));
        app.getInputManager().addMapping("BACKWARD", new KeyTrigger(KeyInput.KEY_S));
        app.getInputManager().addMapping("JUMP", new KeyTrigger(KeyInput.KEY_SPACE));
        app.getInputManager().addListener(this, "LEFTWARD");
        app.getInputManager().addListener(this, "RIGHTWARD");
        app.getInputManager().addListener(this, "FORWARD");
        app.getInputManager().addListener(this, "BACKWARD");
        app.getInputManager().addListener(this, "JUMP");
        //
    }

    /**
     @param binding
     @param keyPressed
     @param tpf
     */
    public void onAction(String binding, boolean keyPressed, float tpf)
    {

        if (binding.equals("LEFTWARD"))
        {

            this.leftward = this.aael.getAnimHelper().leftward(keyPressed);

        } else if (binding.equals("RIGHTWARD"))
        {

            this.rightward = this.aael.getAnimHelper().rightward(keyPressed);

        } else if (binding.equals("FORWARD"))
        {

            this.forward = this.aael.getAnimHelper().forward(keyPressed);

        } else if (binding.equals("BACKWARD"))
        {

                this.backward = this.aael.getAnimHelper().backward(keyPressed);

        } else if (binding.equals("JUMP"))
        {

            this.jump = this.aael.getAnimHelper().jump(keyPressed);

        }
    }

    /**
     @return the leftward
     */
    public boolean isLeftward()
    {
        return this.leftward;
    }

    /**
     @return the rightward
     */
    public boolean isRightward()
    {
        return this.rightward;
    }

    /**
     @return the forward
     */
    public boolean isForward()
    {
        return this.forward;
    }

    /**
     @return the backward
     */
    public boolean isBackward()
    {
        return this.backward;
    }

    /**
     @return the jump
     */
    public boolean isJump()
    {
        return this.jump;
    }
}

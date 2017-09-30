/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.CC;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.LoopMode;
import com.jme3.bullet.objects.PhysicsCharacter;

/*
 Chase camera (aka 3rd person camera) example
 Based on official TestQ3.java

 @author Alex Cham aka Jcrypto
 */
public class AvatarAnimationHelper
{

    private final AnimChannel animChannel;
    private final PhysicsCharacter physicBody;
/**

    @param pc
    @param ac
    */
    public AvatarAnimationHelper(PhysicsCharacter pc, AnimChannel ac)
    {
        this.animChannel = ac;
        this.physicBody = pc;
    }

    protected void idle()
    {
        animChannel.setAnim("Idle1");
        animChannel.setSpeed(0.5f);
    }

    protected boolean forward(boolean pressed)
    {
        if (pressed)
        {
            if (this.physicBody.onGround())
            {
                animChannel.setAnim("Walk");
                animChannel.setSpeed(AvatarConstants.FORWARD_MOVE_SPEED * 2f);
                animChannel.setLoopMode(LoopMode.Loop);
            }
            return true;
        } else
        {
            idle();
            return false;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected boolean backward(boolean pressed)
    {
        if (pressed)
        {
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean rightward(boolean pressed)
    {
        if (pressed)
        {
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean leftward(boolean pressed)
    {
        if (pressed)
        {
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean jump(boolean pressed)
    {
            if (pressed)
            {
                if (this.physicBody.onGround())
                {
                    animChannel.setAnim("HighJump");
                    animChannel.setSpeed(AvatarConstants.FORWARD_MOVE_SPEED / 1.8f);
                    animChannel.setLoopMode(LoopMode.Loop);
                    //
                    this.physicBody.jump();
                }
                return true;
            } else
            {
                return false;
            }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.CC;

import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;

/*
 Chase camera (aka 3rd person camera) example
 Based on official TestQ3.java

 @author Alex Cham aka Jcrypto
 */
public abstract class AbstractPhysicsBodyContext extends AbstractAppState
{

    private AppStateManager stateManager = null;
    //
    private static final BulletAppState bulletAppState;

    static
    {
        bulletAppState = new BulletAppState();
    }

    public AbstractPhysicsBodyContext()
    {
    }

    /**
     @return the bulletAppState
     */
    public BulletAppState getBulletAppState()
    {
        return bulletAppState;
    }

    /**
     @param stateManager the stateManager to set
     Attaching BulletAppstate to Initialize PhysicsSpace
     */
    public void attachBulletAppstate(AppStateManager stateManager)
    {
        this.stateManager = stateManager;
        stateManager.attach(bulletAppState);
    }
}

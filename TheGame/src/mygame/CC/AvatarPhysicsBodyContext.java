/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.CC;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.objects.PhysicsCharacter;
import com.jme3.math.Vector3f;

/*
 Chase camera (aka 3rd person camera) example
 Based on official TestQ3.java

 @author Alex Cham aka Jcrypto
 */
public class AvatarPhysicsBodyContext extends AbstractPhysicsBodyContext
{



    private final PhysicsCharacter physicBody;


    public AvatarPhysicsBodyContext()
    {

        this.physicBody = new PhysicsCharacter(new SphereCollisionShape(AvatarConstants.COLLISION_SHAPE_RADIUS), .01f);

    }



    @Override
    public void initialize(AppStateManager stateManager, Application app)
    {
//
        assert (getBulletAppState() != null);
        System.out.println(this.getClass().getName() + ".getBulletAppState().hashCode() = " + getBulletAppState().hashCode());

//
        this.physicBody.setJumpSpeed(32);
        this.physicBody.setFallSpeed(32);
        this.physicBody.setGravity(32);
        this.physicBody.setPhysicsLocation(new Vector3f(0, 10, 0));
        //
        getBulletAppState().getPhysicsSpace().add(this.physicBody);

    }

    @Override
    public void update(float tpf)
    {
    }

    @Override
    public void cleanup()
    {
        super.cleanup();
    }

    /**
     @return the physicBody
     */
    public PhysicsCharacter getPhysicBody()
    {
        return this.physicBody;
    }
}

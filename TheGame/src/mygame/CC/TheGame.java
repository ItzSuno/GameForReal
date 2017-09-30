/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.CC;

import com.jme3.app.SimpleApplication;

/*
 Chase camera (aka 3rd person camera) example
 Based on official TestQ3.java

 @author Alex Cham aka Jcrypto
 */
public class TheGame extends SimpleApplication
{

    private ApplicationContext applicationContext;

    public TheGame()
    {
    }

    //
    public static void main(String[] args)
    {
        TheGame game = new TheGame();
        game.setShowSettings(false);
        game.start();
    }

    @Override
    public void simpleInitApp()
    {
        this.applicationContext = new ApplicationContext(stateManager, assetManager, settings, inputManager, rootNode, cam, flyCam);
        //
        stateManager.attach(applicationContext);
    }
}

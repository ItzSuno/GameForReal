/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.CC;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;

/*
 Chase camera (aka 3rd person camera) example
 Based on official TestQ3.java

 @author Alex Cham aka Jcrypto
 */
public class MaterialUtils
{

    public MaterialUtils()
    {
    }


    //"Common/MatDefs/Misc/Unshaded.j3md"
    public static Material makeMaterial(AssetManager am, String name, ColorRGBA color)
    {
        Material mat = new Material(am, name);
        mat.setColor("Color", color);
        return mat;
    }
}
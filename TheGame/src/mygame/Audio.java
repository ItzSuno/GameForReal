/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioData.DataType;
import com.jme3.audio.AudioNode;
import com.jme3.scene.Node;

/**
 *
 * @author coder
 */
public class Audio {
    
    AssetManager assetManager;
    
    Audio(AssetManager assMgr){
        assetManager = assMgr;   
    }
    
    private AudioNode enviro;
    
    public Node playEnviro(String file){
        enviro = new AudioNode(assetManager, file, DataType.Buffer);
        enviro.setPositional(false);
        enviro.setLooping(true);
        enviro.setVolume(1);
        Node audioNode = new Node();
        audioNode.attachChild(enviro);
        enviro.play();
        return audioNode;
        
        
    }
    
}

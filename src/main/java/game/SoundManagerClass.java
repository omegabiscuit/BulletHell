
package game;


import java.applet.AudioClip;
import java.io.*;
import java.net.*;

import javax.sound.sampled.*;
import javax.swing.*;

import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class SoundManagerClass {
    //boolean play = false;
    ContinuousAudioDataStream loop = null;

    private AudioClip song; // Sound player

    private URL songPath; // Sound path

    Clip clip;
    FloatControl gainControl;
    Slider volumeSlider;


    public void playSoundEffect(final String url, int loop) {
        if(clip != null){
            if(clip.isRunning())
            clip.stop();
        }
        try {
            File file = new File(url);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(loop);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public void playMusic(String filename) {

        new javafx.embed.swing.JFXPanel();
        //String uriString = new File(file).toURI().toString();
        //new MediaPlayer(new Media(uriString)).play();
        File file = new File(filename);
        Media player = new Media(file.toURI().toString());
        MediaPlayer play = new MediaPlayer(player);
        play.play();


    }

    public void stop() {
        if(clip!=null){
            clip.stop();
        }

    }

    public boolean play(){
        if(clip!=null) {
            if (clip.isActive()) {
                return true;
            }

        }
        return false;
    }
}

	
	


package org.codeforall.AfterMidnight.Game;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Music {
    private Clip clip;

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    private boolean isPlaying = false;

    public Music(String path){
        initClip("/" + path);
    }

    public void play(boolean fromStart){
        if(fromStart){
            clip.setFramePosition(0);
        }
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public void initClip(String path){
        InputStream is;
        InputStream bufferedIn;
        AudioInputStream inputStream;
        try{
            is = Music.class.getResourceAsStream(path);
            if(is == null) {
                throw new RuntimeException("Resource not found: " + path);
            }
            bufferedIn = new BufferedInputStream(is);
            inputStream = AudioSystem.getAudioInputStream(bufferedIn);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            is.close();
            bufferedIn.close();
            inputStream.close();
        }catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex){
            ex.printStackTrace();
        }
    }
}

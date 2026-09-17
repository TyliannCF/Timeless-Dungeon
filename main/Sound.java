package main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    
    private Clip musicClip;
    private Clip sfxClip;
    private FloatControl musicVolumeControl;
    
    // Index des fichiers audio
    public static final int MENU_MUSIC = 0;
    public static final int FOREST_MUSIC = 1;
    public static final int ICE_DUNGEON_MUSIC = 2;
    public static final int VICTORY_MUSIC = 3;
    public static final int LOSE_MUSIC = 4;
    public static final int ATTACK_SFX = 5;
    public static final int HURT_SFX = 6;
    
    private String[] soundFiles = new String[10];
    
    public Sound() {
        
        soundFiles[MENU_MUSIC] = "assets/sounds/menu_music.wav";
        soundFiles[FOREST_MUSIC] = "assets/sounds/forest_music.wav";
        soundFiles[ICE_DUNGEON_MUSIC] = "assets/sounds/ice_dungeon_music.wav";
        soundFiles[VICTORY_MUSIC] = "assets/sounds/victory_music.wav";
        soundFiles[LOSE_MUSIC] = "assets/sounds/lose_music.wav";
        soundFiles[ATTACK_SFX] = "assets/sounds/attack.wav";
        soundFiles[HURT_SFX] = "assets/sounds/hurt.wav";
    }
    
    public void setFile(int index) {
        try {
            File soundFile = new File(soundFiles[index]);
            if (!soundFile.exists()) {
                System.out.println("Sound file not found: " + soundFiles[index]);
                return;
            }

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            musicClip = AudioSystem.getClip();
            musicClip.open(ais);


            // Obtenir le contrôle du volume
            if (musicClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                musicVolumeControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            }
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error loading sound: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void play() {
        if (musicClip != null) {
            musicClip.start();
        }
    }
    
    public void loop() {
        if (musicClip != null) {
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    
    public void stop() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
            musicClip.setFramePosition(0);
        }
    }
    
    public void setVolume(float volume) {
        // volume entre 0.0f (silence) et 1.0f (max)
        if (musicVolumeControl != null) {
            float min = musicVolumeControl.getMinimum();
            float max = musicVolumeControl.getMaximum();
            float gain = min + (max - min) * volume;
            musicVolumeControl.setValue(gain);
        }
    }
    
    public void playSFX(int index) {
        try {
            File soundFile = new File(soundFiles[index]);
            if (!soundFile.exists()) {
                System.out.println("SFX file not found: " + soundFiles[index]);
                return;
            }
            
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            sfxClip = AudioSystem.getClip();
            sfxClip.open(ais);
            
            sfxClip.start();
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error playing SFX: " + e.getMessage());
        }
    }
}

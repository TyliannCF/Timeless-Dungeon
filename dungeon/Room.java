package dungeon;

import characters.Character;
import main.GamePanel;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Room{
    private String name;
    private int id;
    private List<Room> room;
    private List<Character> enemies;
    private List<Character> player;
    private String biome;

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public List<Room> getRoom(){return new ArrayList<>(room);}

    public List<Character> getEnemies(){
         return this.enemies;
    }

    public List<Character> getPlayer(){
        return this.player;
    }

    public String getBiome(){
        return biome;
    }

    public Room(String name, int Id, String biome, List<Character> enemies, List<Character> player){
        this.name = name;
        this.id = Id;
        this.enemies = new ArrayList<>(enemies);
        this.player = new ArrayList<>(player);
        this.biome = biome;

        for (Character character : this.enemies) {
            character.setStatus("idle");
        }
        for (Character character : this.player) {
            character.setStatus("idle");
        }
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        
        // Ajustement de la position Y selon la résolution
        int baseY;
        if (GamePanel.maxScreenCol >= 27) {
            // Résolution 1280x720 (27x15) pour que sa sois plus haut
            baseY = (int)(gp.screenHeight * 0.52);
        } else if (GamePanel.gameState == GamePanel.IceDungeon) {
            baseY = (int)(gp.screenHeight * 0.55); 
        } else {
            baseY = (int)(gp.screenHeight * 0.6);
        }
        
        
        int spacingAllies = gp.screenWidth / 8;
        int spacingEnemies = gp.screenWidth / 9;
        
        
        int scale = Math.max(1, gp.screenHeight / 250);
        
        // Position de départ pour les alliés 
        int xAllies = gp.screenWidth / 10;
        for (Character character : player) {
            character.draw(g2, xAllies, baseY, scale, false);
            xAllies += spacingAllies;
        }

        // Position de départ pour les ennemis 
        int xEnemies = (int)(gp.screenWidth * 0.6);
        for (Character character : enemies) {
            character.draw(g2, xEnemies, baseY, scale, true);
            xEnemies += spacingEnemies;
        }
    }

    public void update() {
        for (Character character : enemies){
            character.updateAnimation();
        }
        for (Character character : player) {
            character.updateAnimation();
        }
    }
    List<Character> enemy = new ArrayList<>();

}

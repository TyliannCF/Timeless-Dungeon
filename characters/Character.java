package characters;

import main.UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;


public class Character extends Sprite {
    
    private int pv, maxPv, vitesse, defense, agilite, mana, maxMana, experience;
    private String name, category;
    private int id;
    private List<Attack> attaque;
    
    
    public Character(String name, String category, int id, int pv, int vitesse,
                     int defense, int agilite, int mana,
                     String spritesheetPath, int frameWidth, int frameHeight) {
        super(spritesheetPath, frameWidth, frameHeight);
        initStats(name, category, id, pv, vitesse, defense, agilite, mana);
    }
    
    
    private void initStats(String name, String category, int id, int pv, int vitesse,
                          int defense, int agilite, int mana) {
        this.name = name;
        this.category = category;
        this.id = id;
        this.pv = pv;
        this.maxPv = pv;
        this.vitesse = vitesse;
        this.defense = defense;
        this.agilite = agilite;
        this.mana = mana;
        this.maxMana = mana;
        this.experience = 0;
        this.attaque = new ArrayList<>();
    }
    
    // Getters
    public int getPV() {
      return pv;
    }

    public int getMaxPv() { 
     return maxPv;
    }

    public int getMaxMana() {
     return maxMana; 
    }

    public int getVitesse() { 
     return vitesse; 
    }

    public int getDefense() {
     return defense; 
    }

    public int getAgilite() {
     return agilite; 
    }

    public int getMana() {
     return mana; 
    }

    public String getName() { 
     return name; 
    }

    public String getCategory() {
      return category; 
    }

    public int getId() {
     return id; 
    }

    public int getExperience() {
     return experience; 
    }

    public List<Attack> getAttack() {
      return new ArrayList<>(attaque); 
    }
    
    
    
    
    public void setId(int id) {
        this.id = id;
    }
    
    
    public void addExperience(int exp) {
        this.experience += exp;
        checkLevelUp();
    }
    
    
    private void checkLevelUp() {
        while (experience >= 100) {
            experience -= 100;
            levelUp();
        }
    }
    
   
    private void levelUp() {
        maxPv += 10;
        pv = maxPv;
        vitesse += 2;
        defense += 3;
        agilite += 2;
        maxMana += 5;
        mana = maxMana;
        System.out.println(name + " monte au niveau supérieur !");
    }
    
    
    public void takeDamage(int damage) {
        pv =pv - damage;
        if (pv < 0) { pv = 0; }
        if (pv == 0) {
            setStatus("dead");
            System.out.println(name + " est mort !");
        }
    }
    
    public void heal(int healAmount) { pv = Math.min(maxPv, pv + healAmount); }
    public boolean isAlive() { return pv > 0; }
    
    // Mana
    public boolean useMana(int manaCost) {
        if (mana < manaCost) return false;
        mana -= manaCost;
        return true;
    }
    
    public void restoreMana(int manaAmount) {
         mana = Math.min(maxMana, mana + manaAmount); 
    }
    
    public void addAttack(Attack attack) { attaque.add(attack); }

    public List<Attack> getAttacks() { return attaque; }

    public Attack getAttack(int index) { 
        return (index >= 0 && index < attaque.size()) ? attaque.get(index) : null; 
    }
    
    public String getAttackName(int index) {
        Attack attack = getAttack(index);
        return (attack != null) ? attack.getName() : null;
    }
    
    public List<String> getAttackNames() {
        List<String> names = new ArrayList<>();
        for (Attack attack : attaque) names.add(attack.getName());
        return names;
    }
    
    public boolean performAttack(Attack attack, Character target) {
        if (attack == null || !isAlive() || !target.isAlive()) return false;

        // (ILANN) Lancer l'animation d'attaque correspondante
        playAnimationOnce("attack1");
        System.out.println(name + " utilise " + attack.getName() + " sur " + target.getName() + " !");
        playAnimationOnce("attack");

        new javax.swing.Timer(600, e -> {

            // Appliquer les dégâts
            target.takeDamage(attack.getDamage());
            target.playAnimationOnce("hurt");

            ((javax.swing.Timer)e.getSource()).stop();
        }).start();


        return true;
    }
    
    public void draw(Graphics2D g2d, int x, int y, int scale, boolean flip) {
        
        BufferedImage frame = getFrame(getStatus(), getCurrentFrame());
        
        if (frame == null) {
            return;
        }

       
        int drawWidth = frame.getWidth() * scale;
        int drawHeight = frame.getHeight() * scale;

       
        if (flip) {
            g2d.drawImage(frame, x + drawWidth, y, x, y + drawHeight, 
                         0, 0, frame.getWidth(), frame.getHeight(), null);
        } else {
            g2d.drawImage(frame, x, y, drawWidth, drawHeight, null);
        }

        // Afficher la barre de PV
        Font arial15 = new Font("Arial", Font.PLAIN, 15);
        g2d.setFont(arial15);

        String text = pv + "HP/" + maxPv + "HP";
        int textLength = g2d.getFontMetrics().stringWidth(text);
        int barHeight = 45;

        
        int barX = x + drawWidth / 2 - (textLength + 8) / 2;
        int barY = y - 30; // 30 pixels au-dessus du personnage

        UI.makeButton(g2d, barX, barY, textLength + 8, barHeight, false);

        g2d.setColor(Color.WHITE);
        g2d.drawString(text, barX + 4, barY + 15);
        g2d.drawString(getName(), barX + 4, barY + 35);
    }
    

   
    @Override
    public String toString() {
        return String.format("%s (%s)\nPV: %d/%d | Mana: %d/%d\nVitesse: %d | Défense: %d | Agilité: %d\nXP: %d",
                name, category, pv, maxPv, mana, maxMana, vitesse, defense, agilite, experience);
    }
}
package test;

import characters.Character;
import characters.heroes.*;

import javax.swing.*;
import java.awt.*;


public class CharacterDisplayExample extends JFrame {
    
    public CharacterDisplayExample(Character character) {
        setTitle("Character Display - " + character.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
       
        CharacterPanel panel = new CharacterPanel(character);
        add(panel);
    }
    
    /**
     * Panneau pour dessiner le personnage
     */
    class CharacterPanel extends JPanel {
        private Character character;
        private Timer animationTimer;
        
        public CharacterPanel(Character character) {
            this.character = character;
            setBackground(Color.BLACK);
            setPreferredSize(new Dimension(800, 600));
            
            // Démarrer l'animation idle
            character.setStatus("idle");
            
            // Timer pour animer le personnage
            animationTimer = new Timer(100, e -> {
                character.updateAnimation();
                repaint();
            });
            animationTimer.start();
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            


            int x = getWidth() / 2 - 100;
            int y = getHeight() / 2 - 100;
            character.draw(g2d, x, y, 3, true);
            
            // Afficher les infos du personnage
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            g2d.drawString("Nom: " + character.getName(), 20, 30);
            g2d.drawString("Catégorie: " + character.getCategory(), 20, 50);
            g2d.drawString("Level: " + character.getId(), 20, 70);
            g2d.drawString("HP: " + character.getPV(), 20, 90);
            g2d.drawString("Vitesse: " + character.getVitesse(), 20, 110);
            g2d.drawString("Défense: " + character.getDefense(), 20, 130);
            g2d.drawString("Animation: " + character.getStatus(), 20, 150);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Créer un personnage
            Character wizard = new Wizard();
            
            // L'afficher
            CharacterDisplayExample example = new CharacterDisplayExample(wizard);
            example.setVisible(true);
        });
    }
}

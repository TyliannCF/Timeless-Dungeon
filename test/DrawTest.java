package test;

import characters.Character;
import characters.heroes.Archer;
import characters.heroes.Swordsman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawTest extends JPanel {
    
    private Character archer;
    private Character swordsman;
    private int frameCounter = 0;
    
    public DrawTest() {
        archer = new Archer();
        swordsman = new Swordsman();
        
        // Démarrer l'animation idle
        archer.setStatus("idle");
        archer.playAnimation();
        
        swordsman.setStatus("walk");
        swordsman.playAnimation();
        
        // Timer pour mettre à jour les animations
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                archer.updateAnimation();
                swordsman.updateAnimation();
                frameCounter++;
                repaint();
            }
        });
        timer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(new Color(50, 50, 50));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        // Dessiner l'archer normal
        archer.draw(g2d, 50, 200, 2, false);
        
        // Dessiner l'archer flippé
        archer.draw(g2d, 400, 200, 2, true);
        
        // Dessiner le swordsman
        swordsman.draw(g2d, 700, 200, 3, false);
        
        // Afficher les infos
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        g2d.drawString("Archer (normal) - Status: " + archer.getStatus() + " Frame: " + archer.getCurrentFrame(), 50, 50);
        g2d.drawString("Archer (flipped)", 400, 50);
        g2d.drawString("Swordsman (x3 scale) - Status: " + swordsman.getStatus() + " Frame: " + swordsman.getCurrentFrame(), 700, 50);
        g2d.drawString("Frame counter: " + frameCounter, 50, 80);
        
        // Test des PV
        g2d.drawString("Archer PV: " + archer.getPV() + "/" + archer.getMaxPv(), 50, 110);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Draw Method");
        DrawTest panel = new DrawTest();
        
        frame.add(panel);
        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

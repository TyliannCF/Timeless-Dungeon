package test;

import characters.enemies.Dark_Elf_1;
import characters.heroes.Knight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AttackAnimationTest extends JPanel {
    
    private Dark_Elf_1 darkelf;
    private Knight knight;
    
    public AttackAnimationTest() {
        darkelf = new Dark_Elf_1();
        knight = new Knight();
        
        // Démarrer en idle
        darkelf.setStatus("idle");
        darkelf.playAnimation();
        
        knight.setStatus("idle");
        knight.playAnimation();
        
        // Timer pour mettre à jour les animations
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                darkelf.updateAnimation();
                knight.updateAnimation();
                repaint();
            }
        });
        timer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(new Color(40, 40, 40));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        // Dessiner le Dark Elf
        darkelf.draw(g2d, 100, 250, 3, false);
        
        // Dessiner le Knight
        knight.draw(g2d, 600, 250, 3, false);
        
        // Instructions
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Test des animations d'attaque avec retour automatique à idle", 50, 40);
        
        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        g2d.drawString("Appuyez sur les touches pour tester :", 50, 80);
        g2d.drawString("1, 2, 3, 4 : Attaques du Dark Elf", 50, 110);
        g2d.drawString("Q, W, E, R : Attaques du Knight", 50, 140);
        g2d.drawString("SPACE : Retour manuel à idle", 50, 170);
        
        // Afficher les statuts
        g2d.setColor(Color.YELLOW);
        g2d.drawString("Dark Elf: " + darkelf.getStatus() + " (frame " + darkelf.getCurrentFrame() + ")", 100, 550);
        g2d.drawString("Knight: " + knight.getStatus() + " (frame " + knight.getCurrentFrame() + ")", 600, 550);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Animation d'Attaque");
        AttackAnimationTest panel = new AttackAnimationTest();
        
        // Ajouter les contrôles clavier
        panel.setFocusable(true);
        panel.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                int key = e.getKeyCode();
                
                // Dark Elf attacks (1-4)
                if (key == java.awt.event.KeyEvent.VK_1) {
                    panel.darkelf.playAnimationOnce("attack");
                    System.out.println("Dark Elf -> Attack");
                } else if (key == java.awt.event.KeyEvent.VK_2) {
                    panel.darkelf.playAnimationOnce("walk");
                    System.out.println("Dark Elf -> Walk");
                } else if (key == java.awt.event.KeyEvent.VK_3) {
                    panel.darkelf.playAnimationOnce("hurt");
                    System.out.println("Dark Elf -> Hurt");
                } else if (key == java.awt.event.KeyEvent.VK_4) {
                    panel.darkelf.playAnimationOnce("death");
                    System.out.println("Dark Elf -> Death");
                }
                
                // Knight attacks (Q, W, E, R)
                else if (key == java.awt.event.KeyEvent.VK_Q) {
                    panel.knight.playAnimationOnce("attack1");
                    System.out.println("Knight -> Attack 1");
                } else if (key == java.awt.event.KeyEvent.VK_W) {
                    panel.knight.playAnimationOnce("attack2");
                    System.out.println("Knight -> Attack 2");
                } else if (key == java.awt.event.KeyEvent.VK_E) {
                    panel.knight.playAnimationOnce("attack3");
                    System.out.println("Knight -> Attack 3");
                } else if (key == java.awt.event.KeyEvent.VK_R) {
                    panel.knight.playAnimationOnce("attack4");
                    System.out.println("Knight -> Attack 4");
                }
                
                // Retour manuel à idle (SPACE)
                else if (key == java.awt.event.KeyEvent.VK_SPACE) {
                    panel.darkelf.setStatus("idle");
                    panel.darkelf.playAnimation();
                    panel.knight.setStatus("idle");
                    panel.knight.playAnimation();
                    System.out.println("Retour à idle");
                }
            }
        });
        
        frame.add(panel);
        frame.setSize(1000, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

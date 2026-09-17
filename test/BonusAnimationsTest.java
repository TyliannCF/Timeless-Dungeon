package test;

import characters.heroes.Enchantress;

/**
 * Test des animations bonus (special_attack, roll, take, etc.)
 */
public class BonusAnimationsTest {
    
    public static void main(String[] args) {
        // Créer une Enchantress
        Enchantress lyria = new Enchantress();
        
        // Tester
        System.out.println("=== Test Animations Bonus ===");
        System.out.println("Personnage: " + lyria.getName());
        
        // Animation normale
        lyria.setStatus("idle");
        System.out.println("\nIdle - Status: " + lyria.getStatus() + ", Frame: " + lyria.getCurrentFrame());
        
        // Animations d'attaque
        lyria.setStatus("attack1");
        System.out.println("Attack1 - Status: " + lyria.getStatus() + ", Frame count: " + lyria.getAnimationFrameCount("attack1"));
        
        lyria.setStatus("attack2");
        System.out.println("Attack2 - Status: " + lyria.getStatus() + ", Frame count: " + lyria.getAnimationFrameCount("attack2"));
        
        // Animations bonus (depuis la spritesheet bonus)
        lyria.setStatus("special_attack1");
        System.out.println("Special Attack 1 - Status: " + lyria.getStatus() + ", Frame count: " + lyria.getAnimationFrameCount("special_attack1"));
        
        lyria.setStatus("roll");
        System.out.println("Roll - Status: " + lyria.getStatus() + ", Frame count: " + lyria.getAnimationFrameCount("roll"));
        
        lyria.setStatus("take");
        System.out.println("Take - Status: " + lyria.getStatus() + ", Frame count: " + lyria.getAnimationFrameCount("take"));
        
        // Afficher les stats
        System.out.println("\n=== Stats ===");
        System.out.println(lyria);
    }
}

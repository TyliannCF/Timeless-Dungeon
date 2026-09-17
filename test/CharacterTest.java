package test;

import characters.Attack;
import characters.Character;
import characters.heroes.Archer;
import characters.heroes.Swordsman;
import characters.heroes.Wizard;

public class CharacterTest {
    
    public static void main(String[] args) {
        System.out.println("=== Test du système de personnages - Timeless Dungeon ===\n");
        
        // Création de personnages avec les nouvelles classes
        Character arthus = new Swordsman();
        Character lyria = new Wizard();
        Character thalys = new Archer();
        
        // Ajout manuel des attaques spécifiques à chaque personnage
        System.out.println("- Ajout des attaques -");
        
        // Arthus - Guerrier avec ses propres attaques
        arthus.addAttack(new Attack("Coup d'épée", 25));
        arthus.addAttack(new Attack("Charge brutale", 40, 10, -1));
        arthus.addAttack(new Attack("Frappe bouclier", 15, 5, -1));
        System.out.println("Attaques ajoutées pour " + arthus.getName());
        
        // Lyria - Mage avec ses propres attaques
        lyria.addAttack(new Attack("Boule de feu", 30, 15, -1));
        lyria.addAttack(new Attack("Éclair de glace", 35, 20, -1));
        lyria.addAttack(new Attack("Tempête arcanique", 50, 30, -1));
        System.out.println("Attaques ajoutées pour " + lyria.getName());
        
        // Thalys - Archer avec ses propres attaques
        thalys.addAttack(new Attack("Tir précis", 20));
        thalys.addAttack(new Attack("Flèche perforante", 35, 8, -1));
        thalys.addAttack(new Attack("Volée de flèches", 45, 15, -1));
        System.out.println("Attaques ajoutées pour " + thalys.getName());
        
        // Affichage des stats initiales
        System.out.println("\n- Personnages créés -");
        System.out.println(arthus);
        System.out.println("\n" + lyria);
        System.out.println("\n" + thalys);
        
       
        System.out.println("\n- Attaques disponibles -");
        System.out.println("\n" + arthus.getName() + ":");
        for (Attack attack : arthus.getAttack()) {
            System.out.println("  - " + attack);
        }
        
        System.out.println("\n" + lyria.getName() + ":");
        for (Attack attack : lyria.getAttack()) {
            System.out.println("  - " + attack);
        }
        
        System.out.println("\n" + thalys.getName() + ":");
        for (Attack attack : thalys.getAttack()) {
            System.out.println("  - " + attack);
        }
        
        // Simulation de combat
        System.out.println("\n- Simulation de combat -");
        
        // Arthus attaque
        Attack arthusAttack = arthus.getAttack().get(0);
        System.out.println(arthus.getName() + " utilise " + arthusAttack.getName() + " !");
        
        // Lyria prend des dégâts
        System.out.println(lyria.getName() + " subit " + arthusAttack.getDamage() + " dégâts !");
        lyria.takeDamage(arthusAttack.getDamage());
        System.out.println(lyria.getName() + " PV: " + lyria.getPV());
        
        // Lyria se soigne
        System.out.println("\n" + lyria.getName() + " utilise une potion de soin !");
        lyria.heal(30);
        System.out.println(lyria.getName() + " PV: " + lyria.getPV());
        
        // Test d'utilisation de mana
        System.out.println("\n--- Test du système de mana ---");
        Attack mageSpell = lyria.getAttack().get(2); // Tempête arcanique
        System.out.println(lyria.getName() + " tente d'utiliser " + mageSpell.getName());
        if (lyria.useMana(mageSpell.getManaCost())) {
            System.out.println("Sort lancé ! Mana restant: " + lyria.getMana());
        } else {
            System.out.println("Pas assez de mana !");
        }
        
        // Test d'expérience et level up
        System.out.println("\n- Test de montée de niveau -");
        System.out.println(arthus.getName() + " gagne de l'expérience !");
        arthus.addExperience(50);
        System.out.println("XP: " + arthus.getExperience() + "/100");
        
        arthus.addExperience(60);
        System.out.println("\nAprès level up:");
        System.out.println(arthus);
        
        // Test de personnage K.O.
        System.out.println("\n-- Test de K.O. -");
        System.out.println(thalys.getName() + " est vivant ? " + thalys.isAlive());
        System.out.println("Inflige 200 dégâts à " + thalys.getName());
        thalys.takeDamage(200);
        System.out.println(thalys.getName() + " est vivant ? " + thalys.isAlive());
        
        System.out.println("\n=== Tests terminés avec succès ! ===");
    }
}

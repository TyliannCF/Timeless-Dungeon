package characters.bosses;

import characters.Attack;
import characters.Character;

public class DarkElfQueen extends Character {
    
    public DarkElfQueen() {
        super(
            "Dark Elf Queen", "Boss", 35,
            250, 35, 22, 30, 0,
            "assets/Dark_Elf_Queen/Dark_Elf_Queen_Spritelist.png", 128, 128
        );

        Attack attack1 = new Attack("Shadow Blade", 40);
        Attack attack2 = new Attack("Dark Vengeance", 55);

        addAttack(attack1);
        addAttack(attack2);

        configureAnimations();
    }
    
    private void configureAnimations() {
        // Positions
        setAnimationPosition("idle", 0, 0, 7);
        setAnimationPosition("walk", 1, 0, 12);
        setAnimationPosition("summon", 2, 0, 14);
        setAnimationPosition("step_back", 3, 0, 6);
        setAnimationPosition("dialogue", 4, 0, 9);
        setAnimationPosition("attack1", 5, 0, 10);
        setAnimationPosition("attack2", 6, 0, 8);
        setAnimationPosition("attack3", 7, 0, 8);
        setAnimationPosition("hurt", 8, 0, 3);
        setAnimationPosition("death", 9, 0, 5);
        
        setAnimationDelay("idle", 140);
        setAnimationDelay("walk", 100);
        setAnimationDelay("run", 80);
        setAnimationDelay("jump", 110);
        setAnimationDelay("attack1", 95);
        setAnimationDelay("attack2", 100);
        setAnimationDelay("attack3", 105);
        setAnimationDelay("hurt", 130);
        setAnimationDelay("death", 220);
        
        // TODO: Convertir toutes ces animations en spritesheet bonus
        // addCustomAnimation("blade_attack1", "assets/Dark_Elf_Queen/Blade_Attack1.png", 8, 90);
        // addCustomAnimation("blade_attack2", "assets/Dark_Elf_Queen/Blade_Attack2.png", 8, 95);
        // addCustomAnimation("blade_attack3", "assets/Dark_Elf_Queen/Blade_Attack3.png", 8, 100);
        // addCustomAnimation("dialogue", "assets/Dark_Elf_Queen/Dialogue.png", 8, 150);
        // addCustomAnimation("step_back", "assets/Dark_Elf_Queen/Step_Back.png", 3, 120);
        // addCustomAnimation("summoning", "assets/Dark_Elf_Queen/Summoning.png", 15, 110);
    }
}

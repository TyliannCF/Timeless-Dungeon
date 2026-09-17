package dungeon;

import characters.Character;
import characters.bosses.Hellhound1;
import characters.enemies.*;
import characters.minibosses.Dragon1;
import characters.heroes.Archer;
import characters.heroes.Knight;
import characters.heroes.Wizard;

import java.util.ArrayList;

public class Ice_dungeon extends Dungeon {
    Mimic_1 mimic1_ice = new Mimic_1();
    Mimic_2 mimic2_ice = new Mimic_2();
    Mimic_3 mimic3_ice = new Mimic_3();
    Ally_Queen allyQueen1_ice = new Ally_Queen();
    Ally_Queen allyQueen2_ice = new Ally_Queen();
    Ally_Queen allyQueen3_ice = new Ally_Queen();
    Demon_1 demon1_ice = new Demon_1();
    Demon_1 demon2_ice = new Demon_1();
    Demon_1 demon3_ice = new Demon_1();

    Archer archer_ice = new Archer();
    Wizard wizard_ice = new Wizard();
    Knight knight_ice = new Knight();

    public Ice_dungeon() {
        super(
                "Ice dungeon",
                1,
                new ArrayList<>(),
                null,
                null,
                null,
                false);

        generateDungeon();


        if (!getRoom().isEmpty()) {
            updateRoomNavigation(0);
        }
    }

    private void generateDungeon() {

        ArrayList<characters.Character> enemies1 = new ArrayList<>();
        enemies1.add(new Blue_Slime());
        enemies1.add(new Green_Slime());
        enemies1.add(new Red_Slime());

        ArrayList<characters.Character> enemies2 = new ArrayList<>();
        enemies2.add(new Skeleton());
        enemies2.add(new Skeleton());
        enemies2.add(new Blue_Slime());

        ArrayList<characters.Character> enemies3 = new ArrayList<>();
        enemies3.add(new Demon_1());
        enemies3.add(new Demon_1());
        enemies3.add(new Demon_1());

        ArrayList<characters.Character> enemies4 = new ArrayList<>();
        enemies4.add(new Hellhound1());

        ArrayList<Character> player1 = new ArrayList<>();
        player1.add(new Archer());
        player1.add(new Wizard());
        player1.add(new Knight());

        ArrayList<Character> player2 = new ArrayList<>();
        player2.add(new Archer());
        player2.add(new Wizard());
        player2.add(new Knight());

        ArrayList<Character> player3 = new ArrayList<>();
        player3.add(new Archer());
        player3.add(new Wizard());
        player3.add(new Knight());

        ArrayList<Character> player4 = new ArrayList<>();
        player4.add(new Archer());
        player4.add(new Wizard());
        player4.add(new Knight());

        Room first_room = new Room("First room", 0, "Forest", enemies1, player1);
        Room second_room = new Room("Second room", 1, "Forest", enemies2, player2);
        Room third_room = new Room("Third room", 2, "Forest", enemies3, player3);
        Room boss_room = new Room("Boss Room", 3, "Forest", enemies4, player4);

        addRoom(first_room);
        addRoom(second_room);
        addRoom(third_room);
        addRoom(boss_room);
    }
}
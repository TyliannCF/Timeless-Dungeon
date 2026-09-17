package dungeon;

import java.util.ArrayList;

import characters.Character;
import characters.bosses.DarkElfQueen;
import characters.enemies.*;
import characters.heroes.Archer;
import characters.heroes.Knight;
import characters.heroes.Wizard;


public class Forest_of_dispair extends Dungeon {
    Mimic_1 mimic1 = new Mimic_1();
    Mimic_2 mimic2 = new Mimic_2();
    Mimic_3 mimic3 = new Mimic_3();
    Ally_Queen allyQueen1 = new Ally_Queen();
    Ally_Queen allyQueen2 = new Ally_Queen();
    Ally_Queen allyQueen3 = new Ally_Queen();
    Demon_1 demon1 = new Demon_1();
    Demon_1 demon2 = new Demon_1();
    Demon_1 demon3 = new Demon_1();
    DarkElfQueen darkElfQueen = new DarkElfQueen();

    Archer archer = new Archer();
    Wizard wizard = new Wizard();
    Knight knight = new Knight();

    public Forest_of_dispair() {
        super(
                "Forest of Dispair",
                0,
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

        ArrayList<Character> enemies1 = new ArrayList<>();
        enemies1.add(new Mimic_1());
        enemies1.add(new Mimic_2());
        enemies1.add(new Mimic_3());

        ArrayList<Character> enemies2 = new ArrayList<>();
        enemies2.add(new Demon_2());
        enemies2.add(new Demon_2());
        enemies2.add(new Demon_1());

        ArrayList<Character> enemies3 = new ArrayList<>();
        enemies3.add(new Dark_Elf_1());
        enemies3.add(new Dark_Elf_2());
        enemies3.add(new Dark_Elf_3());

        ArrayList<Character> enemies4 = new ArrayList<>();
        enemies4.add(new DarkElfQueen());
        enemies4.add(new DarkElfQueen());
        enemies4.add(new DarkElfQueen());

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
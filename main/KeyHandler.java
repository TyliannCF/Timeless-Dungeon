package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {


    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean alliesReleased = false;
    public boolean attackReleased = false;
    public boolean enterReleased = false;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();

        // Permettre de sauter l'écran d'introduction avec ENTER
        if(GamePanel.gameState == GamePanel.introState) {
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ESCAPE) {
                GamePanel.gameState = GamePanel.menuState;
                gp.tileM.selectRandomMenuGif();
                gp.sound.setFile(Sound.MENU_MUSIC);
                gp.sound.loop();
            }
            return;
        }

        if(GamePanel.gameState == GamePanel.menuState) {
            handleMenuNavigation(code);
        }

        // Gestion de la sélection de donjon
        if(GamePanel.gameState == GamePanel.dungeonSelectionState) {
            handleDungeonSelectionNavigation(code);
            if (code == KeyEvent.VK_ENTER && enterReleased) {
                handleDungeonSelection();
            }
            if (code == KeyEvent.VK_ESCAPE) {
                returnToMenu();
            }
        }
        if (GamePanel.gameState == GamePanel.optionState) {
            handleOptionsNavigation(code);
        }
        
        if (GamePanel.gameState == GamePanel.teamManagerState) {
            handleTeamManagerNavigation(code);
        }

        // Gestion des écrans de victoire et défaite
        if ((GamePanel.gameState == GamePanel.winState || GamePanel.gameState == GamePanel.loseState) 
            && code == KeyEvent.VK_ENTER && enterReleased) {
            resetDungeonsAndReturnToMenu();
        }

        if ((GamePanel.gameState == GamePanel.forestOfDispair || GamePanel.gameState == GamePanel.IceDungeon) 
            && GameAction.turn == GameAction.playerTurn) {
            handleCombatNavigation(code);
        }

        if(code == KeyEvent.VK_Z){
            upPressed = true;
        }
        if(code == KeyEvent.VK_Q){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ENTER) {
            enterReleased = true;
            GameAction.turn = GameAction.playerTurn;
            
            if (GameAction.asSelectedAllies) {
                System.out.println(gp.gameAction.getSelected().getName());
                alliesReleased = true;
            }
            if (GameAction.asSelectedAttack) {
                System.out.println(gp.gameAction.getSelectedAttack());
                attackReleased = true;
            }
            if (GameAction.asSelectedAttack && GameAction.asSelectedEnemies) {
                System.out.println(gp.gameAction.getSelectedEnemies().getName());
                gp.gameAction.iaPlay();
            }
        }

        if (code == KeyEvent.VK_Z) upPressed = false;
        if (code == KeyEvent.VK_Q) leftPressed = false;
        if (code == KeyEvent.VK_S) downPressed = false;
        if (code == KeyEvent.VK_D) rightPressed = false;
    }
    
    // Méthodes helper pour réduire la duplication
    private void handleMenuNavigation(int code) {
        if (code == KeyEvent.VK_Z || code == KeyEvent.VK_UP) {
            gp.ui.commandNum = (gp.ui.commandNum - 1 + 4) % 4;
        } else if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum = (gp.ui.commandNum + 1) % 4;
        } else if (code == KeyEvent.VK_ENTER) {
            enterReleased = false;
            switch (gp.ui.commandNum) {
                case 0:
                    GamePanel.gameState = GamePanel.dungeonSelectionState;
                    gp.ui.commandNum = 0;
                    break;
                case 1:
                    GamePanel.gameState = GamePanel.teamManagerState;
                    gp.ui.teamManagerSlot = 0;
                    gp.ui.heroSelectionIndex = 0;
                    break;
                case 2:
                    GamePanel.gameState = GamePanel.optionState;
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }
    
    private void navigateIndex(boolean isLeft, int[] index, int maxSize) {
        if (isLeft) {
            index[0] = (index[0] - 1 + maxSize) % maxSize;
        } else {
            index[0] = (index[0] + 1) % maxSize;
        }
    }
    
    private void handleCombatNavigation(int code) {
        if (!GameAction.asSelectedAllies) {
            handleAllySelection(code);
        } else if (!GameAction.asSelectedAttack) {
            handleAttackSelection(code);
        } else if (attackReleased) {
            handleEnemySelection(code);
        }
    }
    
    private void handleAllySelection(int code) {
        boolean isLeft = (code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT);
        boolean isRight = (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT);
        
        int playerSize = GamePanel.currentDungeon.getCurrentRoom().getPlayer().size();
        
        // Vérifier que la liste des joueurs n'est pas vide
        if (playerSize == 0) {
            System.out.println("ERROR: No players in team! Please configure your team in Team Manager.");
            return;
        }
        
        if (isLeft || isRight) {
            int[] index = {GameAction.indexAllies};
            navigateIndex(isLeft, index, playerSize);
            GameAction.indexAllies = index[0];
        } else if (code == KeyEvent.VK_ENTER && enterReleased) {
            // S'assurer que l'index est valide
            if (GameAction.indexAllies < playerSize) {
                gp.gameAction.setSelected(gp.gameAction.getSelected());
                GameAction.asSelectedAllies = true;
                enterReleased = false;
                System.out.println("Selected ally: " + gp.gameAction.getSelected().getName());
                System.out.println("Available attacks: " + gp.gameAction.getSelected().getAttack().size());
            }
        } else if (code == KeyEvent.VK_ESCAPE) {
            resetDungeonsAndReturnToMenu();
            GameAction.turn = GameAction.iATurn;
        }
    }
    
    private void handleAttackSelection(int code) {
        boolean isLeft = (code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT);
        boolean isRight = (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT);
        
        if (isLeft || isRight) {
            int[] index = {GameAction.indexAttack};
            navigateIndex(isLeft, index, gp.gameAction.getSelected().getAttack().size());
            GameAction.indexAttack = index[0];
            System.out.println("Selected attack: " + gp.gameAction.getSelected().getAttack().get(GameAction.indexAttack).getName());
        } else if (code == KeyEvent.VK_ENTER && enterReleased) {
            gp.gameAction.setSelectedAttack(gp.gameAction.getSelectedAttack());
            GameAction.asSelectedAttack = true;
            attackReleased = false; // Préparer pour la sélection d'ennemi
            enterReleased = false;
            System.out.println("Confirmed attack: " + gp.gameAction.getSelectedAttack().getName());
        } else if (code == KeyEvent.VK_ESCAPE) {
            GameAction.asSelectedAllies = false;
            alliesReleased = false;
            enterReleased = false;
        }
    }
    
    private void handleEnemySelection(int code) {
        boolean isLeft = (code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT);
        boolean isRight = (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT);
        
        if (isLeft || isRight) {
            int[] index = {GameAction.indexenemies};
            navigateIndex(isLeft, index, GamePanel.currentDungeon.getCurrentRoom().getEnemies().size());
            GameAction.indexenemies = index[0];
        } else if (code == KeyEvent.VK_ENTER) {
            gp.gameAction.setSelectedEnemies(gp.gameAction.getSelectedEnemies());
            GameAction.asSelectedEnemies = true;
            gp.gameAction.handleDeath();
        } else if (code == KeyEvent.VK_ESCAPE) {
            GameAction.asSelectedAttack = false;
            attackReleased = false;
        }
    }
    
    private void handleDungeonSelectionNavigation(int code) {
        if (code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT) {
            if (gp.ui.commandNum < 4) {
                gp.ui.commandNum = (gp.ui.commandNum - 1 + 4) % 4;
            }
        } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (gp.ui.commandNum < 4) {
                gp.ui.commandNum = (gp.ui.commandNum + 1) % 4;
            }
        } else if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum = 4;
        } else if (code == KeyEvent.VK_Z || code == KeyEvent.VK_UP) {
            if (gp.ui.commandNum == 4) gp.ui.commandNum = 0;
        }
    }
    
    private void handleDungeonSelection() {
        enterReleased = false;
        switch (gp.ui.commandNum) {
            case 0:
                loadDungeon("Loading Forest of Dispair", GamePanel.forest_of_dispair, 
                           GamePanel.forestOfDispair, Sound.FOREST_MUSIC);
                break;
            case 1:
                loadDungeon("Loading Ice Dungeon", GamePanel.iceDungeon, 
                           GamePanel.IceDungeon, Sound.ICE_DUNGEON_MUSIC);
                break;
            case 2:
            case 3:
                // TODO: Créer les Desert et Mountain dungeons
                break;
            case 4:
                returnToMenu();
                break;
        }
    }
    
    private void loadDungeon(String loadingText, dungeon.Dungeon dungeon, int gameState, int musicFile) {
        gp.ui.startLoading(loadingText);
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                GamePanel.currentDungeon = dungeon;
                
                // Mettre à jour l'équipe du joueur dans le donjon
                if (gp.playerTeam != null && gp.playerTeam.getTeamSize() > 0) {
                    dungeon.updatePlayerTeam(gp.playerTeam.getCharacters());
                }
                
                GamePanel.gameState = gameState;
                gp.sound.stop();
                gp.sound.setFile(musicFile);
                gp.sound.loop();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
    
    private void handleOptionsNavigation(int code) {
        if (code == KeyEvent.VK_Z || code == KeyEvent.VK_UP) {
            gp.ui.optionNum = (gp.ui.optionNum - 1 + 6) % 6;
        } else if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.optionNum = (gp.ui.optionNum + 1) % 6;
        } else if (code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT) {
            adjustVolumeBrightness(-0.1f);
        } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            adjustVolumeBrightness(0.1f);
        } else if (code == KeyEvent.VK_ENTER && enterReleased) {
            handleOptionSelection();
        } else if (code == KeyEvent.VK_ESCAPE) {
            returnToMenu();
        }
    }
    
    private void adjustVolumeBrightness(float delta) {
        if (gp.ui.optionNum == 0) {
            gp.ui.volume = Math.max(0.0f, Math.min(1.0f, gp.ui.volume + delta));
            gp.sound.setVolume(gp.ui.volume);
        } else if (gp.ui.optionNum == 1) {
            gp.ui.brightness = Math.max(0.3f, Math.min(1.0f, gp.ui.brightness + delta));
        }
    }
    
    private void handleOptionSelection() {
        switch (gp.ui.optionNum) {
            case 2:
                GamePanel.maxScreenCol = 20;
                GamePanel.maxScreenRow = 11;
                gp.resize();
                break;
            case 3:
                GamePanel.maxScreenCol = 16;
                GamePanel.maxScreenRow = 9;
                gp.resize();
                break;
            case 4:
                GamePanel.maxScreenCol = 27;
                GamePanel.maxScreenRow = 15;
                gp.resize();
                break;
            case 5:
                returnToMenu();
                break;
        }
    }
    
    private void returnToMenu() {
        GamePanel.gameState = GamePanel.menuState;
        gp.ui.commandNum = 0;
        enterReleased = false;
    }
    
    private void resetDungeonsAndReturnToMenu() {
        GamePanel.forest_of_dispair = new dungeon.Forest_of_dispair();
        GamePanel.iceDungeon = new dungeon.Ice_dungeon();
        GamePanel.currentDungeon = null;
        GamePanel.gameState = GamePanel.menuState;
        gp.sound.stop();
        gp.sound.setFile(Sound.MENU_MUSIC);
        gp.sound.loop();
        gp.tileM.selectRandomMenuGif();
        enterReleased = false;
    }
    
    private void handleTeamManagerNavigation(int code) {
        if (code == KeyEvent.VK_UP) {
            gp.ui.teamManagerSlot = (gp.ui.teamManagerSlot - 1 + 3) % 3;
        }
        if (code == KeyEvent.VK_DOWN) {
            gp.ui.teamManagerSlot = (gp.ui.teamManagerSlot + 1) % 3;
        }
        if (code == KeyEvent.VK_LEFT) {
            gp.ui.heroSelectionIndex = (gp.ui.heroSelectionIndex - 1 + 6) % 6;
        }
        if (code == KeyEvent.VK_RIGHT) {
            gp.ui.heroSelectionIndex = (gp.ui.heroSelectionIndex + 1) % 6;
        }
        if (code == KeyEvent.VK_ENTER && enterReleased) {
            confirmHeroSelection();
            enterReleased = false;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            returnToMenu();
        }
    }
    
    private void confirmHeroSelection() {
        String[] heroes = {"Knight", "Archer", "Wizard", "Musketeer", "Swordsman", "Enchantress"};
        String selectedHero = heroes[gp.ui.heroSelectionIndex];
        int slot = gp.ui.teamManagerSlot;
        
        // Créer une instance du héros sélectionné
        characters.Character newHero = null;
        switch (selectedHero) {
            case "Knight":
                newHero = new characters.heroes.Knight();
                break;
            case "Archer":
                newHero = new characters.heroes.Archer();
                break;
            case "Wizard":
                newHero = new characters.heroes.Wizard();
                break;
            case "Musketeer":
                newHero = new characters.heroes.Musketeer();
                break;
            case "Swordsman":
                newHero = new characters.heroes.Swordsman();
                break;
            case "Enchantress":
                newHero = new characters.heroes.Enchantress();
                break;
        }
        
        if (newHero != null) {
            // Si le slot a déjà un héros, le remplacer
            if (slot < gp.playerTeam.getTeamSize()) {
                gp.playerTeam.removeCharacter(slot);
            }
            
            // Ajouter le nouveau héros au bon emplacement
            while (gp.playerTeam.getTeamSize() < slot) {
                // Ajouter des slots vides si nécessaire
                gp.playerTeam.addCharacter(null);
            }
            
            if (slot < gp.playerTeam.getTeamSize()) {
                gp.playerTeam.getCharacters().set(slot, newHero);
            } else {
                gp.playerTeam.addCharacter(newHero);
            }
            
            System.out.println("Added " + selectedHero + " to slot " + (slot + 1));
        }
    }

}

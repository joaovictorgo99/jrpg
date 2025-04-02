package jrpg.view;

import jrpg.model.Enemy;
import jrpg.model.Game;
import jrpg.model.Player;
import jrpg.repository.*;
import jrpg.utils.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JPanel contentPane;
    private JTextArea enemyTextArea;
    private JTextField battleTextField;
    private JTextArea battleTextArea;
    private JTextArea playerTextArea;
    private JButton attackButton;
    private JButton defendButton;
    private JButton healButton;
    private JLabel playerLabel;
    private JLabel enemyLabel;
    private JSeparator enemySeparator;
    private JSeparator playerSeparator;

    private final Timer stopwatchAction;

    public View() {
        setTitle("JRPG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        pack();

        // Set the frame location to the center of the screen
        setLocationRelativeTo(null);

        GameUtils.newGame(new Game());
        setBattleTextBattleTextField();
        setTextEnemyTextArea();
        setTextPlayerTextArea();

        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int damage = PlayerUtils.attack(PlayerRepository.getPlayers().getLast(), EnemyRepository.getEnemies().getLast());

                if (BattleRepository.getBattles().getLast().isPlayerWinner()) {
                    endBattle(damage);
                }
                else {
                    setButtonEnabled(false);
                    stopwatchAction.start();

                    setAttackTextBattleTextArea(damage);
                    setTextPlayerTextArea();
                    setTextEnemyTextArea();
                    TurnRepository.getTurns().getLast().setPlayerTurn(false);
                    TurnRepository.getTurns().getLast().setCriticalDamage(false);
                    setBattleTextBattleTextField();
                }
            }
        });

        defendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PlayerUtils.defend(PlayerRepository.getPlayers().getLast(), TurnRepository.getTurns().getLast());
                setTextPlayerTextArea();

                if (TurnRepository.getTurns().getLast().isDefenseOn()) {
                    setButtonEnabled(false);
                    stopwatchAction.start();
                }

                setDefendTextBattleTextArea();
                TurnRepository.getTurns().getLast().setPlayerTurn(false);
            }
        });

        healButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int heal = PlayerUtils.heal(PlayerRepository.getPlayers().getLast());
                setHealTextBattleTextArea(heal);
                setTextPlayerTextArea();
            }
        });

        ActionListener playerAction = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                setButtonEnabled(true);
                enemyAction();
            }
        };

        stopwatchAction = new Timer(RandomUtils.generateNumber(3, 5) * 1000, playerAction);
        stopwatchAction.setRepeats(false);

        // Set the frame visible
        setVisible(true);
    }

    public void setButtonEnabled(boolean enabled) {
        attackButton.setEnabled(enabled);
        defendButton.setEnabled(enabled);
        healButton.setEnabled(enabled);
    }

    public void setTextEnemyTextArea() {
        Enemy enemy = EnemyRepository.getEnemies().getLast();

        enemyTextArea.setText("HP: " + enemy.getLife() + "\n" +
                                "TYPE: " + enemy.getType() + "\n" +
                                "LVL: " + enemy.getLevel());
    }

    public void setTextPlayerTextArea() {
        Player player = PlayerRepository.getPlayers().getLast();

        playerTextArea.setText("HP: " + player.getLife() + "/" + player.getMaxLife() + "\n" +
                                "POWER: " + player.getPower() + "/" + player.getMaxPower() + "\n" +
                                "LVL: " + player.getLevel());
    }

    public void setBattleTextBattleTextField() {
        if (TurnRepository.getTurns().getLast().isPlayerTurn()) {
            battleTextField.setText("BATTLE " + BattleRepository.getBattles().getLast().getId() + " / " +
                    "TURN " + TurnRepository.getTurns().getLast().getId() + " - " +
                    "PLAYER TURN");
        }
        else {
            battleTextField.setText("BATTLE " + BattleRepository.getBattles().getLast().getId() + " / " +
                    "TURN " + TurnRepository.getTurns().getLast().getId() + " - " +
                    "ENEMY TURN");
        }
    }

    public String setAttackTextBattleTextArea(int damagePoints) {
        String attackText = "";
        String creature;

        if (TurnRepository.getTurns().getLast().isPlayerTurn()) {
            creature = "Player";
        }
        else {
            creature = "Enemy";
        }

        if (damagePoints == 0) {
            battleTextArea.setText(creature + " misses the attack.");
        }
        else {
            attackText = creature + " deals " + damagePoints + " points of damage.";
            battleTextArea.setText(attackText);

            if (TurnRepository.getTurns().getLast().isCriticalDamage()) {
                attackText = "CRITICAL ATTACK!\n" + attackText;
                battleTextArea.setText(attackText);
            }
        }

        return attackText;
    }

    public void setDefendTextBattleTextArea() {
        if (TurnRepository.getTurns().getLast().isDefenseOn()) {
            battleTextArea.setText("Player uses defense.");
        }
        else {
            battleTextArea.setText("Player doesn't have power.");
        }
    }

    public void setHealTextBattleTextArea(int healPoints) {
        if (healPoints == 0) {
            battleTextArea.setText("Player doesn't have power.");
        }
        else {
            battleTextArea.setText("Player heals " + healPoints + " life points.");

            if (TurnRepository.getTurns().getLast().isCriticalHeal()) {
                battleTextArea.setText("CRITICAL HEAL!\nPlayer heals all his life points.");
            }
        }
    }

    public void enemyAction() {
        int damage = EnemyUtils.attack(EnemyRepository.getEnemies().getLast(), PlayerRepository.getPlayers().getLast(), TurnRepository.getTurns().getLast());

        if (GameRepository.getGames().getLast().isGameOver()) {
            setTextPlayerTextArea();
            endGame(damage);
        }
        else {
            setAttackTextBattleTextArea(damage);
            setTextPlayerTextArea();
            setTextEnemyTextArea();
            TurnUtils.newTurn(false);
            setBattleTextBattleTextField();
        }
    }

    public void endGame(int damage) {
        battleTextArea.setText(setAttackTextBattleTextArea(damage) + "\nPlayer defeated!");
        setButtonEnabled(false);
    }

    public void endBattle(int damage) {
        String levelUpText = PlayerUtils.levelUp(PlayerRepository.getPlayers().getLast());
        PlayerUtils.recoverPower(PlayerRepository.getPlayers().getLast());

        battleTextArea.setText(setAttackTextBattleTextArea(damage) + "\nEnemy defeated!" + levelUpText);

        BattleUtils.newBattle(false);
        setTextEnemyTextArea();
        setTextPlayerTextArea();
    }
}

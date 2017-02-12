package game;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatsPanel extends JPanel
{

    JButton newGameButton;
    GamePanel gp;

    JLabel currentRound;
    JLabel highScore;
    
    private int highScoreInt;

    public StatsPanel(GamePanel gp)
    {
        this.gp = gp;
        highScoreInt = 0;
        setUpContents();

    }

    private void setUpContents()
    {

        setLayout(new GridLayout(1, 3));

        currentRound = new JLabel("current level: 0");
        currentRound.setHorizontalAlignment(JLabel.CENTER);
        add(currentRound);

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                gp.startNewGame();

            }
        });

        newGameButton.setHorizontalAlignment(JLabel.CENTER);
        add(newGameButton);

        highScore = new JLabel("high score: 0");
        highScore.setHorizontalAlignment(JLabel.CENTER);
        add(highScore);

    }
    
    public void updateLabels(int currentLevel){
        if(currentLevel > highScoreInt){
            highScoreInt = currentLevel;
            highScore.setText("high score: " + highScoreInt);
        }
        currentRound.setText("current level: " + currentLevel);
    }

}

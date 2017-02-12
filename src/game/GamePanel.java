package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import main.Manager;
import variables.GameSpeed;

public class GamePanel extends JFrame implements ActionListener
{

    public static final int HEIGHT = 400;
    public static final int WIDTH = 400;
    private final String TITLE = "Simon Says Game";

    private final int DELAY = GameSpeed.DISPLAYING_DELAY;
    private Timer printingTimer;

    private ArrayList<ColorPanel> panels;

    
    private StatsPanel statsPanel;
    private Manager manager;

    public GamePanel(Manager manager)
    {
        this.manager = manager;
        setTitle(TITLE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        
        setUpPanels();

        printingTimer = new Timer(DELAY, this);
        printingTimer.setRepeats(false);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void setUpPanels()
    {

        // setLayout(new GridLayout(2, 2, MARGINS, MARGINS));
        
        setLayout(new BorderLayout());
        
        statsPanel = new StatsPanel(this);
        add(statsPanel, BorderLayout.NORTH);
        
        
        JPanel gamePanel = new JPanel();
        
        gamePanel.setLayout(new GridLayout(2, 2));

        panels = new ArrayList<ColorPanel>();
        panels.add(new ColorPanel(this, 0));
        panels.add(new ColorPanel(this, 1));
        panels.add(new ColorPanel(this, 2));
        panels.add(new ColorPanel(this, 3));

        gamePanel.add(panels.get(0));
        gamePanel.add(panels.get(1));
        gamePanel.add(panels.get(2));
        gamePanel.add(panels.get(3));
        
        add(gamePanel, BorderLayout.CENTER);
    }

    public void panelClicked(int colorNumber)
    {
        manager.panelClicked(colorNumber);
    }

    public void panelEditability(boolean editable)
    {
        for (int i = 0; i < panels.size(); i++)
        {
            panels.get(i).setClickable(editable);
        }
    }

    public void printOrderToScreen(int colorNumber)
    {

//        System.out.println(panels.get(colorNumber).getColorNumber());

        panels.get(colorNumber).setFlashColor();

        printingTimer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (int i = 0; i < panels.size(); i++)
        {
            panels.get(i).setNormalColor();
        }
        manager.printNextOrder();
    }

    public void startNewGame(){
        manager.startNewGame();
    }
    
    public void updateStatsLabels(int currentLevel){
        statsPanel.updateLabels(currentLevel);
    }
    
    public boolean checkIfGoodResults(int colorNumber){
        return manager.checkIfGoodResults(colorNumber);
    }
    
    public void wrongAnswer()
    {
        manager.wrongAnswer();
    }
    
}

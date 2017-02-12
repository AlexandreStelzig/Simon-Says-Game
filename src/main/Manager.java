package main;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import game.GameLoop;
import game.GamePanel;

public class Manager
{

    private GameLoop gameLoop;
    private int currentIndex;
    private int printingOrder;
    private GamePanel gamePanel;
    private Timer printingTimer;
    private Timer printingTimerShort;

    private final int DELAYLONG = 1000;
    private final int DELAYSHORT = 200;

    public Manager()
    {
        gameLoop = new GameLoop();
        currentIndex = 0;
        printingOrder = 0;
        gamePanel = new GamePanel(this);
        ManagerAction ma = new ManagerAction(this);
        printingTimer = new Timer(DELAYLONG, ma);
        printingTimer.setRepeats(false);
        printingTimerShort = new Timer(DELAYSHORT, ma);
        printingTimerShort.setRepeats(false);

        gamePanel.panelEditability(false);
    }

    public void panelClicked(int colorNumber)
    {

        currentIndex++;
        if (currentIndex > (gameLoop.getLevel() - 1))
        {
            gameLoop.createNextOrder();
            currentIndex = 0;
            printNextOrder();
            gamePanel.updateStatsLabels(gameLoop.getLevel());
        }

    }

    public void wrongAnswer()
    {
        JOptionPane.showMessageDialog(gamePanel, "Game Over\nLevel : " + gameLoop.getLevel());
        resetGame();
    }

    private void resetGame()
    {

        gameLoop.resetGame();
        currentIndex = 0;
        gamePanel.updateStatsLabels(0);
        gamePanel.panelEditability(false);
    }

    public void startNewGame()
    {
        gameLoop.resetGame();
        currentIndex = 0;
        printNextOrder();
        gamePanel.updateStatsLabels(gameLoop.getLevel());
    }

    public void printNextOrder()
    {

        if (printingOrder > (gameLoop.getLevel() - 1))
        {
            printingOrder = 0;
//            gameLoop.print();

            gamePanel.panelEditability(true);
            return;
        }

        if (printingOrder == 0)
        {
            printingTimer.start();
        } else
        {
            printingTimerShort.start();
        }

    }

    public void printOnScreen()
    {
        gamePanel.panelEditability(false);

//        System.out.println("Printing on " + gameLoop.getOrder(printingOrder));
        gamePanel.printOrderToScreen(gameLoop.getOrder(printingOrder));

        printingOrder++;
    }

    public boolean checkIfGoodResults(int colorNumber)
    {
        return colorNumber == gameLoop.getOrder(currentIndex);
    }

    public static void main(String[] args)
    {
        new Manager();
    }

}

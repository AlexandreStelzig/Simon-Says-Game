package game;

import java.util.ArrayList;

public class GameLoop
{

    private ArrayList<Integer> gameOrder;

    public GameLoop()
    {
        gameOrder = new ArrayList<>();
        createNextOrder();
    }
    
    public void resetGame(){
        gameOrder.clear();
        createNextOrder();
    }

    public void createNextOrder()
    {
        gameOrder.add((int) (Math.random() * 4));
    }

    public int getOrder(int pos)
    {
        return gameOrder.get(pos);
    }

    public int getLevel()
    {
        return gameOrder.size();
    }

    public void print()
    {
        System.out.println("---- LEVEL " + getLevel() + " ----");
        for (int i = 0; i < getLevel(); i++)
        {
            System.out.println(getOrder(i));
        }
    }

}

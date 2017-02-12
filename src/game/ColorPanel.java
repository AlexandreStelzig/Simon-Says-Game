package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import variables.ColorVariables;
import variables.GameSpeed;

public class ColorPanel extends JPanel implements MouseListener, ActionListener
{

    GamePanel gp;

    private final int DELAY = GameSpeed.CHOICE_COLOR_DELAY;

    private boolean clickable;
    private Timer timer;

    private Color normalColor;
    private Color flashColor;

    private int colorNumber;

    public int getColorNumber()
    {
        return colorNumber;
    }

    private boolean insidePanel;

    public ColorPanel(GamePanel gp, int colorNumber)
    {
        this.colorNumber = colorNumber;
        this.gp = gp;
        this.setOpaque(true);
        defineBackground();
        clickable = true;
        insidePanel = false;
        timer = new Timer(DELAY, this);
        timer.setRepeats(false);
        addMouseListener(this);

    }

    private void defineBackground()
    {
        if (colorNumber == 0)
        {
            normalColor = ColorVariables.NORMAL_GREEN;
            flashColor = ColorVariables.FLASH_GREEN;
        }
        if (colorNumber == 1)
        {
            normalColor = ColorVariables.NORMAL_RED;
            flashColor = ColorVariables.FLASH_RED;
        }
        if (colorNumber == 2)
        {
            normalColor = ColorVariables.NORMAL_YELLOW;
            flashColor = ColorVariables.FLASH_YELLOW;
        }
        if (colorNumber == 3)
        {
            normalColor = ColorVariables.NORMAL_BLUE;
            flashColor = ColorVariables.FLASH_BLUE;
        }
        setBackground(normalColor);

    }

    public boolean isClickable()
    {
        return clickable;
    }

    public void setClickable(boolean clickable)
    {
        this.clickable = clickable;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e)
    {

    }

    public void changeColorTimer()
    {
        timer.start();
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e)
    {
        if (clickable && insidePanel)
        {
            setFlashColor();
        }

    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e)
    {
        if (clickable)
        {
            if (!gp.checkIfGoodResults(colorNumber))
            {
                gp.wrongAnswer();
                setNormalColor();
            } else if (insidePanel)
            {
                clickable = false;
                gp.panelClicked(colorNumber);
                changeColorTimer();
            }
        }

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e)
    {
        // TODO Auto-generated method stub
        insidePanel = true;

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e)
    {
        // TODO Auto-generated method stub
        insidePanel = false;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        setNormalColor();
        clickable = true;

    }

    public void setNormalColor()
    {
        setBackground(normalColor);
    }

    public void setFlashColor()
    {
        setBackground(flashColor);
    }

}

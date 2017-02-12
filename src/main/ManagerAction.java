package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerAction implements ActionListener
{
    Manager manager;

    public ManagerAction(Manager manager)
    {
        this.manager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        manager.printOnScreen();

    }

}

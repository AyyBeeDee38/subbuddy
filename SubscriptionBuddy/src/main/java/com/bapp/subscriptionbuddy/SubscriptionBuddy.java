/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.bapp.subscriptionbuddy;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Mustafa
 */
public class SubscriptionBuddy {

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Login loginFrame = new Login();
        loginFrame.setVisible(true);
        
        

        int x = (screenSize.width - loginFrame.getWidth()) / 2;
        int y = (screenSize.height - loginFrame.getHeight()) / 2;
        
        

        // Set the location of the frame
        loginFrame.setLocation(x, y);
    }
}

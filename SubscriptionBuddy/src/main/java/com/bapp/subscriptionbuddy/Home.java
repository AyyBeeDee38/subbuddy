/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.bapp.subscriptionbuddy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Mustafa
 */
public class Home extends javax.swing.JFrame {
    ImageIcon imageYoutube = new ImageIcon("youtube.png");
    ImageIcon imageNetflix = new ImageIcon("netflix.png");
    ImageIcon imageSpotify = new ImageIcon("spotify.png");
    ImageIcon imageAmazon = new ImageIcon("amazon.png");
    ImageIcon ImageFoodpanda = new ImageIcon("foodpanda.png");
    ImageIcon imageHBOMax = new ImageIcon("hbomax.png");
    ImageIcon imageHulu = new ImageIcon("hulu.png");
    ImageIcon imageCrunchyRoll = new ImageIcon("crunchyroll.png");
    
    private static Subscription userData;
    List<String> purchaseRecords = new ArrayList<>();
   
     public void reloadTextFileToClass() {   
         Login reloadUser = new Login();
         this.userData = reloadUser.fetchAllUserData(this.userData.getUsername());
    }
       
    /**
     * Creates new form Home  
     */
    
    
    /** @param userData */
    public Home(Subscription userData) {
        initComponents();
        this.userData = userData;
        this.purchaseRecords = this.userData.readPurchaseHistory();
        youtubeLabel.setIcon(imageYoutube);
        netflixLabel.setIcon(imageNetflix);
        spotifyLabel.setIcon(imageSpotify);
        amazonLabel.setIcon(imageAmazon);
        foodpandaLabel.setIcon(ImageFoodpanda);
        hbomaxLabel.setIcon(imageHBOMax);
        HuluLabel.setIcon(imageHulu);
        crunchyrollLabel.setIcon(imageCrunchyRoll);
        
        if (!purchaseRecords.isEmpty()) {
            displayPurchaseHistory();
        }
        
        
       
        LoggedUser.setText("Logged in user: " + userData.getUsername());
        AvailableCredits.setText("Available Credits: " + userData.getCredits());
        
        checkIfSubscribed();
    }
    
    private void displayPurchaseHistory() {
    int recordCount = purchaseRecords.size() / 3;

    for (int i = 0; i < 5 && i < recordCount; i++) {
        int baseIndex = i * 3;  // Correct index calculation
        String username = purchaseRecords.get(baseIndex);
        String subscription = purchaseRecords.get(baseIndex + 1);
        String price = purchaseRecords.get(baseIndex + 2);
        String displayText = username + " bought " + subscription + " with price " + price;

        switch (i) {
            case 0:
                purchase1.setText(displayText);
                break;
            case 1:
                purchase2.setText(displayText);
                break;
            case 2:
                purchase3.setText(displayText);
                break;
            case 3:
                purchase4.setText(displayText);
                break;
            case 4:
                purchase5.setText(displayText);
                break;
            default:
                break;
        }
    }
}
    
    private void checkIfSubscribed() {
        if (this.userData.getYoutubeSubscription()) { BuyYoutube.setText("Subscribed"); BuyYoutube.setEnabled(false); }
        if (this.userData.getNetflixSubscription()) { BuyNetflix.setText("Subscribed"); BuyNetflix.setEnabled(false); }
        if (this.userData.getSpotifySubscription()) { BuySpotify.setText("Subscribed"); BuySpotify.setEnabled(false); }
        if (this.userData.getAmazonSubscription()) { BuyAmazon.setText("Subscribed"); BuyAmazon.setEnabled(false); }
        if (this.userData.getFoodpandaSubscription()) { BuyFoodpanda.setText("Subscribed"); BuyFoodpanda.setEnabled(false); }
        if (this.userData.getHbomaxSubscription()) { BuyHBO.setText("Subscribed"); BuyHBO.setEnabled(false); }
        if (this.userData.getHuluSubscription()) { BuyHulu.setText("Subscribed"); BuyHulu.setEnabled(false); }
        if (this.userData.getCrunchyrollSubscription()) { BuyCrunchyroll.setText("Subscribed"); BuyCrunchyroll.setEnabled(false); }
    }
    
    public Subscription fetchAllUserData(String username){
    try {
        BufferedReader br = new BufferedReader(new FileReader("users.txt"));
        String uname;
        String pass = "";
        int credits = 0;
        String youtubeSub = "";
        String netflixSub  = "";
        String spotifySub  = "";
        String amazonSub  = "";
        String foodpandaSub  = "";
        String hbomaxSub  = "";
        String huluSub  = "";
        String crunchyrollSub  = "";

        while ((uname = br.readLine()) != null) {
            if (uname.equals(username)) {
                pass = br.readLine();
                credits = Integer.parseInt(br.readLine());
                youtubeSub = br.readLine();
                netflixSub = br.readLine();
                spotifySub = br.readLine();
                amazonSub = br.readLine();
                foodpandaSub = br.readLine();
                hbomaxSub = br.readLine();
                huluSub = br.readLine();
                crunchyrollSub = br.readLine();

                break; // Exit the loop since the user is found
            } else {
                // Skip reading the data for this user
                for (int i = 0; i < 10; i++) {
                    br.readLine(); // Skip 8 lines (assuming each user has 8 lines of data)
                }
            }
        }
        
        Subscription userData = new Subscription(uname, pass, credits,
                                            Boolean.parseBoolean(youtubeSub),
                                            Boolean.parseBoolean(netflixSub),
                                            Boolean.parseBoolean(spotifySub),
                                            Boolean.parseBoolean(amazonSub),
                                            Boolean.parseBoolean(foodpandaSub),
                                            Boolean.parseBoolean(hbomaxSub),
                                            Boolean.parseBoolean(huluSub),
                                            Boolean.parseBoolean(crunchyrollSub));
        br.close(); // Close the BufferedReader
        return userData;
        
    } catch (IOException ex) {
        ex.printStackTrace();
    }
        return null;
}
    
    public void updatePurchaseHistory(int num, String username,String subscription, String price) {
         switch(num) {
             case 1 : purchase1.setText(username + " subscribed to " + subscription + " for " + price + " Credits."); break;
             case 2 : purchase2.setText(username + " subscribed to " + subscription + " for " + price + " Credits."); break;
             case 3 : purchase3.setText(username + " subscribed to " + subscription + " for " + price + " Credits."); break;
             case 4 : purchase4.setText(username + " subscribed to " + subscription + " for " + price + " Credits."); break;
             case 5 : purchase5.setText(username + " subscribed to " + subscription + " for " + price + " Credits."); break;
         }
     } 
    
    
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BuyCreditsButton = new javax.swing.JButton();
        TransferCreditsButton = new javax.swing.JButton();
        youtubeLabel = new javax.swing.JLabel();
        netflixLabel = new javax.swing.JLabel();
        spotifyLabel = new javax.swing.JLabel();
        amazonLabel = new javax.swing.JLabel();
        foodpandaLabel = new javax.swing.JLabel();
        hbomaxLabel = new javax.swing.JLabel();
        HuluLabel = new javax.swing.JLabel();
        crunchyrollLabel = new javax.swing.JLabel();
        BuyYoutube = new javax.swing.JButton();
        BuyNetflix = new javax.swing.JButton();
        BuySpotify = new javax.swing.JButton();
        BuyAmazon = new javax.swing.JButton();
        BuyFoodpanda = new javax.swing.JButton();
        BuyHulu = new javax.swing.JButton();
        BuyHBO = new javax.swing.JButton();
        BuyCrunchyroll = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        LoggedUser = new javax.swing.JLabel();
        AvailableCredits = new javax.swing.JLabel();
        ReloadButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        purchase1 = new javax.swing.JLabel();
        purchase2 = new javax.swing.JLabel();
        purchase3 = new javax.swing.JLabel();
        purchase4 = new javax.swing.JLabel();
        purchase5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        BuyCreditsButton.setText("Buy Credits");
        BuyCreditsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyCreditsButtonActionPerformed(evt);
            }
        });

        TransferCreditsButton.setText("Transfer Credits");
        TransferCreditsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransferCreditsButtonActionPerformed(evt);
            }
        });

        youtubeLabel.setText(".");

        netflixLabel.setText(".");

        spotifyLabel.setText(".");

        amazonLabel.setText(".");

        foodpandaLabel.setText(".");

        hbomaxLabel.setText(".");

        HuluLabel.setText(".");

        crunchyrollLabel.setText(".");

        BuyYoutube.setText("400 Credits");
        BuyYoutube.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyYoutubeActionPerformed(evt);
            }
        });

        BuyNetflix.setText("600 Credits");
        BuyNetflix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyNetflixActionPerformed(evt);
            }
        });

        BuySpotify.setText("200 Credits");
        BuySpotify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuySpotifyActionPerformed(evt);
            }
        });

        BuyAmazon.setText("300 Credits");
        BuyAmazon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyAmazonActionPerformed(evt);
            }
        });

        BuyFoodpanda.setText("150 Credits");
        BuyFoodpanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyFoodpandaActionPerformed(evt);
            }
        });

        BuyHulu.setText("450 Credits");
        BuyHulu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyHuluActionPerformed(evt);
            }
        });

        BuyHBO.setText("500 Credits");
        BuyHBO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyHBOActionPerformed(evt);
            }
        });

        BuyCrunchyroll.setText("360 Credits");
        BuyCrunchyroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyCrunchyrollActionPerformed(evt);
            }
        });

        jLabel1.setText("Youtube Premium");

        jLabel2.setText("Netflix");

        jLabel3.setText("Spotify Premium");

        jLabel4.setText("Amazon Prime");

        jLabel5.setText("Foodpanda Pro");

        jLabel6.setText("HBO Max");

        jLabel7.setText("Hulu");

        jLabel8.setText("CrunchyRoll");

        LoggedUser.setText("Logged in user: User");

        AvailableCredits.setText("Available Credits: 1000");

        ReloadButton.setText("⟳");
        ReloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReloadButtonActionPerformed(evt);
            }
        });

        jLabel9.setText("PurchaseHistory");

        purchase1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        purchase2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        purchase3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        purchase4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        purchase5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(foodpandaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(youtubeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(BuyYoutube, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(BuyFoodpanda))
                                            .addComponent(jLabel1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(BuyNetflix, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(netflixLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(BuyHBO, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(hbomaxLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(73, 73, 73)
                                        .addComponent(jLabel6)))))
                        .addGap(1, 1, 1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(31, 31, 31)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel8))
                            .addComponent(crunchyrollLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spotifyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BuySpotify, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(HuluLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BuyHulu, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BuyCrunchyroll, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BuyAmazon, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3)
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(amazonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LoggedUser, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AvailableCredits, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(purchase1)
                            .addComponent(jLabel9)
                            .addComponent(purchase2)
                            .addComponent(purchase3)
                            .addComponent(purchase4)
                            .addComponent(purchase5))
                        .addGap(0, 207, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BuyCreditsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TransferCreditsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ReloadButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReloadButton)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(youtubeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(netflixLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spotifyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(amazonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LoggedUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AvailableCredits)
                        .addGap(35, 35, 35)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BuyYoutube)
                            .addComponent(BuyNetflix)
                            .addComponent(BuySpotify)
                            .addComponent(BuyAmazon))
                        .addGap(104, 104, 104)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(foodpandaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hbomaxLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HuluLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crunchyrollLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BuyFoodpanda)
                            .addComponent(BuyHBO)
                            .addComponent(BuyHulu)
                            .addComponent(BuyCrunchyroll))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BuyCreditsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TransferCreditsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(purchase1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(purchase2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(purchase3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(purchase4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(purchase5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuyCreditsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyCreditsButtonActionPerformed
       JFrame parentFrame = (JFrame) this.getRootPane().getParent(); 
       BuyCreditsDialog buyCreditsFrame = new BuyCreditsDialog(parentFrame, true, this.userData);
       buyCreditsFrame.setLocationRelativeTo(null);
       buyCreditsFrame.setVisible(true);
       reloadTextFileToClass();
       AvailableCredits.setText("Available Credits: " + this.userData.getCredits());
       
       checkIfSubscribed();
       
    }//GEN-LAST:event_BuyCreditsButtonActionPerformed

    private void TransferCreditsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransferCreditsButtonActionPerformed
        JFrame parentFrame = (JFrame) this.getRootPane().getParent(); 
        TransferCreditDialog transferCreditsDialog = new TransferCreditDialog(parentFrame, true, this.userData);
        transferCreditsDialog.setLocationRelativeTo(null);
        transferCreditsDialog.setVisible(true);
        reloadTextFileToClass();
        AvailableCredits.setText("Available Credits: " + this.userData.getCredits());
        checkIfSubscribed();
        this.purchaseRecords = this.userData.readPurchaseHistory();
        displayPurchaseHistory();
    }//GEN-LAST:event_TransferCreditsButtonActionPerformed

    private void BuyYoutubeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyYoutubeActionPerformed
        JFrame parentFrame = (JFrame) this.getRootPane().getParent();
        BuySubscriptionDialog buySub = new BuySubscriptionDialog( parentFrame, true, this.userData, "youtube", 400);
        buySub.setVisible(true);
        reloadTextFileToClass();
        AvailableCredits.setText("Available Credits: " + this.userData.getCredits());
        checkIfSubscribed();
        this.purchaseRecords = this.userData.readPurchaseHistory();
        displayPurchaseHistory();
    }//GEN-LAST:event_BuyYoutubeActionPerformed

    private void BuyNetflixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyNetflixActionPerformed
        JFrame parentFrame = (JFrame) this.getRootPane().getParent();
        BuySubscriptionDialog buySub = new BuySubscriptionDialog( parentFrame, true , this.userData, "netflix", 600);
        buySub.setVisible(true);
        reloadTextFileToClass();
        AvailableCredits.setText("Available Credits: " + this.userData.getCredits());
        checkIfSubscribed();
        this.purchaseRecords = this.userData.readPurchaseHistory();
        displayPurchaseHistory();
    }//GEN-LAST:event_BuyNetflixActionPerformed

    private void BuySpotifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuySpotifyActionPerformed
        JFrame parentFrame = (JFrame) this.getRootPane().getParent();
        BuySubscriptionDialog buySub = new BuySubscriptionDialog( parentFrame, true , this.userData, "spotify", 200);
        buySub.setVisible(true);
        reloadTextFileToClass();
        AvailableCredits.setText("Available Credits: " + this.userData.getCredits());
        checkIfSubscribed();
        this.purchaseRecords = this.userData.readPurchaseHistory();
        displayPurchaseHistory();
    }//GEN-LAST:event_BuySpotifyActionPerformed

    private void BuyAmazonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyAmazonActionPerformed
        JFrame parentFrame = (JFrame) this.getRootPane().getParent();
        BuySubscriptionDialog buySub = new BuySubscriptionDialog( parentFrame, true , this.userData, "amazon", 300);
        buySub.setVisible(true);
        reloadTextFileToClass();
        AvailableCredits.setText("Available Credits: " + this.userData.getCredits());
        checkIfSubscribed();
        this.purchaseRecords = this.userData.readPurchaseHistory();
        displayPurchaseHistory();
    }//GEN-LAST:event_BuyAmazonActionPerformed

    private void BuyFoodpandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyFoodpandaActionPerformed
        JFrame parentFrame = (JFrame) this.getRootPane().getParent();
        BuySubscriptionDialog buySub = new BuySubscriptionDialog( parentFrame, true , this.userData, "foodpanda", 150);
        buySub.setVisible(true);
        reloadTextFileToClass();
        AvailableCredits.setText("Available Credits: " + this.userData.getCredits());
        checkIfSubscribed();
        this.purchaseRecords = this.userData.readPurchaseHistory();
        displayPurchaseHistory();
    }//GEN-LAST:event_BuyFoodpandaActionPerformed

    private void BuyHuluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyHuluActionPerformed
       JFrame parentFrame = (JFrame) this.getRootPane().getParent();
        BuySubscriptionDialog buySub = new BuySubscriptionDialog( parentFrame, true , this.userData, "hulu", 450) ;
        buySub.setVisible(true);
        reloadTextFileToClass();
        AvailableCredits.setText("Available Credits: " + this.userData.getCredits());
        checkIfSubscribed();
        this.purchaseRecords = this.userData.readPurchaseHistory();
        displayPurchaseHistory();
    }//GEN-LAST:event_BuyHuluActionPerformed

    private void BuyHBOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyHBOActionPerformed
        JFrame parentFrame = (JFrame) this.getRootPane().getParent();
        BuySubscriptionDialog buySub = new BuySubscriptionDialog( parentFrame, true , this.userData, "hbo", 500);
        buySub.setVisible(true);
        reloadTextFileToClass();
        AvailableCredits.setText("Available Credits: " + this.userData.getCredits());
        checkIfSubscribed();
        this.purchaseRecords = this.userData.readPurchaseHistory();
        displayPurchaseHistory();
    }//GEN-LAST:event_BuyHBOActionPerformed

    private void BuyCrunchyrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyCrunchyrollActionPerformed
        JFrame parentFrame = (JFrame) this.getRootPane().getParent();
        BuySubscriptionDialog buySub = new BuySubscriptionDialog( parentFrame, true, this.userData, "crunchyroll", 360 );
        buySub.setVisible(true);
        reloadTextFileToClass();
        AvailableCredits.setText("Available Credits: " + this.userData.getCredits());
        checkIfSubscribed();
        this.purchaseRecords = this.userData.readPurchaseHistory();
        displayPurchaseHistory();
    }//GEN-LAST:event_BuyCrunchyrollActionPerformed

    private void ReloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReloadButtonActionPerformed
        reloadTextFileToClass();
        AvailableCredits.setText("Available Credits: " + this.userData.getCredits());
        checkIfSubscribed();
        this.purchaseRecords = this.userData.readPurchaseHistory();
        displayPurchaseHistory();
    }//GEN-LAST:event_ReloadButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home(userData).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AvailableCredits;
    private javax.swing.JButton BuyAmazon;
    private javax.swing.JButton BuyCreditsButton;
    private javax.swing.JButton BuyCrunchyroll;
    private javax.swing.JButton BuyFoodpanda;
    private javax.swing.JButton BuyHBO;
    private javax.swing.JButton BuyHulu;
    private javax.swing.JButton BuyNetflix;
    private javax.swing.JButton BuySpotify;
    private javax.swing.JButton BuyYoutube;
    private javax.swing.JLabel HuluLabel;
    private javax.swing.JLabel LoggedUser;
    private javax.swing.JButton ReloadButton;
    private javax.swing.JButton TransferCreditsButton;
    private javax.swing.JLabel amazonLabel;
    private javax.swing.JLabel crunchyrollLabel;
    private javax.swing.JLabel foodpandaLabel;
    private javax.swing.JLabel hbomaxLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel netflixLabel;
    private javax.swing.JLabel purchase1;
    private javax.swing.JLabel purchase2;
    private javax.swing.JLabel purchase3;
    private javax.swing.JLabel purchase4;
    private javax.swing.JLabel purchase5;
    private javax.swing.JLabel spotifyLabel;
    private javax.swing.JLabel youtubeLabel;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bapp.subscriptionbuddy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mustafa
 */
public class Subscription extends User {
    
    private boolean youtubeSubscription;
    private boolean netflixSubscription;
    private boolean spotifySubscription;
    private boolean amazonSubscription;
    private boolean foodpandaSubscription;
    private boolean hbomaxSubscription;
    private boolean huluSubscription;
    private boolean crunchyrollSubscription;
    
    
    public Subscription(String username, String password, int credits,  boolean youtubeSubscription, boolean netflixSubscription, boolean spotifySubscription, boolean amazonSubscription, boolean foodpandaSubscription, boolean hbomaxSubscription, boolean huluSubscription, boolean crunchyrollSubscription) {
        super(username, password,credits ); 
        this.youtubeSubscription = youtubeSubscription;
        this.netflixSubscription = netflixSubscription;
        this.spotifySubscription = spotifySubscription;
        this.amazonSubscription = amazonSubscription;
        this.foodpandaSubscription = foodpandaSubscription;
        this.hbomaxSubscription = hbomaxSubscription;
        this.huluSubscription = huluSubscription;
        this.crunchyrollSubscription = crunchyrollSubscription;
    }
    
     public void writeClassToFile() {
    try {
        // Step 1: Read the file into a list of strings
        BufferedReader br = new BufferedReader(new FileReader("users.txt"));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();

        // Step 2: Find the target username and update the required fields
        boolean[] subscriptionStatuses = {
            this.youtubeSubscription, this.netflixSubscription, this.spotifySubscription,
            this.amazonSubscription, this.foodpandaSubscription, this.hbomaxSubscription,
            this.huluSubscription, this.crunchyrollSubscription
        };
        
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals(this.getUsername())) {
                if (i + 2 < lines.size()) {
                    lines.set(i + 2, Integer.toString(this.getCredits()));
                }
                for (int j = 0; j < subscriptionStatuses.length; j++) {
                    if (i + 3 + j < lines.size()) {
                        lines.set(i + 3 + j, Boolean.toString(subscriptionStatuses[j]));
                    }
                }
                break;
            }
        }

        // Step 3: Write the updated lines back to the file (overwriting the file)
        BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt"));
        for (String updatedLine : lines) {
            bw.write(updatedLine);
            bw.newLine();
        }
        bw.close();

    } catch (IOException ex) {
        ex.printStackTrace();
    }
}
     
     public void writePurchaseHistory(String subscription, int price) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("purchase.txt", true))) {
            String username = this.getUsername();
            bw.write(username);
            bw.newLine();
            bw.write(subscription);
            bw.newLine();
            bw.write(Integer.toString(price));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
     public List<String> readPurchaseHistory() {
        List<String> purchaseRecords = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("purchase.txt"))) {
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String username = line;
                String subscription = br.readLine();
                String price = br.readLine();

                if (username == null || subscription == null || price == null) {
                    System.out.println("Invalid record found in the file.");
                    continue;
                }

                if (username.equals(this.getUsername())) {
                    purchaseRecords.add(username);
                    purchaseRecords.add(subscription);
                    purchaseRecords.add(price);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No records found for username: " + this.getUsername());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the purchase history file.");
            e.printStackTrace();
        }

        return purchaseRecords;
    }
     
     
     
     
    
     public boolean getYoutubeSubscription() {
        return youtubeSubscription;
    }

    public void setYoutubeSubscription(boolean youtubeSubscription) {
        this.youtubeSubscription = youtubeSubscription;
    }

    public boolean getNetflixSubscription() {
        return netflixSubscription;
    }

    public void setNetflixSubscription(boolean netflixSubscription) {
        this.netflixSubscription = netflixSubscription;
    }

    public boolean getSpotifySubscription() {
        return spotifySubscription;
    }

    public void setSpotifySubscription(boolean spotifySubscription) {
        this.spotifySubscription = spotifySubscription;
    }

    public boolean getAmazonSubscription() {
        return amazonSubscription;
    }

    public void setAmazonSubscription(boolean amazonSubscription) {
        this.amazonSubscription = amazonSubscription;
    }

    public boolean getFoodpandaSubscription() {
        return foodpandaSubscription;
    }

    public void setFoodpandaSubscription(boolean foodpandaSubscription) {
        this.foodpandaSubscription = foodpandaSubscription;
    }

    public boolean getHbomaxSubscription() {
        return hbomaxSubscription;
    }

    public void setHbomaxSubscription(boolean hbomaxSubscription) {
        this.hbomaxSubscription = hbomaxSubscription;
    }

    public boolean getHuluSubscription() {
        return huluSubscription;
    }

    public void setHuluSubscription(boolean huluSubscription) {
        this.huluSubscription = huluSubscription;
    }

    public boolean getCrunchyrollSubscription() {
        return crunchyrollSubscription;
    }

    public void setCrunchyrollSubscription(boolean crunchyrollSubscription) {
        this.crunchyrollSubscription = crunchyrollSubscription;
    }

}

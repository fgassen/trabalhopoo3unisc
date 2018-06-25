/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc;

import java.util.List;
import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.api.DirectMessagesResources;
import twitter4j.conf.ConfigurationBuilder;
/**
 *
 * @author root
 */
public class Message {
    
    Twitter twitter;
     
    public Message() throws TwitterException {     
        twitter = new Login().getTwitter();
    }
      
    void sendMessage() throws TwitterException{ 
        //message = ((DirectMessagesResources) twitter).sendDirectMessage(string, string1)
        //Status status = twitter.updateStatus(latestStatus);
        try {
            DirectMessage message = twitter.sendDirectMessage("1001591854650019841", "sasas");
            System.out.println("Direct message successfully sent to " + message.getRecipientScreenName());
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to send a direct message: " + te.getMessage());
            System.exit(-1);
        }
    }
    
    
    void showMessage(){

        try {
            
            DirectMessage message = twitter.showDirectMessage(Long.parseLong("1001591854650019841"));
            System.out.println("From: @" + message.getSenderScreenName() + " id:" + message.getId() + " - "
                    + message.getText());
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get message: " + te.getMessage());
            System.exit(-1);
        }

    }
    
    void showSendMessage(){

         try {
            Paging paging = new Paging(1);
            List<DirectMessage> messages;
            do {
                messages = twitter.getSentDirectMessages(paging);
                for (DirectMessage message : messages) {
                    System.out.println("From: @" + message.getSenderScreenName() + " id:" + message.getId() + " - "
                            + message.getText());
                }
                paging.setPage(paging.getPage() + 1);
            } while (messages.size() > 0 && paging.getPage() < 10);
            System.out.println("done.");
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get messages: " + te.getMessage());
            System.exit(-1);
        }
    }
    
    void receiveMessage() throws TwitterException{ 
        
        try {
            Paging paging = new Paging(1);
            List<DirectMessage> messages;
            do {
                messages = twitter.getDirectMessages(paging);
                for (DirectMessage message : messages) {
                    System.out.println("From: @" + message.getSenderScreenName() + " id:" + message.getId() + " - "
                            + message.getText());
                }
                paging.setPage(paging.getPage() + 1);
            } while (messages.size() > 0 && paging.getPage() < 10);
            System.out.println("done.");
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get messages: " + te.getMessage());
            System.exit(-1);
        }
    } 
}

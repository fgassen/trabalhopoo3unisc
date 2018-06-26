/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc;

import java.util.List;
import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusUpdate;
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
    List<Status> tweets;
    int current;
    boolean condition;
    
    public Message(){
        twitter = new Login().getTwitter();
        current = 0;
        condition = true;
    }
      
    void receivetweets(){
        try {
           Query query = new Query("@Poo3Trabalho");
           QueryResult result = twitter.search(query);
           tweets = result.getTweets();
           for (Status tweet : tweets) {
               System.out.println("User:"+tweet.getUser().getId()+" "+tweet.getText());
           }
            replyTo(tweets);
        }catch(TwitterException te){
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
    } 
    
    public void replyTo(List<Status> tweets) {
        Status reply = null;
        for (Status tweet : tweets) {
            try {
                reply = twitter.updateStatus(new StatusUpdate("@" + tweet.getUser().getScreenName() + " this is a reply to your tweet.").inReplyToStatusId(tweet.getId()));
                System.out.println("Posted reply " + reply.getId() + " in response to tweet " + reply.getInReplyToStatusId());
            } catch (TwitterException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        }
    }
    
    
    
    public void showTwittersMe(){
        try {
            Paging paging = new Paging(1);
            List<Status> messages;
            do {
                messages = twitter.getRetweetsOfMe(paging);
                for (Status message : messages) {
                    System.out.println(message.toString());
                }
                paging.setPage(paging.getPage() + 1);
            } while (messages.size() > 0 && paging.getPage() < 10);
            System.out.println("done.");
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get messages: " + te.getMessage());
        }
        
    }
    
    void sendDirectMessage() throws TwitterException{ 
        //message = ((DirectMessagesResources) twitter).sendDirectMessage(string, string1)
        //Status status = twitter.updateStatus(latestStatus);
        try {
            DirectMessage message = twitter.sendDirectMessage("1001591854650019841", "sasas");
            System.out.println("Direct message successfully sent to " + message.getRecipientScreenName());
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to send a direct message: " + te.getMessage());
        }
    }
    
    void showDirectMessage(){
        try {
            Paging paging = new Paging(1);
            List<DirectMessage> messages = twitter.getDirectMessages(paging);
            for (DirectMessage message : messages) {
                System.out.println("From: @" + message.getSenderScreenName() + " id:" + message.getId() + " - "
                    + message.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get message: " + te.getMessage());
        }

    }
    
    void showDirectSendMessage(){

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
        }
    }
    
    void receiveDirectMessage() throws TwitterException{ 
        
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
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get messages: " + te.getMessage());
        }
    } 
    
    public void test()  {
    
        try {
            twitter.updateStatus("Testado um twitter APP v2");
        } catch (TwitterException e) {
            System.err.println("Error occurred while updating the status!");
        }
        
    }
    
    
    
    void refreshTweets(){
        int counter = 0;
        while (condition){
            if(counter == 300000){
                receivetweets();
                System.out.println("Updated Tweets");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
    
}

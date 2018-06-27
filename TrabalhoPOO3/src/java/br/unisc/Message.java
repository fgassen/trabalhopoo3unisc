/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
/**
 *
 * @author root
 */
public class Message {
    
    Date lastreply;
    Twitter twitter;
    List<Status> tweets;
    int current;
    boolean condition;
    
    public Message(){
        twitter = new Login().getTwitter();
        current = 0;
        condition = true;
        lastreply  = new Date(2018, 06, 26, 20, 47);
    }
      
    void receivetweets(){
        
        try {
           Query query = new Query("@Poo3Trabalho");
           QueryResult result = twitter.search(query);
           tweets = result.getTweets();
           boolean out = true;
           int counter = 0; 
           Date auxdate = lastreply;
           do {
                out = replyTo(tweets.get(counter));
                
                if(auxdate.before(tweets.get(counter).getCreatedAt())){
                    auxdate = tweets.get(counter).getCreatedAt();
                }
                
           } while (tweets.size() > counter && out );

            /*
            for (Status tweet : tweets) {
               System.out.println("User:"+tweet.getUser().getId()+" "+tweet.getText()+" Reply : "+tweet.getCreatedAt());
            }
             */
            
            lastreply = auxdate;
           
        }catch(TwitterException te){
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
    } 
    
    public boolean replyTo(Status tweet) {
        Status reply = null;
        Date aux = lastreply;
        if(lastreply.before(tweet.getCreatedAt())){
            try {
                System.out.println(tweet.getText());
                String[] out = tweet.getText().split("@Poo3Trabalho");
                //System.out.println(out[1]);
                twitter.updateStatus(new StatusUpdate("@" + tweet.getUser().getScreenName() + reply(out[1]) ).inReplyToStatusId(tweet.getId()));
                //System.out.println("Posted reply " + reply.getId() + " in response to tweet " + reply.getInReplyToStatusId());
                return true;
            } catch (TwitterException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }     
        }
        return false;
    }
    
    boolean isReply(Status tweet){
        return tweet.getCreatedAt().after(lastreply);
    }
    
    
    public String reply(String question){
        DAO dao = new DAO();
        List<String> list = dao.getListAnwser(question);
        if(list == null){
            return "Por favor detalhe mais informação solictada";
        }else{
            return list.get(0);
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
            receivetweets();
            System.out.println("Updated Tweets");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
    
}

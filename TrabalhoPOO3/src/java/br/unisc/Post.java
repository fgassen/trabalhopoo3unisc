/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc;

import twitter4j.DirectMessage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.api.DirectMessagesResources;
import twitter4j.conf.ConfigurationBuilder;
/**
 *
 * @author root
 */
public class Post {
    
    Twitter twitter;
     
    public Post() throws TwitterException {     
        twitter = new Login().getTwitter();
    }
      
    void sendMessage() throws TwitterException{ 
        
        //message = ((DirectMessagesResources) twitter).sendDirectMessage(string, string1)
        //Status status = twitter.updateStatus(latestStatus);
        twitter.sendDirectMessage("1001591854650019841", "sasas" );
  
        
    }
}

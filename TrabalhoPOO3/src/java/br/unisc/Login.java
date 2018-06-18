/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author root
 */
public class Login {
    
    protected Twitter twitter;
    //protected TwitterStream twitterStream;
    private ConfigurationBuilder configBuilder;

    public Login() {
        
        configBuilder = new ConfigurationBuilder();
        configBuilder.setDebugEnabled(true);        
        configBuilder.setOAuthConsumerKey("eSmA9DXpTpVjkIUoKvVGYLawW");
        configBuilder.setOAuthConsumerSecret("XMzcixGlXNWE2RsXKBCvZQ3G9rj3Jn9OGOrlFEEs6RwOXFcKaD");
        configBuilder.setOAuthAccessToken("1001591854650019841-SxiA4N8z7QjdEI6RoDCTjY7jwghaU3");
        configBuilder.setOAuthAccessTokenSecret("spQcYUCzStBzuQXOkS4nhaL0ODYj964jEe1t1pS233Vxb");

        //use the ConfigBuilder.build() method and pass the result to the TwitterFactory
        TwitterFactory tf = new TwitterFactory(configBuilder.build());
        //you can now get authenticated instance of Twitter object.
        twitter = tf.getInstance();
        
              
        
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    public ConfigurationBuilder getConfigBuilder() {
        return configBuilder;
    }

    public void setConfigBuilder(ConfigurationBuilder configBuilder) {
        this.configBuilder = configBuilder;
    }
    
     
    
}

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
        configBuilder.setOAuthConsumerKey("BF9DBxZeT3EqUQKS4SzwDSXeF");
        configBuilder.setOAuthConsumerSecret("3jw6k1AvkLz0Qk7zLnUmMf3JGTmOjWfPgQmnnpxWIctMw5YEsT");
        configBuilder.setOAuthAccessToken("1001591854650019841-l6xPmV9s3xACCUaIaHpYPFGuA9Nc93");
        configBuilder.setOAuthAccessTokenSecret("eAJTuzDnWl9jkonQeATsJbLBt7PgXS3nksA5wMcG4D4Bw");

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

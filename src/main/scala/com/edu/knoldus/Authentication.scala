package com.edu.knoldus

import twitter4j.conf.ConfigurationBuilder
import twitter4j.{Twitter, TwitterFactory}

class Authentication {

  /**
    *
    * @param ConsumerKey       set and passed from test file(Tweet4sTest)
    * @param ConsumerSecret    set and passed from test file(Tweet4sTest)
    * @param AccessToken       set and passed from test file(Tweet4sTest)
    * @param AccessTokenSecret set and passed from test file(Tweet4sTest)
    * @return Twitter connection
    */
  def connectToTwitter(ConsumerKey: String, ConsumerSecret: String, AccessToken: String, AccessTokenSecret: String): Twitter = {

    val myTweetConfiguration = new ConfigurationBuilder
    myTweetConfiguration.setOAuthConsumerKey(ConsumerKey)
    myTweetConfiguration.setOAuthConsumerSecret(ConsumerSecret)
    myTweetConfiguration.setOAuthAccessToken(AccessToken)
    myTweetConfiguration.setOAuthAccessTokenSecret(AccessTokenSecret)
    new TwitterFactory(myTweetConfiguration.build).getInstance
  }

}

package com.edu.knoldus

/**
  *
  * hold data coming from twitter
  */
case class TweetContainer(tweet: String,
                          screenName: String,
                          likes: Int,
                          reTweets: Int
                         )

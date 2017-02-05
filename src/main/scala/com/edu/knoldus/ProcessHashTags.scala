package com.edu.knoldus

import java.util.logging.Logger
import twitter4j.Query
import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ProcessHashTags(ConsumerKey: String, ConsumerSecret: String, AccessToken: String, AccessTokenSecret: String) {
  /**
    * @param hashTag takes #tag to search as a query
    * @return list of tweets wrapped into future
    */
  def fetchTweetsByHashTag(hashTag: String): Future[List[TweetContainer]] = Future {
    val logger = Logger.getLogger(getClass.getName)
    val numberOfTweetFetch = 100
    val Twitter = (new Authentication).connectToTwitter(ConsumerKey, ConsumerSecret, AccessToken, AccessTokenSecret)
    val hashTagToSearch = new Query(hashTag)
    hashTagToSearch.setCount(numberOfTweetFetch)
    try {
      val response = Twitter.search(hashTagToSearch)
      val listEncapsulatedResponse = response.getTweets.asScala.toList
      val fetchResults = listEncapsulatedResponse map (data =>
        TweetContainer(data.getText, data.getUser.getScreenName, data.getFavoriteCount, data.getRetweetCount))
      logger.info("AVERAGE RETWEETS PER TWEET ARE : " + getAverageReTweets(fetchResults))
      logger.info("AVERAGE LIKES    PER TWEET ARE : " + getAverageLikes(fetchResults))
      fetchResults
    }
    catch {
      case ex: Exception => List(TweetContainer("", "", -1, -1))
    }

  }

  /**
    *
    * @param allTweets takes list of tweets and perform avg operation on its retweet field
    * @return average converted to string to logger to print
    */
  def getAverageReTweets(allTweets: List[TweetContainer]): String = {

    val listOfReTweets = allTweets.map(_.reTweets)
    val totalReTweets = listOfReTweets.sum
    val averageReTweets = totalReTweets / allTweets.size
    averageReTweets.toString

  }

  /**
    * @param allTweets takes list of tweets and perform avg operation on its like field
    * @return average converted to string to logger to print
    */
  def getAverageLikes(allTweets: List[TweetContainer]): String = {

    val listOfLikes = allTweets.map(_.likes)
    val totalLikes = listOfLikes.sum
    val averageReTweets = totalLikes / allTweets.size
    averageReTweets.toString

  }


}

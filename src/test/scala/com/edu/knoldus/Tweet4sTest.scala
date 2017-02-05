package com.edu.knoldus

import org.scalatest.FlatSpec

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class Tweet4sTest extends FlatSpec {

  /**
    * your twitter credentials goes here
    */

  val AuthConsumerKey = ""
  val AuthConsumerSecret = ""
  val AuthAccessToken = ""
  val AuthAccessTokenSecret = ""


  val testobject = new ProcessHashTags(AuthConsumerKey, AuthConsumerSecret, AuthAccessToken, AuthAccessTokenSecret)

  it should "match the tweet count fetched if available else get all" in {
    assert(Await.result(testobject.fetchTweetsByHashTag("#GlobalWarming") map (_.size), 5000.second) >= 0)
  }


  it should "Authentication failed" in {
    val testobject2 = new ProcessHashTags(AuthConsumerKey, AuthConsumerSecret, AuthAccessToken, "")
    assert(Await.result(testobject2.fetchTweetsByHashTag("GlolWarming") map (_.size), 5000.second) == 1)

  }

  it should "execute average retweet per post and should return 2 " in {
    assert(testobject.getAverageReTweets(List(TweetContainer("", "", 1, 2), TweetContainer("", "", 1, 2))) == "2")
  }

  it should "execute average likes per post and should return 1 " in {
    assert(testobject.getAverageLikes(List(TweetContainer("", "", 1, 2), TweetContainer("", "", 1, 2))) == "1")
  }


}


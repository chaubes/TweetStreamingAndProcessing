# Sample Scala App to Stream and Process tweets

[![Build Status](https://api.travis-ci.org/chaubes/TweetStreamingAndProcessing.svg?branch=master)](https://api.travis-ci.org/chaubes/TweetStreamingAndProcessing.svg?branch=master)
<br/>  Technologies used: scala, akka and akka-http 

**Purpose of this application:**
  The basic function of this scala application is to capture the streaming tweets filtered on the basis of provided input text. (_For Example :"iphone"_)
  And also the processing of the captured tweets happens _asynchronously_ without pausing the stream. 

  The tweet streaming and capturing is implemented using akka-http while the _asynchronous_ tweet processing is implemented using akka actors.

In order to start streaming and processing the tweets, follow the below steps:

1) Update the application.conf file with your actual property values for the below props placeholders inorder to enable 
   twitter streaming on your machine:-
   <br/><i>
    twitterAppConsumerKey = "\<twitterAppConsumerKey\>"<br/>
    twitterAppConsumerSecret = "\<twitterAppConsumerSecret\>"<br/>
    twitterAppAccessToken = "\<twitterAppAccessToken\>"<br/>
    twitterAppAccessTokenSecret = "\<twitterAppAccessTokenSecret\>"<br/></i><br/>
    These access token values are required to authorize the OAuth requests to access twitter streaming api w.r.t. your twitter account<br/>
    
    Refer to the link for details about connecting to twitter streaming https://dev.twitter.com/streaming/overview/connecting
    
2) In the TwitterStreamer object, replace the text "<Text to Filter Streaming tweets>"  with the appropriate text related 
   to which you want the tweets to be streamed and processed.
 
# Application extension   
1) This application can be extended to support any streaming api which requires OAuth authorization for the requests.
   The files in the package example.tweet.stream.process.common can be used as base files for any application dealing with akka-http
   and akka.

2) In order to provide your own custom implementation for capturing streaming tweets and processing:-
   
   a) Provide your custom TwitterStreamingService implementation which initializes the access token properties for Twitter streaming api.
      Also, It should contain the definition of a callback method like tweetStreamProcessingFunction for performing actions 
      on the captured streaming tweets.
      
   b) Provide your custom implementation of akka actors TweetConsumer and TweetProcessor in order to process the captured 
      tweets asynchronously.
      
   c) Update the case class Tweet and TweetAnalysisResponse if you require more custom fields in these domain objects.
      
   d) Provide your own custom initializer class similar to the TwitterStreamingInitializer.   
    
    
    

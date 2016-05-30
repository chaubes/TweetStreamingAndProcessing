# TweetStreamingAndProcessing
Sample scala app to stream twitter tweets and process them using akka and akka-http.<br/>
The basic function of this scala application is to capture the streaming tweets filtered on the basis of provided
input text. At the same time it involves asynchronous processing of the captured tweet text without pausing the tweet streaming action.
The tweet streaming and capture is implemented using akka-http while the asynchronous tweet processing is implemented
using akka actors.

In order to start streaming and processing the tweets, follow the below steps:

1) Update the application.conf file with your actual property values for the below props placeholders inorder to enable 
   twitter streaming on your machine:-
   <br/><br/><i>
    twitterAppConsumerKey = "\<twitterAppConsumerKey\>"<br/>
    twitterAppConsumerSecret = "\<twitterAppConsumerSecret\>"<br/>
    twitterAppAccessToken = "\<twitterAppAccessToken\>"<br/>
    twitterAppAccessTokenSecret = "\<twitterAppAccessTokenSecret\>"<br/></i><br/>
    These access token values are required to authorize the OAuth requests to access twitter streaming api w.r.t. your twitter account<br/>
    
    Refer to the link for details about connecting to twitter streaming https://dev.twitter.com/streaming/overview/connecting
    
2) In the TwitterStreamer object, replace the text "<Text to Filter Streaming tweets>"  with the appropriate text related 
   to which you want the tweets to be streamed and processed.
   
In order to extend this sample app to provide your own implementations for tweet capture and processing, you just
need to write your custom implementations of TwitterStreamingService and akka actors like TweetConsumer as well as TweetProcessor.
    
    
    

import tweepy
import keys
import datetime, time
import sys

auth = tweepy.OAuthHandler(keys.TWITTER_APP_KEY, keys.TWITTER_APP_SECRET)
auth.set_access_token(keys.TWITTER_KEY, keys.TWITTER_SECRET)
api = tweepy.API(auth)


startDate = datetime.datetime(2019, 1, 1, 0, 0, 0)
endDate =   datetime.datetime(2019, 12, 31, 23, 59, 59)

def getTweets(api, username):
    print(startDate)
    print(endDate)
    tweets = []
    tmpTweets = api.user_timeline(username)
    for tweet in tmpTweets:
        print(tweet.created_at)
        if tweet.created_at < endDate and tweet.created_at > startDate:
            print('TWEET FOUND')
            tweets.append(tweet)

getTweets(api, 'GovNedLamont')
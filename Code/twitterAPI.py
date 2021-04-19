import tweepy
import keys
import datetime, time
import sys
import os
import re
import sys

    
auth = tweepy.OAuthHandler(keys.TWITTER_APP_KEY, keys.TWITTER_APP_SECRET)
auth.set_access_token(keys.TWITTER_KEY, keys.TWITTER_SECRET)
api = tweepy.API(auth)


startDate = datetime.datetime(2021, 4, 1, 0, 0, 0)
endDate =   datetime.datetime(2021, 4, 18, 23, 59, 59)

def remove_emoji(string):
    emoji_pattern = re.compile("["
                           u"\U0001F600-\U0001F64F"  # emoticons
                           u"\U0001F300-\U0001F5FF"  # symbols & pictographs
                           u"\U0001F680-\U0001F6FF"  # transport & map symbols
                           u"\U0001F1E0-\U0001F1FF"  # flags (iOS)
                           u"\U00002702-\U000027B0"
                           u"\U000024C2-\U0001F251"
                           u"\U0000202F-\U0000202F"
                           u"\U0001f933-\U0001f933F"
                           u"\U00002066-\U00002066"
                           u"\U00002069-\U00002069"
                           u"\U0000200D-\U0000200D"
                           "]+", flags=re.UNICODE)
    return emoji_pattern.sub(r'', string)

def getTweets(api, username):
    print(startDate)
    print(endDate)
    tmpTweets = api.user_timeline(username, tweet_mode="extended")
    counter = 1
    for tweet in tmpTweets:
        if tweet.created_at < endDate and tweet.created_at > startDate:
            filename = "TweetData/training/Colorado/" + str(counter) + ".txt"
            os.makedirs(os.path.dirname(filename), exist_ok=True)
            if tweet.full_text.startswith("RT @") == True:
                newTweet = remove_emoji(tweet.retweeted_status.full_text)
            else:
                newTweet = remove_emoji(tweet.full_text)
            newTweet = re.sub(r"http\S+", "", newTweet)
            if(len(newTweet) > 150):
                counter = counter + 1
                with open(filename, "w") as f:
                    f.write(newTweet)
            print(tweet.created_at)

getTweets(api, 'GovofCO')

"""API ACCESS KEYS"""

access_token = "810544285976137728-RV5wKNFA4sD7UJbwxbQr9gfT89Cydfk"             
access_token_secret =  "H6244xXkp9dBsYimdmNLD5OamRh9FDWoGbHvLI8tuv8gH"
consumer_key =  "d2tn57rkrRGuUF4p5ond0Gp89"
consumer_secret =  "J4V4vwXfCHVGKXxDqD072MHU0DPDHYCkzTT2mppqyyEL3Erz8z"

import tweepy

# Subclass Stream to print IDs of Tweets received
class IDPrinter(tweepy.Stream):

    def on_status(self, status):
        print(status.id)

# Initialize instance of the subclass
printer = IDPrinter(
  consumer_key, consumer_secret,
  access_token, access_token_secret
)

# Filter realtime Tweets by keyword
printer.filter(track=["Twitter"])
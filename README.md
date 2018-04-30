# product-feed
product-feed

There is two module in in product feed(Seller and Customer).
Seller can publish feeds about a product and the rank of the feed. Customer can see the feed of those seller whom he/she subscribed to sorted by recent or rank.

Seller can do following things.
  > Create seller
  > Delete Seller
  > Publish Feed.
  > Delete the Feed.
  
Customer can do follwing things.
  > Create a customer
  > Delete the Customer
  > Subscribed to Seller
  > Un-subscribe to Selle
  > Fetch the Feed
 
 
So basically customer has following attribute
  > id
  > name
and Seller has following attribute
  > id
  > name
and Feed posted by seller has following attribute
  > id
  > sellerId
  > value(Feed description)
  > rank             ==> can be used to sort the feed by default
  > createdAt        ==> can used to sort the feed in case customer need on the fly
 
 NOTE==> This is in-memory solution. 

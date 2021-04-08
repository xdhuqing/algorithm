package com.hq.learnning.leetcode.twitter;

import java.util.List;

public class TestTweeter {

	public static void main(String[] args) {
		Twitter twitter = new Twitter();
		twitter.registerUser(1);
		twitter.registerUser(2);
		//----------------------------

		twitter.postTweet(1, "my first post");
		// ⽤户 1 发送了⼀条新推⽂ 5
		List<Twitter.Tweet> tweets = twitter.getNewsFeed(1);
		for(int index = 0; index < tweets.size(); index++){
			System.out.println(tweets.get(index).getContent());
		}
		System.out.println("------------------");
		// return [5]，因为⾃⼰是关注⾃⼰的
		twitter.follow(1, 2);
		// ⽤户 1 关注了⽤户 2
		twitter.postTweet(2, "my second post");
		// ⽤户2发送了⼀个新推⽂ (id = 6)
		tweets = twitter.getNewsFeed(1);
		for(int index = 0; index < tweets.size(); index++){
			System.out.println(tweets.get(index).getContent());
		}
		System.out.println("------------------");
		// return [6, 5]
		// 解释：⽤户 1 关注了⾃⼰和⽤户 2，所以返回他们的最近推⽂
		// ⽽且 6 必须在 5 之前，因为 6 是最近发送的
		twitter.unfollow(1, 2);
		// ⽤户 1 取消关注了⽤户 2
		tweets = twitter.getNewsFeed(1);
		// return [5]
		for(int index = 0; index < tweets.size(); index++){
			System.out.println(tweets.get(index).getContent());
		}
		System.out.println("------------------");

	}

}

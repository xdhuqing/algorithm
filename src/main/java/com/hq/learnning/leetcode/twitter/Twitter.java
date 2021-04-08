package com.hq.learnning.leetcode.twitter;

import org.springframework.util.Assert;

import java.util.*;

public class Twitter {

	private static Map<Integer, User> registedUsers;



	public Twitter() {
		registedUsers = new HashMap<>();
	}

	//---------------------------

	/* 还有那⼏个 API ⽅法 */
	public void postTweet(int userId, String content) {
		User user = this.findUserById(userId);
		Assert.notNull(user, "user does not exists!");
		user.addTweet(content);
	}


	public List<Tweet> getNewsFeed(int userId) {
		List<Tweet> res = new ArrayList<>();
		if (!registedUsers.containsKey(userId)) return res;
        // 关注列表的⽤户 Id
		Set<Integer> users = registedUsers.get(userId).getFollowedUsers();
        // *********⾃动通过 time 属性从⼤到⼩排序，容量为 users 的⼤⼩**********
		PriorityQueue<Tweet> pq =
				new PriorityQueue<>(users.size(), (a, b)-> (b.timestamp > a.timestamp ? 1 : -1));
        // 先将所有链表头节点插⼊优先级队列
		for (int id : users) {
			Tweet twt = registedUsers.get(id).head;
			if (twt == null) continue;
			pq.add(twt);
		}
		while (!pq.isEmpty()) {
			// 最多返回 10 条就够了
			if (res.size() == 10) break;
			// 弹出 time 值最⼤的（最近发表的）
			Tweet twt = pq.poll();
			res.add(twt);
			// 将下⼀篇 Tweet 插⼊进⾏排序
			if (twt.next != null)
				pq.add(twt.next);
		}
		return res;
	}
	public void follow(int followerId, int followeeId) {
		User user = findUserById(followerId);
		Assert.notNull(user, "user does not exists!");
		user.follow(followeeId);

	}
	public void unfollow(int followerId, int followeeId) {
		User user = findUserById(followerId);
		Assert.notNull(user, "user does not exists!");
		user.unFollow(followeeId);
	}



	public void registerUser(int userId){
		User user = findUserById(userId);
		Assert.isNull(user, "user already exists!");
		User user1 = new User();
		user1.setId(userId);
		registedUsers.put(userId, user1);
	}

	//------------------------------------------------
	private User findUserById(int userId) {
		if (registedUsers.containsKey(userId)){
			return registedUsers.get(userId);
		}
		return null;
	}





	//--------------------------------------------------

	static class Tweet {
		/**
		 * user id
		 */
		private int userId;
		/**
		 * tweet id
		 */
		private int id;
		/**
		 * tweet posted time
		 */
		private long timestamp;
		/**
		 * tweet content
		 */
		private String content;
		/**
		 * 下一篇
		 */
		private Tweet next;

		public Tweet(String content) {
			this(0, 0, System.currentTimeMillis(), content);
		}
		public Tweet(int userId, String content) {
			this(userId, 0, System.currentTimeMillis(), content);
		}

		public Tweet(int userId, int id, long timestamp, String content) {
			this.userId = userId;
			this.id = id;
			this.timestamp = timestamp;
			this.content = content;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Tweet getNext() {
			return next;
		}

		public void setNext(Tweet next) {
			this.next = next;
		}
	}
	static class User {
		/**
		 * user id
		 */
		private int id;
		/**
		 * my tweets
		 */
		private Tweet head;

		/**
		 * users followed
		 */
		private Set<Integer> followedUsers;

		public User() {
			followedUsers = new HashSet<>();
		}


		public Set<Integer> getFollowedUsers() {
			return followedUsers;
		}

		public void setFollowedUsers(Set<Integer> followedUsers) {
			this.followedUsers = followedUsers;
		}

		public int getId() {
			return id;
		}

		/**
		 * follow self
		 * @param id
		 */
		public void setId(int id) {
			this.id = id;
			this.follow(id);
		}

		public Tweet getHead() {
			return head;
		}

		public void setHead(Tweet head) {
			this.head = head;
		}

		public void setMyTweets(List<Tweet> myTweets) {
			myTweets = myTweets;
		}

		public void addTweet(String content) {
			Tweet tweet = new Tweet(content);
			tweet.next = head;
			head = tweet;
		}

		public void follow(int followeeId) {
			this.getFollowedUsers().add(followeeId);
		}
		public void unFollow(int followeeId) {
			if (this.id != followeeId){
				this.getFollowedUsers().remove(followeeId);
			}

		}
	}
}

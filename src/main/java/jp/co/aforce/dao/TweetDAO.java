package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.Tweet;

public class TweetDAO extends DAO {

	//ツイート一覧を取得するメソッド
	public List<Tweet> getAllTweets() throws Exception {
		List<Tweet> tweets = new ArrayList<>();
		Connection con = getConnection();
		String sql = "SELECT id, content, posted_at, author FROM tweets ORDER BY posted_at DESC";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String content = rs.getString("content");
			String postedAt = rs.getString("posted_at");
			String author = rs.getString("author");
			Tweet tweet = new Tweet(id, content, postedAt, author);
			tweets.add(tweet);
		}

		st.close();
		con.close();
		return tweets;
	}

	//ツイートを新規投稿するメソッド
	public boolean addTweet(String content, String author) throws Exception {
		Connection con = getConnection();
		String sql = "INSERT INTO tweets (content, author) VALUES (?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, content);
		st.setString(2, author);
		int succsess = st.executeUpdate();

		st.close();
		con.close();
		
		 return succsess > 0 ;
	}
	
	public boolean delete() throws Exception {
	    Connection con = getConnection();

	    PreparedStatement st = con.prepareStatement(
    		"DELETE FROM tweets WHERE id = ( SELECT id FROM ( SELECT id FROM tweets ORDER BY posted_at DESC LIMIT 1  ) AS temp);"
	    );
	 

	    int rowsDeleted = st.executeUpdate();

	    st.close();

	    return rowsDeleted > 0;
	}

	
	
	
}

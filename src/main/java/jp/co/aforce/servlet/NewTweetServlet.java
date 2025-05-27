package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.dao.TweetDAO;

@WebServlet("/new_tweet")
public class NewTweetServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String content = request.getParameter("content");
		String author = request.getParameter("author");
		
	
		
		TweetDAO tweetDAO = new TweetDAO();
		HttpSession session = request.getSession();
		try {
			boolean success = tweetDAO.addTweet(content, author);
			
			if(success == true) {
				 session.setAttribute("success",success );
	            	
            }else {
            	 session.setAttribute("success",success );
            }
		
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("success", false);
		}
		
		response.sendRedirect("tweet_list");
	}
}

package article.service;


public class DeleteArticleRequest {

	private String userId;
	private int articleNumber;
	
	public DeleteArticleRequest(String userId, int articleNumber) {
		this.userId = userId;
		this.articleNumber = articleNumber;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public int getArticleNumber() {
		return articleNumber;
	}
}

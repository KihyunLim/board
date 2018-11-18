package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleDao;
import article.model.Article;
import jdbc.jdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DelFlagUpdateService {

	private ArticleDao articleDao = new ArticleDao();
	
	public void delFlagUpdate(DeleteArticleRequest delFlagUpdateReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = articleDao.selectById(conn,  delFlagUpdateReq.getArticleNumber());
			if (article == null) {
				throw new ArticleNotFoundException();
			}
			if (!canModify(delFlagUpdateReq.getUserId(), article)) {
				throw new PermissionDeniedException();
			}
			articleDao.delFlagUpdate(conn, delFlagUpdateReq.getArticleNumber());
			conn.commit();
		} catch (SQLException e) {
			jdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			jdbcUtil.rollback(conn);
			throw e;
		} finally {
			jdbcUtil.close(conn);
		}
	}
	
	private boolean canModify(String delFlagUpdateUserId, Article article) {
		return article.getWriter().getId().equals(delFlagUpdateUserId);
	}
}

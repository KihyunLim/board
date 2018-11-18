package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleNotFoundException;
import article.service.DeleteArticleRequest;
import article.service.DelFlagUpdateService;
import article.service.PermissionDeniedException;
import auth.service.User;
import mvc.command.CommandHandler;

public class DelFlagUpdateHandler implements CommandHandler {

	private DelFlagUpdateService delFlagUpdateService = new DelFlagUpdateService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		DeleteArticleRequest delFlagUpdateReq = new DeleteArticleRequest(authUser.getId(), no);
		req.setAttribute("delFlagUpdateReq", delFlagUpdateReq);
		try {
			delFlagUpdateService.delFlagUpdate(delFlagUpdateReq);
			return "/WEB-INF/view/deleteSuccess.jsp";
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}

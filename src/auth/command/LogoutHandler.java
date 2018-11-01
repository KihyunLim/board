package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;

public class LogoutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(false);
/*		1. getSession(), getSession(true)
		 - HttpSession이 존재하면 현재 HttpSession을 반환하고 존재하지 않으면 새로이 세션을 생성합니다

		2. getSession(false)
		 - HttpSession이 존재하면 현재 HttpSession을 반환하고 존재하지 않으면 새로이 생성하지 않고 그냥 null을 반환합니다*/
		if( session != null ) {
			//현재 생성된 세션을 무효화 시킨다.
			session.invalidate();
		}
		res.sendRedirect(req.getContextPath() + "/index.jsp");
		return null;
	}
}

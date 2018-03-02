package filters;

import com.employee.model.EmployeeVO;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmployeeFilter implements Filter {
	
	private FilterConfig config;
	
	public void init(FilterConfig config) {
		this.config = config;
	}
	public void destroy() {
		this.config = null;
	}

	//使用者沒有登入會轉到登入畫面
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("employeeVO");
		
		if (employeeVO == null) {
			res.sendRedirect(req.getContextPath() + "/back-end/login.jsp");
			return;
		}
		chain.doFilter(request, response);
	}
}

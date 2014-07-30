package cn.xdf.cg.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action基类
 * 
 * @author qiusen
 * 
 * @date 2010-01-20
 */
public abstract class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 5376593007483604700L;

	/**
	 * HttpRequest
	 * 
	 * @return HttpRequest
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * HttpSession
	 * 
	 * @return HttpSession
	 */
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * HttpResponse
	 * 
	 * @return HttpResponse
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 获取上下文路径
	 * 
	 * @return 上下文路径
	 */
	public String getContext() {
		return this.getRequest().getContextPath();
	}

	/**
	 * 得到服务全路径，例如：http://127.0.0.1:8080/cg
	 * 
	 * @return 服务全路径
	 */
	public String getBasePath() {
		String basePath = this.getRequest().getScheme() + "://"
				+ this.getRequest().getServerName() + ":"
				+ this.getRequest().getServerPort() + this.getContext();

		return basePath;
	}
	
	/**
	 * 得到应用的绝对地址，例如：D:\Eclipse\workspace\CourseCreater\web\
	 * 
	 * @return 应用的绝对地址
	 */
	public String getRealPath() {
		return ServletActionContext.getServletContext().getRealPath("/");
	}

	/**
	 * 获得用户的真实IP
	 * 
	 * @return 用户的真实IP
	 */
	public String getRealIP() {
		String ip = getRequest().getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getRemoteAddr();
		}
		
		return ip;
	}
	
	/**
	 * 获得请求的referer
	 * 
	 * @return referer
	 */
	public String getReferer() {
		String referer = getRequest().getHeader("referer");
		if (referer != null && !referer.equalsIgnoreCase("")) {
			referer = referer.substring(0, referer.lastIndexOf("/")+1);
		} else {
			referer = "";
		}
		
		return referer;
	}

}

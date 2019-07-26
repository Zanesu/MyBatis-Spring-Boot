package tk.mybatis.springboot.shiro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class SystemExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception arg3) {
		// TODO Auto-generated method stub
		ModelAndView mw = new ModelAndView();
		if(arg3 instanceof UnauthorizedException){
			mw.setViewName("/unauth");
			return mw;
		}
		return null;
	}

}

package hobbydev.web;
		
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(method=RequestMethod.GET)
public class BaseController {
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path="/*", method = RequestMethod.GET)
	public ModelAndView getGenericPage(ModelAndView mv) {
		mv.setViewName("pages/generic");
		return mv;
	}
	
	@PreAuthorize("permitAll()")
	@RequestMapping(path="/favicon.ico", method = RequestMethod.GET)
	public ModelAndView getFavicon(ModelAndView mv) {
		mv.setViewName("redirect:/res/app/media/favicon.ico");
		return mv;
	}
	
	@RequestMapping(path="/docs", method = RequestMethod.GET)
	public ModelAndView getApi(ModelAndView mv) {
		mv.setViewName("swagger-ui");
		return mv;
	}
}

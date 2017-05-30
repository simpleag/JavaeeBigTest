package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.ifp.wechat.constant.ConstantWeChat;
import com.ifp.wechat.entity.menu.Button;
import com.ifp.wechat.entity.menu.Menu;
import com.ifp.wechat.service.MessageService;
import com.ifp.wechat.service.SignService;
import com.ifp.wechat.util.MessageUtil;

@Controller
@RequestMapping("wx")
public class WechatServlet {
	private static final long serialVersionUID = 1L;
	
	@RequestMapping(method = RequestMethod.GET)
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String echostr = request.getParameter("echostr");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (SignService.checkSignature(request)) {
				out.print(echostr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
			out = null;
		}
	}
	@RequestMapping(method = RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
//		String respMessage = WechatController.processWebchatRequest(request);
		PrintWriter out = null;
		try {
			out = response.getWriter();
//			out.print(respMessage);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
			out = null;
		}
	}
}



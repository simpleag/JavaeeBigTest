package servlet;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.util.ClassPath;

import dao.CommonDao;
/*
 * 通用的用户类
 * 包括登录验证和绑定操作
 */
@Controller
public class UserServlet {
	PrintWriter printWriter = null;
	ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
	
	@RequestMapping(value = "/login",method = RequestMethod.POST) // @RequestMapping 注解可以用指定的URL路径访问本控制层
	public void CheckUser(HttpServletRequest request,HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		CommonDao commonDao = (CommonDao) context.getBean("commonDao");
		System.out.println("test start");
		JSONObject j1 = new JSONObject();
		User u1 = new User();
		try {
			List list = (List) commonDao.loadObjet(username, "User","userNum");
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			if (list.size() == 0) {
				System.out.println("查无此用户");
				j1.put("url", "/no.jsp");
				j1.put("msg", "查无此用户");
			} else {
				u1 = (User) list.get(0);
				if(password.equals(u1.getUserPwd())) {
					if(u1.getUserTag().equals("student")){
						j1.put("url", "/student.html");
					}else{
						j1.put("url", "/teacher.html");
					}
					j1.put("msg", "成功登录");
				} else {
					j1.put("url","/no.jsp");
					j1.put("msg", "密码错误");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			j1.put("type", u1.getUserTag());
			j1.put("username", u1.getUserName());
			j1.put("userid",u1.getUserId());
			String json = JSON.toJSONString(j1);
			System.out.println(json);
			printWriter.print(json);
			printWriter.flush();
			printWriter.close();
		}
	}
	/*用户绑定 未测试 微信端未实现
	 * 需要username password usernum 
	 * 返回信息URL+msg
	 */
	@RequestMapping(value = "/binding",method = RequestMethod.POST) // @RequestMapping 注解可以用指定的URL路径访问本控制层
	public void BindingUser(HttpServletRequest request,HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usernum = request.getParameter("usernum");
		CommonDao commonDao = (CommonDao) context.getBean("commonDao");
		JSONObject j1 = new JSONObject();
		User u1 = new User();
		try {
			List list = (List) commonDao.loadObjet(username, "User","userName");
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			if (list.size() == 0) {
				System.out.println("查无此用户");
				j1.put("msg", "查无此用户");
			} else {
				u1 = (User) list.get(0);
				if(password.equals(u1.getUserPwd()) && usernum.equals(u1.getUserNum())) {
					j1.put("url", "/ok.html");
					j1.put("msg", "成功登录");
				} else {
					j1.put("url","/no.jsp");
					j1.put("msg", "密码或者学工号错误");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			j1.put("username", u1.getUserName());
			String json = JSON.toJSONString(j1);
			System.out.println("json");
			printWriter.print(json);
			printWriter.flush();
			printWriter.close();
		}
	}
}

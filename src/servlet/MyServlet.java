package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import hibernate.HibernateUtil;

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

import action.MyAction;
import dao.CustomersDAO;




@Controller
public class MyServlet {
	PrintWriter printWriter = null;
	ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String AddChoice(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}
	/**
     * @RequestParam注解的作用是：根据参数名从URL中取得参数值
     * @param username
     *            用户名，一定要对应着表单的name才行
     * @param password
     *            用户密码，也应该对应表单的数据项
     * @param model
     *            一个域对象，可用于存储数据值
     * @return
	 * @throws IOException 
     */
//    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
//            Model model) 
//	@RequestMapping(value = "/login",method = RequestMethod.POST) // @RequestMapping 注解可以用指定的URL路径访问本控制层
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
//    	Customers customer = new CustomersDAO().loadlike();
    	System.out.println(username+" "+password);
        if (username.equals("admin") && password.equals("admin")) {
//            model.addAttribute("username", username);
        	request.getSession().setAttribute("usernmae", username);
        	request.getSession().setAttribute("customer", "aa");
//        	request.setAttribute("usernmae", username);
//        	request.setAttribute("customer", "aa");
        	System.out.println("ok");
        	//将结果返回给html 这将导致jsp统统GG
        	response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			//放入json中 String各种失败
			JSONObject j1 = new JSONObject();
			j1.put("url", "/ok.jsp");
			String json = JSON.toJSONString(j1);
			System.out.println("json");
			printWriter.print(json);
			printWriter.flush();
			printWriter.close();
            return "ok";
        } else {
//            model.addAttribute("username", username);
        	request.getSession().setAttribute("usernmae", username);
        	request.getSession().setAttribute("customer", "aa");
//        	request.setAttribute("usernmae", username);
//        	request.setAttribute("customer", "aa");
            System.out.println("no");
            response.setCharacterEncoding("UTF-8");
            printWriter = response.getWriter();
			JSONObject j1 = new JSONObject();
			j1.put("url", "/no.jsp");
			String json = JSON.toJSONString(j1);
			System.out.println("json");
			printWriter.print(json);
			printWriter.flush();
			printWriter.close();
            return "no";
        }
    }
}

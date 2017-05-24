package servlet;

import hibernate.HibernateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customers;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import action.MyAction;
import dao.CustomersDAO;




@Controller
public class MyServlet {
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
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST) // @RequestMapping 注解可以用指定的URL路径访问本控制层
//    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
//            Model model) 
    public String login(HttpServletRequest request, HttpServletResponse response){
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
//    	Customers customer = new CustomersDAO().loadlike();
    	MyAction action = (MyAction) context.getBean("myAction");
    	CustomersDAO cdao= action.getCustomersDAO();;
    	Customers customer = cdao.loadlike();
        if (username.equals("admin") && password.equals("admin")) {
//            model.addAttribute("username", username);
        	request.setAttribute("usernmae", username);
        	request.setAttribute("customer", customer.getCustomerID());
            return "ok";
        } else {
//            model.addAttribute("username", username);
            request.setAttribute("usernmae", username);
            request.setAttribute("customer", customer.getCustomerID());
            return "no";
        }
    }
}

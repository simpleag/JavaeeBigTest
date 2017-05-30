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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import dao.CommonDao;

@Controller
public class AdminServlet {
	PrintWriter printWriter = null;
	ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
	
	@RequestMapping(value = "showuser",method = RequestMethod.POST)
	public void ShowUserList(HttpServletRequest request, HttpServletResponse response) {
		CommonDao commonDao = (CommonDao)context.getBean("commonDao");
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		User u1 = new User();
		try {
			List list = (List)commonDao.loadall("User");
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			for(int i=0;i<list.size();i++){
				u1 = (User) list.get(i);
				ja.add(u1);
			}
			System.out.println(JSON.toJSONString(ja));
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			printWriter.print(ja);
			printWriter.flush();
			printWriter.close();
		}
	}
}

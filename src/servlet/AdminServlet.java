package servlet;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Teachingclass;
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
	/*显示用户列表
	 * 样例
	 * [
	 * {"userId":"1",
	 *  "userName":"zwp",
	 *  "userNum":"101",
	 *  "userOpenId":"",
	 *  "userPwd":"1",
	 *  "userTag":"student"
	 *  },
	 *  {"userId":"2","userName":"awu","userNum":"102","userOpenId":"","userPwd":"1","userTag":"student"},{"userId":"3","userName":"mry","userNum":"10101","userOpenId":"","userPwd":"1","userTag":"teacher"},{"userId":"4","userName":"t2","userNum":"10102","userPwd":"1","userTag":"teacher"}]
	 * 
	 */
	@RequestMapping(value = "showuser",method = RequestMethod.POST)
	public void ShowUserList(HttpServletRequest request, HttpServletResponse response) {
		CommonDao commonDao = (CommonDao)context.getBean("commonDao");
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		User u1 = new User();
		Teachingclass t1 = new Teachingclass();
		try {
			List classlist = commonDao.loadObjet("3", "Teachingclass","teacherId");
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			for(int i=0;i<classlist.size();i++){
				t1 = (Teachingclass) classlist.get(i);
				ja.add(t1);
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

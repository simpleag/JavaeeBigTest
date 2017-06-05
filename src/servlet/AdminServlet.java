package servlet;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.scripts.JO;
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
import dao.TeachingclassDao;

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
	/*
	 * [
	 * {
	 * "classAdd":"教3101",
	 * "classId":1,
	 * "classNum":"1401",
	 * "classTag":"",
	 * "classTempTime":"",
	 * "classTime":"1-2",
	 * "subId":"1",
	 * "teacherId":"3"
	 * }
	 * ,{"classAdd":"教三101","classId":2,"classNum":"1402","classTime":"周二1-2","subId":"1","teacherId":"3"},{"classId":3,"classNum":"1403","subId":"1","teacherId":"4"}]
	 */
	@RequestMapping(value = "showclasslist",method = RequestMethod.POST)
	public void ShowClassList(HttpServletRequest request, HttpServletResponse response) {
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		TeachingclassDao tdao = (TeachingclassDao) context.getBean("teachingclassDao");
		String userid = request.getParameter("username");
		try {
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			printWriter.print(ja);
			printWriter.flush();
			printWriter.close();
		}
	}
	@RequestMapping(value = "updataClass",method = RequestMethod.POST)
	public void UpdataClass(HttpServletRequest request, HttpServletResponse response) {
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		String tc = request.getParameter("tcclass");
		JSONArray ja2 = new JSONArray();
		ja2.add(JSON.parse(tc));
		System.out.println(JSON.toJSONString(ja2));
		JSONArray ja3 = new JSONArray();
		//完成对字符串的处理转化成原来的jsonlist
		ja3 = ja2.getJSONArray(0);
		System.out.println(JSON.toJSONString(ja3));
		try {
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			Teachingclass tctemp = (Teachingclass) ja3.get(0);
			CommonDao commonDao = (CommonDao) context.getBean("commonDao");
			commonDao.updata(tctemp);
			j1.put("msg", "ok");
			ja.add(j1);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			printWriter.print(ja);
			printWriter.flush();
			printWriter.close();
		}
	}
}

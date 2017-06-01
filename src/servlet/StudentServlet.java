package servlet;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Elective;
import model.Information;

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
public class StudentServlet {
	PrintWriter printWriter = null;
	ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
	CommonDao commonDao = (CommonDao) context.getBean("commonDao");
	/*
	 * 查询成绩
	 * 
	 */
	@RequestMapping(value = "showscore",method = RequestMethod.POST)
	public void ShowScore(HttpServletRequest request, HttpServletResponse response) {
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		String userid = request.getParameter("userid");
		List scorelist;
		try {
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			scorelist = commonDao.loadObjet(userid, "Elective","userId");
			for(int i=0;i<scorelist.size();i++){
				Elective e = new Elective();
				e = (Elective) scorelist.get(i);
				System.out.println(e.getScore().toString()+" "+e.getScore());
			}
			j1.put("msg", "ok");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			j1.put("msg", "no");
			e1.printStackTrace();
		} finally {
			printWriter.print(j1);
			printWriter.flush();
			printWriter.close();
		}
	}
	/*
	 * 学生查看历史信息
	 * 需要学生的userid
	 */
	@RequestMapping(value = "studentshowreceiveinfo",method = RequestMethod.POST)
	public void ShowReceiveMsgs(HttpServletRequest request, HttpServletResponse response) {
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		String userid = request.getParameter("userid");
		CommonDao commonDao = (CommonDao)context.getBean("commonDao");
		try {
			List infolist = commonDao.loadObjet(userid,"Information", "fromUserId");
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			Information info = new Information();
			for(int i=0;i<infolist.size();i++){
				info = (Information) infolist.get(i);
				ja.add(info);
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

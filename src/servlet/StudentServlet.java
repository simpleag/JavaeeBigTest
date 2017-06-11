package servlet;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Elective;
import model.Information;
import model.Subject;
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
import dao.InfoDao;
import dao.TeacherDao;
import dao.TeachingclassDao;

@Controller
public class StudentServlet {
	PrintWriter printWriter = null;
	ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
	CommonDao commonDao = (CommonDao) context.getBean("commonDao");
	/*
	 * 查询成绩
	 * 输出如下
	 * [
	 * {
	 * "classId":"1",
	 * "electiveId":1,
	 * "electiveTag":"",
	 * "score":"1",
	 * "userId":"1"
	 * },{"classId":"2","electiveId":2,"electiveTag":"","score":"20","userId":"1"},{"classId":"3","electiveId":3,"score":"3","userId":"1"}]
	 *需要获取学生userid
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
				j1 = new JSONObject();
				Elective e = new Elective();
				e = (Elective) scorelist.get(i);
				List cllist = commonDao.loadByPk(Integer.valueOf(e.getClassId()),"Teachingclass", "classId");
				Teachingclass tc = (Teachingclass) cllist.get(0);
				j1.put("classname", tc.getClassNum());
				List sublist = commonDao.loadByPk(Integer.valueOf(tc.getSubId()), "Subject", "subId");
				Subject sub = (Subject) sublist.get(0);
				j1.put("classid", sub.getSubName());
				j1.put("score", e.getScore());
				ja.add(j1);
				System.out.println(e.getScore().toString()+" "+e.getScore());
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			System.out.println(JSON.toJSONString(ja));
			printWriter.print(JSON.toJSONString(ja));
			printWriter.flush();
			printWriter.close();
		}
	}
	/*
	 * 学生查看历史信息
	 * 需要学生的userid
	 * 返回如下
	 * [
	 * {
	 * "classId":"1",
	 * "fromUserId":"4",
	 * "infoContent":"test101",
	 * "infoId":"402805815c5d32bd015c5d32c4a60000",
	 * "sendTime":1496212227000,
	 * "toUserId":"1"
	 * }
	 * ,{"classId":"1","fromUserId":"3","infoContent":"test","infoId":"e5c6e48a5c5813df015c5813e82f0000","sendTime":1496126313000,"toUserId":"1"}]
	 */
	@RequestMapping(value = "studentinfo",method = RequestMethod.POST)
	public void ShowReceiveMsgs(HttpServletRequest request, HttpServletResponse response) {
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		String userid = request.getParameter("userid");
		CommonDao commonDao = (CommonDao)context.getBean("commonDao");
		InfoDao infoDao = (InfoDao) context.getBean("infoDao");
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
		try {
			List infolist = infoDao.infoListByUserid(userid);
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			for(int i=0;i<infolist.size();i++){
				//解析复合对象
				Object[] object = (Object[]) infolist.get(i);
				Information info = (Information) object[0];
				User user = (User) object[1];
				Teachingclass tc = (Teachingclass) object[2];
				Subject sub = (Subject) object[3];
				j1 = new JSONObject();
				String strTime = sdf.format(info.getSendTime());
				j1.put("info", info.getInfoContent());
				j1.put("sendtime",strTime);
				j1.put("fromuser", user.getUserName());
				j1.put("classname", sub.getSubName()+tc.getClassNum());
				ja.add(j1);
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
	 * 现实学生班级
	 * 需要获取学生的userid
	 * 输出如下
	 * [
	 * {
	 * "classAdd":"教5-106",
	 * "classId":1,
	 * "classNum":"1401",
	 * "classTag":"",
	 * "classTempTime":"",
	 * "classTime":"1-2",
	 * "subId":"1",
	 * "teacherId":"3"
	 * }
	 * ]
	 */
	@RequestMapping(value = "studentclass",method = RequestMethod.POST)
	public void ShowClass(HttpServletRequest request, HttpServletResponse response) {
		JSONArray ja1 = new JSONArray();
		JSONObject j1 = new JSONObject();
		TeachingclassDao tdao = (TeachingclassDao) context.getBean("teachingclassDao");
		String userid = request.getParameter("userid");
		try {
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			List classlist = tdao.loadClassOfStudent(userid) ;
			JSONObject jo1 = new JSONObject();
			JSONArray ja2 = new JSONArray();
			for(int i=0;i<classlist.size();i++){
				Object[] objects = (Object[]) classlist.get(i);
				Teachingclass tc = (Teachingclass) objects[0];
				User user = (User) objects[1];
				Subject subject = (Subject) objects[2];
				jo1 = new JSONObject();
				jo1.put("classnum", tc.getClassNum());
				jo1.put("classadd", tc.getClassAdd());
				jo1.put("classtime", tc.getClassTime());
				jo1.put("subname", subject.getSubName());
				jo1.put("teachername",user.getUserName());
				System.out.println(tc.getClassNum()+" "+tc.getClassAdd());
				ja1.add(jo1);
//				tc = (Teachingclass) classlist.get(i);
//				jo1 = new JSONObject();
//				jo1.put("classnum", tc.getClassNum());
//				jo1.put("classadd", tc.getClassAdd());
//				ja2.add(jo1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			System.out.println(JSON.toJSONString(ja1));
			printWriter.print(ja1);
			printWriter.flush();
			printWriter.close();
		}
	}
	/*显示学生的老师
	 * 输出如下
	 * [
	 * {
	 * "userId":3,
	 * "userName":"mry",
	 * "userNum":"10101",
	 * "userOpenId":"",
	 * "userPwd":"1",
	 * "userTag":"teacher"
	 * }
	 * ,{"userId":4,"userName":"t2","userNum":"10102","userPwd":"1","userTag":"teacher"}]
	 * 需要获取学生的userid
	 */
	@RequestMapping(value = "studentteachers",method = RequestMethod.POST)
	public void ShowTeachers(HttpServletRequest request, HttpServletResponse response) {
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		TeachingclassDao tdao = (TeachingclassDao) context.getBean("teachingclassDao");
		String userid = request.getParameter("userid");
		try {
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			TeacherDao teacherDao = (TeacherDao) context.getBean("teacherDao");
			List teacherList = teacherDao.loadClassOfStudent(userid);
			User user = new User();
			for(int i=0;i<teacherList.size();i++){
				user = (User) teacherList.get(i);
				j1 = new JSONObject();
				j1.put("teachername",user.getUserName());
				j1.put("phone", user.getPhonenumber());
				ja.add(j1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			printWriter.print(ja);
			printWriter.flush();
			printWriter.close();
		}
	}
//	@RequestMapping(value = "",method = RequestMethod.POST)
//	public void yangli(HttpServletRequest request, HttpServletResponse response) {
//		JSONArray ja = new JSONArray();
//		JSONObject j1 = new JSONObject();
//		TeachingclassDao tdao = (TeachingclassDao) context.getBean("teachingclassDao");
//		String userid = request.getParameter("username");
//		try {
//			response.setCharacterEncoding("UTF-8");
//			printWriter = response.getWriter();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			printWriter.print(ja);
//			printWriter.flush();
//			printWriter.close();
//		}
//	}
	
	
}

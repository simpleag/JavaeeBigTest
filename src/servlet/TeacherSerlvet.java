package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import dao.StudentDao;

@Controller
public class TeacherSerlvet {
	PrintWriter printWriter = null;
	ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
	/* 获取教师的课程序列 
	 * 需要网页发送教师id 返回课程列表
	 * 返回样例
	 * [
	 * 	{"classAdd":"教3101",
	 * 	 "classId":"1",
	 *   "classNum":"1401",
	 *   "classTag":"",
	 *   "classTempTime":"",
	 *   "classTime":"1-2",
	 *   "subId":"1",
	 *   "teacherId":"3"
	 *   },
	 *   {"classAdd":"教三101","classId":"2","classNum":"1402","classTime":"周二1-2","subId":"1","teacherId":"3"}]
	 */
	@RequestMapping(value = "teacherclass",method = RequestMethod.POST)
	public void ShowClassList(HttpServletRequest request, HttpServletResponse response) {
		CommonDao commonDao = (CommonDao)context.getBean("commonDao");
		StudentDao studentdao = (StudentDao) context.getBean("studentDao");
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		String teacherId = request.getParameter("teacherId");
		Teachingclass t1 = new Teachingclass();
		try {
			List classlist = commonDao.loadObjet(teacherId, "Teachingclass","teacherId");
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			for(int i=0;i<classlist.size();i++){
				t1 = (Teachingclass) classlist.get(i);
				String subid = t1.getSubId();
				List sublist = commonDao.loadObjet(subid, "Subject", "subId");
				Subject subject = new Subject();
				subject = (Subject) sublist.get(0);
				JSONObject jo2;
				JSONArray ja2 = new JSONArray();
				j1 = new JSONObject();
				j1.put("classnum",t1.getClassNum());
				j1.put("classid", t1.getClassId());
				j1.put("classadd",t1.getClassAdd());
				j1.put("subname", subject.getSubName());
				j1.put("classtime", t1.getClassTime());
				ja.add(j1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			System.out.println(JSON.toJSONString(ja));
			printWriter.print(ja);
			printWriter.flush();
			printWriter.close();
		}
	}
	/*根据教师id查找学生
	 *
	 */
	@RequestMapping(value = "teacherstudent",method = RequestMethod.POST)
	public void ShowStudentList(HttpServletRequest request, HttpServletResponse response) {
		StudentDao studentdao = (StudentDao) context.getBean("studentDao");
		String userid = request.getParameter("userid");
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		try {
			List userlist = studentdao.loadStudentOfTeacherId(userid);
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			for(int i=0;i<userlist.size();i++){
				Object[] object = (Object[]) userlist.get(i);
				User u1 = (User) object[0];
				Teachingclass tc = (Teachingclass) object[1];
				Subject sub = (Subject) object[2];
				j1 = new JSONObject();
				j1.put("username", u1.getUserName());
				j1.put("userid",u1.getUserId());
				j1.put("classname",sub.getSubName()+tc.getClassNum());
				j1.put("classid", tc.getClassId());
				ja.add(j1);
			}
			System.out.println(JSON.toJSONString(ja));

		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(userid);
			e.printStackTrace();
		} finally {
			System.out.print(JSON.toJSONString(ja));
			printWriter.print(ja);
			printWriter.flush();
			printWriter.close();
		}
	}
	/*查询教师发送的历史消息 需要教师的userid
	 * 返回如下
	 * [{
	 * "classId":"1",
	 * "fromUserId":"3",
	 * "infoContent":"test",
	 * "infoId":"e5c6e48a5c5813df015c5813e82f0000",
	 * "sendTime":1496126313000,
	 * "toUserId":"1"}]
	 */
	@RequestMapping(value = "teachershowsendinfo",method = RequestMethod.POST)
	public void ShowSendList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		String userid = request.getParameter("userid");
		CommonDao commonDao = (CommonDao)context.getBean("commonDao");
		InfoDao infoDao = (InfoDao) context.getBean("infoDao");
		response.setCharacterEncoding("UTF-8");
		printWriter = response.getWriter();
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
		try {
			List infolist = infoDao.infoListByFromUserid(userid);
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			for(int i=0;i<infolist.size();i++){
				Object[] object = (Object[]) infolist.get(i);
				Information info = (Information) object[0];
				User user = (User) object[1];
				Teachingclass tc = (Teachingclass) object[2];
				Subject sub = (Subject) object[3];
				j1 = new JSONObject();
				String strTime = sdf.format(info.getSendTime());
				j1.put("username", user.getUserName());
				j1.put("classname", sub.getSubName()+tc.getClassNum());
				j1.put("context", info.getInfoContent());
				j1.put("sendtime", strTime);
				ja.add(j1);
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			System.out.println(JSON.toJSONString(ja));
			printWriter.print(ja);
			printWriter.flush();
			printWriter.close();
		}
	}
	/*查询教师接收的历史消息 需要教师的userid
	 * 返回如下
	 * [{
	 * "classId":"1",
	 * "fromUserId":"3",
	 * "infoContent":"test",
	 * "infoId":"e5c6e48a5c5813df015c5813e82f0000",
	 * "sendTime":1496126313000,
	 * "toUserId":"1"}]
	 */
	@RequestMapping(value = "teacherreceiveinfo",method = RequestMethod.POST)
	public void ShowReceiveList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		String userid = request.getParameter("userid");
		CommonDao commonDao = (CommonDao)context.getBean("commonDao");
		InfoDao infoDao = (InfoDao) context.getBean("infoDao");
		response.setCharacterEncoding("UTF-8");
		printWriter = response.getWriter();
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
		try {
			System.out.println("userid:"+userid);
			List infolist = infoDao.infoListByUserid(userid);
			for(int i=0;i<infolist.size();i++){
				Object[] object = (Object[]) infolist.get(i);
				Information info = (Information) object[0];
				User user = (User) object[1];
				Teachingclass tc = (Teachingclass) object[2];
				Subject sub = (Subject) object[3];
				j1 = new JSONObject();
				String strTime = sdf.format(info.getSendTime());
				j1.put("username", user.getUserName());
				j1.put("classname", sub.getSubName()+tc.getClassNum());
				j1.put("context", info.getInfoContent());
				j1.put("sendtime", strTime);
				ja.add(j1);
			}
			System.out.println(JSON.toJSONString(ja));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			printWriter.print(ja);
			printWriter.flush();
			printWriter.close();
		}
	}
	/*选择班级群发信息 待添加微信实现接口
	 * 需要获取教师userid和班级classid
	 */
	@RequestMapping(value = "teachersendinfobyclass",method = RequestMethod.POST)
	public void SendInfoByClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONArray jsonStudentList = new JSONArray();
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		CommonDao commonDao = (CommonDao)context.getBean("commonDao");
		StudentDao studentdao = (StudentDao) context.getBean("studentDao");
		String userId = request.getParameter("userid");
		String classId = request.getParameter("classid");
		String context = request.getParameter("context");
		response.setCharacterEncoding("UTF-8");
		printWriter = response.getWriter();
		try {
			List userlist = studentdao.loadStudentOfClass(Integer.valueOf(classId));
			User u1 = new User();
			for(int i=0;i<userlist.size();i++){
				u1 = (User) userlist.get(i);
				jsonStudentList.add(u1);
			}
			System.out.println(JSON.toJSONString(jsonStudentList));
			for(int i=0;i<jsonStudentList.size();i++){
				u1 = (User) jsonStudentList.get(i);
				System.out.println(u1.getUserName()+" "+u1.getUserId());
				Information info = new Information();
				info.setClassId(classId);
				info.setFromUserId(userId);
				info.setToUserId(String.valueOf(u1.getUserId()));
				info.setInfoContent(context);
				Timestamp time = new Timestamp(new Date().getTime());
				info.setSendTime(time);
				commonDao.insert(info);

			}
			j1.put("msg", "ok");

		} catch (Exception e) {
			// TODO: handle exception
			j1.put("msg", "wrong");
			e.printStackTrace();
		} finally {
			printWriter.print(j1);
			printWriter.flush();
			printWriter.close();
		}
	}
	/*根据学生发送信息
	 * 需要获取教师userid 班级classid和学生列表的json数组
	 * json数组样例 其实只要包含userId即可
	 * [
	 * {
	 * "userId":1,
	 * "userName":"zwp",
	 * "userNum":"101",
	 * "userOpenId":"",
	 * "userPwd":"1",
	 * "userTag":"student"
	 * },{"userId":6,"userName":"test2","userNum":"104","userPwd":"1"},{"userId":5,"userName":"t3","userNum":"103","userOpenId":"","userPwd":"2","userTag":"student"},{"userId":7,"userName":"test7","userNum":"105","userPwd":"1"}]
	 * 
	 */
	@RequestMapping(value = "teachersendinfobyuser",method = RequestMethod.POST)
	public void SendInfoByUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONArray jsonStudentList = new JSONArray();
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		CommonDao commonDao = (CommonDao)context.getBean("commonDao");
		StudentDao studentdao = (StudentDao) context.getBean("studentDao");
		String classId = request.getParameter("classid");
		String userId = request.getParameter("userid");
		//假设接收到jsonlist的字符串
		String str = request.getParameter("context");
		String touseruid = request.getParameter("touserid");
//		System.out.println("str: "+str);
		//
//		JSONArray ja2 = new JSONArray();
//		ja2.add(JSON.parse(str));
//		System.out.println(JSON.toJSONString(ja2));
//		JSONArray ja3 = new JSONArray();
		//完成对字符串的处理转化成原来的jsonlist
//		ja3 = ja2.getJSONArray(0);
		response.setCharacterEncoding("UTF-8");
		printWriter = response.getWriter();
		try {
//			for(int i=0;i<ja3.size();i++){
//				JSONObject jo2 = ja3.getJSONObject(i);
//				String studentId = jo2.getString("userId");
				Information info = new Information();
				info.setClassId(classId);
				info.setFromUserId(userId);
				info.setToUserId(touseruid);
				info.setInfoContent(str);
				Timestamp time = new Timestamp(new Date().getTime());
				info.setSendTime(time);
				commonDao.insert(info);

//			}
			j1.put("msg", "ok");

		} catch (Exception e) {
			// TODO: handle exception
			j1.put("msg", "wrong");
		} finally {
			printWriter.print(j1);
			printWriter.flush();
			printWriter.close();
		}
	}

}

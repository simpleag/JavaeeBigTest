package servlet;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Information;
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
	@RequestMapping(value = "teachershowclass",method = RequestMethod.POST)
	public void ShowClassList(HttpServletRequest request, HttpServletResponse response) {
		CommonDao commonDao = (CommonDao)context.getBean("commonDao");
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		String teacherId = request.getParameter("teacherID");
		Teachingclass t1 = new Teachingclass();
		try {
			List classlist = commonDao.loadObjet(teacherId, "Teachingclass","teacherId");
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
	/*根据班级id查找学生
	 * [
	 * {
	 * "userId":"1",
	 * "userName":"zwp",
	 * "userNum":"101",
	 * "userOpenId":"",
	 * "userPwd":"1",
	 * "userTag":"student"}]
	 */
	@RequestMapping(value = "teachershowstudent",method = RequestMethod.POST)
	public void ShowStudentList(HttpServletRequest request, HttpServletResponse response) {
		StudentDao studentdao = (StudentDao) context.getBean("studentDao");
		String classid = request.getParameter("classId");
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		User u1 = new User();
		try {
			List userlist = studentdao.loadStudentOfClass(Integer.valueOf(classid));
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			for(int i=0;i<userlist.size();i++){
				u1 = (User) userlist.get(i);
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
	public void ShowSendList(HttpServletRequest request, HttpServletResponse response) {
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
	@RequestMapping(value = "teachershowreceiveinfo",method = RequestMethod.POST)
	public void ShowReceiveList(HttpServletRequest request, HttpServletResponse response) {
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
	/*选择班级群发信息 待添加微信实现接口
	 * 需要获取教师userid和班级classid
	 */
	@RequestMapping(value = "teachersendinfobyclass",method = RequestMethod.POST)
	public void SendInfoByClass(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonStudentList = new JSONArray();
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		CommonDao commonDao = (CommonDao)context.getBean("commonDao");
		StudentDao studentdao = (StudentDao) context.getBean("studentDao");
		String userId = request.getParameter("userid");
		String classId = request.getParameter("classid");
		try {
			List userlist = studentdao.loadStudentOfClass(Integer.valueOf("classid"));
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
				info.setInfoContent("test"+u1.getUserNum());
				Timestamp time = new Timestamp(new Date().getTime());
				info.setSendTime(time);
				commonDao.insert(info);

			}
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
	public void SendInfoByUser(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonStudentList = new JSONArray();
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		CommonDao commonDao = (CommonDao)context.getBean("commonDao");
		StudentDao studentdao = (StudentDao) context.getBean("studentDao");
		String classId = request.getParameter("classid");
		String userId = request.getParameter("userid");
		//假设接收到jsonlist的字符串
		String str = request.getParameter("data");
		System.out.println("str: "+str);
		//
		JSONArray ja2 = new JSONArray();
		ja2.add(JSON.parse(str));
		System.out.println(JSON.toJSONString(ja2));
		JSONArray ja3 = new JSONArray();
		//完成对字符串的处理转化成原来的jsonlist
		ja3 = ja2.getJSONArray(0);
		try {
			for(int i=0;i<ja3.size();i++){
				JSONObject jo2 = ja3.getJSONObject(i);
				String studentId = jo2.getString("userId");
				Information info = new Information();
				info.setClassId(classId);
				info.setFromUserId(userId);
				info.setToUserId(studentId);
				info.setInfoContent("test"+studentId);
				Timestamp time = new Timestamp(new Date().getTime());
				info.setSendTime(time);
				commonDao.insert(info);

			}
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

package test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import hibernate.HibernateUtil;
import model.Elective;
import model.Information;
import model.MyToken;
import model.Teachingclass;
import model.User;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import dao.CommonDao;
import dao.StudentDao;
import dao.TeacherDao;
import dao.TeachingclassDao;


public class Test {
	public static void main(String[] args) throws Exception {
		//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//		//begin是不可或缺的
		//		session.beginTransaction();
		//		System.out.println("test2");
		//		Customers customer = null;
		//		org.hibernate.Query qry = session.createQuery("from Customers where CustomerId LIKE '%W%'");
		//        java.util.List list = qry.list();
		//        for (int i = 0; i < 1; i++) {
		//            customer = (Customers) list.get(i);
		//            System.out.println("CusomerId:"+customer.getCustomerID()+"  CompanyName:"+customer.getCompanyName());
		//        }
		//        
		//        session.close();
		//        
		//		Date d1 = new Date();
		//		long l1 = d1.getTime();
		//		System.out.println(d1+" "+l1);
		//1495606749303
		//		String id = "1";
		//		MyToken t1 = new MyToken();
		//		t1 = (MyToken)new CommonDao().loadObjet(id,t1,t1.getClass().getName(),"tokenId");
		//		System.out.print(t1.getClass().getName()+" ");
		//		System.out.println(t1.getTokenId()+" "+t1.getTokenEndTime());
		//		t1.setAccessToken("3");
		//		t1.setTokenEndTime((long)0);
		//		t1.setTokenId("8");
		//		new CommonDao().delete(t1);
		//对studentDao进行测试
		//		User user = new User();
		//		List list = new StudentDao().loadStudentOfClass("1");
		//		user = (User) list.get(0);
		//		System.out.println(user.getUserId() +" "+user.getUserName());

		//		Information info = new Information();
		//		info.setInfoContent("test");
		//		Date date = new Date();
		//		Timestamp ts = new Timestamp(date.getTime());
		//		info.setSendTime(ts);
		//		info.setToUserId("1");
		//		info.setFromUserId("3");
		//		info.setClassId("1");
		//		new CommonDao().insert(info);
		//对老师dao进行测试
		//		User user = new User();
		//		List list = new TeacherDao().loadClassOfStudent("1");
		//		for(int i=0;i<list.size();i++){
		//			user = (User) list.get(i);
		//			System.out.println(user.getUserName());
		//		}
		//servlet中方法进行测试
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		CommonDao commonDao = (CommonDao)context.getBean("commonDao");
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		//		User u1 = new User();
		//		u1.setUserName("test7");
		//		u1.setUserOpenId(null);
		//		u1.setUserTag(null);
		//		u1.setUserNum("105");
		//		u1.setUserPwd("1");
		//		commonDao.insert(u1);
		//		JSONArray ja = new JSONArray();
		//		JSONObject j1 = new JSONObject();
		//		User u1 = new User();
		//		try {
		//			List list = (List)commonDao.loadall("User");
		//			for(int i=0;i<list.size();i++){
		//				u1 = (User) list.get(i);
		//				ja.add(u1);
		//			}
		//			System.out.println(JSON.toJSONString(ja));
		//		} catch (Exception e) {
		//			// TODO: handle exception
		//		}
		//		JSONArray ja = new JSONArray();
		//		JSONObject j1 = new JSONObject();
		//		List classlist = commonDao.loadObjet("3", "Teachingclass","teacherId");
		//		Teachingclass t1 = new Teachingclass();
		//		User u1 = new User();
		////		StudentDao sdao = (StudentDao) context.getBean("studentDao");
		//		if(classlist.size() == 0){
		//			
		//		} else {
		//			for(int i=0;i<classlist.size();i++){
		//				t1 = (Teachingclass) classlist.get(i);
		//				ja.add(t1);
		//			}
		//		}
		//		System.out.println(JSON.toJSONString(ja));

		//查询历史消息
		//		List infolist = commonDao.loadObjet("1","Information", "toUserId");
		//		Information info = new Information();
		//		for(int i=0;i<infolist.size();i++){
		//			info = (Information) infolist.get(i);
		//			ja.add(info);
		//		}
		//		System.out.println(JSON.toJSONString(ja));
		//		System.out.println(new Date().getTime());

		//查询班级对应的学生
		//		StudentDao studentdao = (StudentDao) context.getBean("studentDao");
		//		List userlist = studentdao.loadStudentOfClass("1");
		//		User u1 = new User();
		//		for(int i=0;i<userlist.size();i++){
		//			u1 = (User) userlist.get(i);
		//			ja.add(u1);
		//		}
		//		System.out.println(JSON.toJSONString(ja));

		//发送消息
//		JSONArray jsonStudentList = new JSONArray();
//		StudentDao studentdao = (StudentDao) context.getBean("studentDao");
//		List userlist = studentdao.loadStudentOfClass(Integer.valueOf("1"));
//		User u1 = new User();
//		for(int i=0;i<userlist.size();i++){
//			u1 = (User) userlist.get(i);
//			jsonStudentList.add(u1);
//		}
//		System.out.println(JSON.toJSONString(jsonStudentList));
//		//假设接收到jsonlist的字符串
//		String str = JSON.toJSONString(jsonStudentList);
//		System.out.println("str: "+str);
//		//
//		JSONArray ja2 = new JSONArray();
//		ja2.add(JSON.parse(str));
//		System.out.println(JSON.toJSONString(ja2));
//		JSONArray ja3 = new JSONArray();
//		//完成对字符串的处理转化成原来的jsonlist
//		ja3 = ja2.getJSONArray(0);
//		System.out.println(JSON.toJSONString(ja3));
//		for(int i=0;i<ja3.size();i++){
//			JSONObject jo2 = ja3.getJSONObject(i);
//			String janme = jo2.getString("userName");
//			System.out.println(ja3.size()+" "+" "+janme+" "+jo2.getString("userId"));
//		}
		//		for(int i=0;i<jsonStudentList.size();i++){
		//			u1 = (User) jsonStudentList.get(i);
		//			System.out.println(u1.getUserName()+" "+u1.getUserId());
		//			Information info = new Information();
		//			info.setClassId(String.valueOf(1));
		//			info.setFromUserId(String.valueOf(4));
		//			info.setToUserId(String.valueOf(u1.getUserId()));
		//			info.setInfoContent("test"+u1.getUserNum());
		//			Timestamp time = new Timestamp(new Date().getTime());
		//			info.setSendTime(time);
		//			commonDao.insert(info);
		//			
		//		}
		//查成绩
//		String userid = "1";
//		List scorelist = commonDao.loadObjet(userid, "Elective","userId");
//		for(int i=0;i<scorelist.size();i++){
//			Elective e = new Elective();
//			e = (Elective) scorelist.get(i);
//			System.out.println(e.getScore().toString()+" "+e.getScore());
//		}
		
		//学生查询课程
		TeachingclassDao tdao = (TeachingclassDao) context.getBean("teachingclassDao");
		String userid = "1";
		Teachingclass tc = new Teachingclass();
		List classlist = tdao.loadClassOfStudent(userid) ;
		JSONObject jo1 = new JSONObject();
		JSONArray ja1 = new JSONArray();
		JSONArray ja2 = new JSONArray();
		for(int i=0;i<classlist.size();i++){
			tc = (Teachingclass) classlist.get(i);
			ja1.add(tc);
			jo1.put("classnum", tc.getClassNum());
			jo1.put("classadd", tc.getClassAdd());
			ja2.add(jo1);
		}
		System.out.println(JSON.toJSONString(ja1));
		System.out.println(JSON.toJSONString(ja2));
	}

}

package test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import hibernate.HibernateUtil;
import model.Information;
import model.MyToken;
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
		//
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		CommonDao commonDao = (CommonDao)context.getBean("commonDao");
		JSONArray ja = new JSONArray();
		JSONObject j1 = new JSONObject();
		User u1 = new User();
		try {
			List list = (List)commonDao.loadall("User");
			for(int i=0;i<list.size();i++){
				u1 = (User) list.get(i);
				ja.add(u1);
			}
			System.out.println(JSON.toJSONString(ja));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

package test;

import java.util.Date;

import hibernate.HibernateUtil;
import model.MyToken;

import org.hibernate.Session;

import dao.CommonDao;


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
		String id = "1";
		MyToken t1 = new MyToken();
//		t1 = (MyToken)new CommonDao().loadObjet(id,t1,t1.getClass().getName(),"tokenId");
//		System.out.print(t1.getClass().getName()+" ");
//		System.out.println(t1.getTokenId()+" "+t1.getTokenEndTime());
		t1.setAccessToken("");
		t1.setTokenEndTime((long)0);
		t1.setTokenId("8");
		new CommonDao().updata(t1);;
	}
}

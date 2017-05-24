package test;

import hibernate.HibernateUtil;
import model.Customers;

import org.hibernate.Session;


public class Test {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//begin是不可或缺的
		session.beginTransaction();
		System.out.println("test2");
		Customers customer = null;
		org.hibernate.Query qry = session.createQuery("from Customers where CustomerId LIKE '%W%'");
        java.util.List list = qry.list();
        for (int i = 0; i < 1; i++) {
            customer = (Customers) list.get(i);
            System.out.println("CusomerId:"+customer.getCustomerID()+"  CompanyName:"+customer.getCompanyName());
        }
        
        session.close();
        

	}
}

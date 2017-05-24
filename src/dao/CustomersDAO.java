package dao;

import hibernate.HibernateUtil;
import model.Customers;

import org.hibernate.Session;


public class CustomersDAO {
	public Customers loadlike() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//begin是不可或缺的
		session.beginTransaction();
		System.out.println("test2");
		org.hibernate.Query qry = session.createQuery("from Customers where CustomerId LIKE '%W%'");
        java.util.List list = qry.list();
        Customers customer = null;
        for (int i = 0; i < list.size(); i++) {
            customer = (Customers) list.get(i);
            System.out.println("CusomerId:"+customer.getCustomerID()+"  CompanyName:"+customer.getCompanyName());
        }
        session.close();
        customer = (Customers) list.get(0);
//        HibernateUtil.getSessionFactory().close();
		return customer;
	}
}

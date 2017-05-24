package dao;
import java.util.List;

import hibernate.HibernateUtil;

import org.hibernate.Session;
public class CommonDao {
	/*
	 * 精确查找 并返回对象
	 * id(String类型数据即可 主键均为String类型) 表对应类型的临时类  类的名称（对应数据库中表） 列名  根据不同列查找 
	 */
	public Object loadObjet(String id,Object temp,String objname,String colum) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		//from类名
		org.hibernate.Query qry = session.createQuery("from "+objname+" where "+ colum +" = ?");
		qry.setString(0,id);
		List list = qry.list();
		temp = list.get(0);
		session.close();
		return temp;
	}
	/*
	 * 模糊查询，并将所有查询结果用List返回
	 * id(String类型数据即可 主键均为String类型) 表对应类型的临时类  类的名称（对应数据库中表） 列名  根据不同列查找 
	 */
	public Object loadFuzzy(String id,Object temp,String objname,String colum) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		//from类名
		id = "%"+id+"%";
		org.hibernate.Query qry = session.createQuery("from "+objname+" where "+ colum +" like ?");
		qry.setString(0,id);
		List list = qry.list();
		session.close();
		return list;
	}
	/*
	 * 打印表的所有数据 返回list
	 * 类名（对应数据库的表名）
	 */
	public List loadall(String objname) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		org.hibernate.Query qry = session.createQuery("from "+objname);
		List list = qry.list();
		session.close();
		return list;
	}
	/*
	 * 添加数据
	 * 插入的对象
	 */
	public void insert(Object temp) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(temp);
			//commit后不关闭
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e+"插入数据异常");
		}finally{
		}
	}
	/*
	 * 更新数据　实现软删除和修改
	 * 输入修改后的对象
	 */
	public void updata(Object temp) throws Exception{
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(temp);
			//commit后不关闭
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e+"删除数据异常");
		}finally{
		}
	}
}

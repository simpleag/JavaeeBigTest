package dao;

import hibernate.HibernateUtil;

import java.util.List;

import model.Information;
import model.Subject;
import model.Teachingclass;
import model.User;

import org.hibernate.Query;
import org.hibernate.Session;

public class InfoDao {
	/*
	 * 根据消息接收者id查询
	 * 消息、发送者user、发送class
	 * 返回复合对象链
	 */
	public List infoListByUserid(String touserid) throws Exception{
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String sql = " SELECT j2eebigtest.information.*,j2eebigtest.user.*,j2eebigtest.teachingclass.* ,j2eebigtest.subject.* "
							+" from j2eebigtest.information,j2eebigtest.user,j2eebigtest.teachingclass,j2eebigtest.subject "
							+" where j2eebigtest.information.toUserId = ? and j2eebigtest.information.fromUserId = j2eebigtest.user.userId and j2eebigtest.teachingclass.classId = j2eebigtest.information.classId and j2eebigtest.subject.subId = j2eebigtest.teachingclass.subId "
							+" order by j2eebigtest.information.sendTime DESC ";
			Query query = session.createSQLQuery(sql).addEntity("information",Information.class).addEntity("user",User.class).addEntity("teachingclass",Teachingclass.class).addEntity("subject",Subject.class);
			query.setString(0,touserid);
			List infoList = query.list();
			session.close();
			return infoList;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e);
		} finally {
			
		}
	}
	public List infoListByFromUserid(String fromuserid) throws Exception{
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String sql = " SELECT j2eebigtest.information.*,j2eebigtest.user.*,j2eebigtest.teachingclass.*,j2eebigtest.subject.* "
							+" from j2eebigtest.information,j2eebigtest.user,j2eebigtest.teachingclass,j2eebigtest.subject "
							+" where j2eebigtest.information.fromUserId = ? and j2eebigtest.information.toUserId = j2eebigtest.user.userId and j2eebigtest.teachingclass.classId = j2eebigtest.information.classId"
							+" order by j2eebigtest.information.sendTime DESC ";
			Query query = session.createSQLQuery(sql).addEntity("information",Information.class).addEntity("user",User.class).addEntity("teachingclass",Teachingclass.class).addEntity("subject",Subject.class);
			query.setString(0, fromuserid);
			List infoList = query.list();
			session.close();
			return infoList;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e);
		} finally {
			
		}
	}
}

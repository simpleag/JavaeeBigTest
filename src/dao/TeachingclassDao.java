package dao;

import hibernate.HibernateUtil;

import java.util.List;

import model.Subject;
import model.Teachingclass;
import model.User;

import org.hibernate.Session;



public class TeachingclassDao {
	/*
	 * 根据学生id查找课程
	 */
	public List loadClassOfStudent(String studentid) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String sql = " SELECT  DISTINCT j2eebigtest.teachingclass.* ,j2eebigtest.user.*,j2eebigtest.subject.*  "
		             +" FROM j2eebigtest.teachingclass,j2eebigtest.elective,j2eebigtest.user,j2eebigtest.subject "
					 +" where j2eebigtest.elective.userId = ? and j2eebigtest.elective.classId = j2eebigtest.teachingclass.classId and j2eebigtest.user.userId =  j2eebigtest.teachingclass.teacherId and j2eebigtest.subject.subId = j2eebigtest.teachingclass.subId ";
			org.hibernate.Query query = session.createSQLQuery(sql).addEntity("teachingclass",Teachingclass.class).addEntity("user",User.class).addEntity("subject",Subject.class);
			query.setString(0,studentid);
			List list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e);
		}
	}
}

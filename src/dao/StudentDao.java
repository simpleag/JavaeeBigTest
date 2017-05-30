package dao;

import hibernate.HibernateUtil;

import java.util.List;

import model.User;

import org.hibernate.Query;
import org.hibernate.Session;

public class StudentDao {
	/*
	 * 根据教师的任课班级ID 查询老师班级下的学生
	 */
	public List loadStudentOfClass(String classid) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String sql = " select j2eebigtest.user.* "
			             +" from j2eebigtest.user,j2eebigtest.elective,j2eebigtest.teachingclass "
					     +" where  j2eebigtest.teachingclass.classId = ? and j2eebigtest.elective.classId = j2eebigtest.teachingclass.classId and j2eebigtest.elective.userId = j2eebigtest.user.userId;";
			org.hibernate.Query query = session.createSQLQuery(sql).addEntity(User.class);
			query.setString(0,classid);
			List list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e);
		}
	}
}

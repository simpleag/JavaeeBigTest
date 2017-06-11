package dao;

import hibernate.HibernateUtil;

import java.util.List;

import model.Subject;
import model.Teachingclass;
import model.User;

import org.hibernate.Query;
import org.hibernate.Session;

public class StudentDao {
	/*
	 * 根据教师的任课班级ID 查询老师班级下的学生
	 */
	public List loadStudentOfClass(int classid) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String sql = " select j2eebigtest.user.* "
			             +" from j2eebigtest.user,j2eebigtest.elective,j2eebigtest.teachingclass "
					     +" where  j2eebigtest.teachingclass.classId = ? and j2eebigtest.elective.classId = j2eebigtest.teachingclass.classId and j2eebigtest.elective.userId = j2eebigtest.user.userId;";
			org.hibernate.Query query = session.createSQLQuery(sql).addEntity(User.class);
			query.setString(0,String.valueOf(classid));
			List list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e);
		}
	}
	/*
	 * 根据教师ID 查询老师班级下的学生
	 * 返回复合对象 user+teahingclass+subject
	 */
	public List loadStudentOfTeacherId(String userid) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String sql = " SELECT distinct j2eebigtest.user.*,j2eebigtest.teachingclass.*,j2eebigtest.subject.*"
			             +" FROM  j2eebigtest.user,j2eebigtest.elective,j2eebigtest.teachingclass,j2eebigtest.subject"
					     +" where j2eebigtest.teachingclass.teacherId = ? and j2eebigtest.teachingclass.classId = j2eebigtest.elective.classId and j2eebigtest.user.userId = j2eebigtest.elective.userId and j2eebigtest.subject.subId = j2eebigtest.teachingclass.subId"
			             +" order by classid ";
			org.hibernate.Query query = session.createSQLQuery(sql).addEntity("user",User.class).addEntity("teachingclass",Teachingclass.class).addEntity("subject",Subject.class);
			query.setString(0,userid);
			List list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e);
		}
	}
}

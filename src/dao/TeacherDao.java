package dao;

import java.util.List;

import model.User;

import org.hibernate.Session;

import cn.edu.zucc.hibernatetest.util.HibernateUtil;

public class TeacherDao {
	/*
	 * 根据学生id查找老师
	 */
	public List loadClassOfStudent(String studentid) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String sql = "SELECT DISTINCT j2eebigtest.user.* "
					+" FROM j2eebigtest.user,j2eebigtest.elective,j2eebigtest.teachingclass "
					+" where j2eebigtest.user.userId in ( "
					+"	select j2eebigtest.teachingclass.teacherId "
					+"  from j2eebigtest.user,j2eebigtest.elective,j2eebigtest.teachingclass "
					+"  where j2eebigtest.elective.userId = ? and j2eebigtest.elective.classId = j2eebigtest.teachingclass.classId "
					+"  )";
			org.hibernate.Query query = session.createSQLQuery(sql).addEntity(User.class);
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

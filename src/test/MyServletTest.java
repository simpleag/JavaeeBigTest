package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;

import servlet.MyServlet;

import com.opensymphony.xwork2.interceptor.annotations.Before;
/*
 * 加载配置文件
 */
@ContextConfiguration({"classpath*:/springMVC-servlet.xml","classpath*:/web.xml"})  
public class MyServletTest {
	//模拟request,response  
	private MockHttpServletRequest request ;  
	private MockHttpServletResponse response; 
	private MyServlet myServlet = new MyServlet();

	/* 
	 * 测试开始之前进行初始化 
	 */  
	//    @Before  
	//    public void setUp() throws Exception {  
	//        request = new MockHttpServletRequest();  
	//        request.setCharacterEncoding("UTF-8");  
	//        response = new MockHttpServletResponse();  
	//        System.out.print("before");
	//    }  
	@Test
	public void test() {
		request = new MockHttpServletRequest();  
		request.setCharacterEncoding("UTF-8");  
		response = new MockHttpServletResponse(); 
		request.setParameter("username", "admin");  
		request.setParameter("password", "123");  
		try {  
			//判断控制器执行后是否返回字符串"/index"用于渲染  
			assertEquals("ok", myServlet.login(request, response));  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
	}

}

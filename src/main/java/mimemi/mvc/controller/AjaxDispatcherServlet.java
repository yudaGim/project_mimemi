package mimemi.mvc.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet(urlPatterns = "/ajax", loadOnStartup = 1)
public class AjaxDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// application에서 받아올 map을 저장할 객체 생성
	Map<String, Controller> map;
	Map<String, Class<?>> clzMap;
    
	@Override
	public void init() throws ServletException {
		// 클래스가 호출되면 가장 먼저 호출되는 init 메소드에서 map, clzMap 호출해 저장
		map = (Map<String, Controller>)super.getServletContext().getAttribute("ajaxMap");
		clzMap = (Map<String, Class<?>>)super.getServletContext().getAttribute("ajaxClzMap");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request 영역에서 key값과 methodName값 가져와 저장
		String key = request.getParameter("key");
		String methodName = request.getParameter("methodName");
		
		System.out.println("key = " + key + ", methodName = " + methodName);
		
		try {
			// clzMap에서 키 값에 해당하는 밸류(클래스) 가져와 저장
			Class<?> clz = clzMap.get(key);
			
			// 위에서 저장한 클래스에서 메소드 이름으로 메소드를 가져옴(가져올 때 매개변수도 함께 입력)
			Method method = clz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			
			// 키 값에 해당하는 컨트롤러를 가져옴
			Controller controller = map.get(key);
			
			// 컨트롤러에서 메소드를 호출
			method.invoke(controller, request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

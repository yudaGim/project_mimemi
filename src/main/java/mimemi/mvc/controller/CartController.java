package mimemi.mvc.controller;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mimemi.mvc.dto.CartDTO;
import mimemi.mvc.service.CartService;
import mimemi.mvc.service.CartServiceImpl;
import net.sf.json.JSONArray;

public class CartController implements Controller {
	private CartService cartService = new CartServiceImpl();

	@Override
	public ModelAndView hendlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

	/**
	 * 구매폼에 장바구니에 담긴 상품 뿌려주기
	 * */
	public ModelAndView viewOrderForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String mode = request.getParameter("mode");
		List<CartDTO> cartList = null;

		if(mode.equals("C")) { // 장바구니 전체 구매
			String userId = "happy01";
			cartList = cartService.selectCartByUserId(userId);
		} else if(mode.equals("S")) { // 장바구니 부분 구매
			String[] cartIds = request.getParameterValues("cartSelect");
			cartList = new ArrayList<CartDTO>();
			
			for(String id : cartIds) {
				CartDTO cart = cartService.selectCartByCartId(Integer.parseInt(id));
				cartList.add(cart);
			}
		} else if(mode.equals("D")) { // 상품란에서 바로 구매
			
		}
		
		session.removeAttribute("cartList");
		session.setAttribute("cartList", cartList);
		
		ModelAndView mv = new ModelAndView("order/order.jsp?mode=" + mode, true);
		return mv;
	}

	/**
	 * 아이디로 장바구니 검색
	 * */
	public void selectCartByUserId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		
		String userId = request.getParameter("userId");
		List<CartDTO> list = cartService.selectCartByUserId(userId);
		
		JSONArray cartArr = JSONArray.fromObject(list);
		
		PrintWriter out = response.getWriter();
		out.print(cartArr);
	}
	
	/**
	 * 장바구니 수량 변경
	 * */
	public void updateCartQty(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int cartId = Integer.parseInt(request.getParameter("cartId"));
		int cartQty = Integer.parseInt(request.getParameter("cartQty"));
		
		cartService.updateCartQty(cartId, cartQty);
	}
	
	/**
	 * 장바구니 요일 변경
	 * */
	public void updateCartWeekday(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int cartId = Integer.parseInt(request.getParameter("cartId"));
		String cartWeekday = request.getParameter("cartWeekday");
		
		cartService.updateCartWeekday(cartId, cartWeekday);
	}
	
	/**
	 * 장바구니 기간 변경
	 * */
	public void updateCartPeriod(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int cartId = Integer.parseInt(request.getParameter("cartId"));
		String cartPeriod = request.getParameter("cartPeriod");
		
		cartService.updateCartPeriod(cartId, cartPeriod);
	}
	
	/**
	 * 장바구니 첫 배송일 변경
	 * */
	public void updateCartStart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int cartId = Integer.parseInt(request.getParameter("cartId"));
		String cartStart = request.getParameter("cartStart");
		
		cartService.updateCartStart(cartId, cartStart);
	}

	/**
	 * 장바구니 부분 삭제
	 */
	public void deleteSelectedCart(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String[] arr = request.getParameterValues("cartId");
		List<Integer> cartId = new ArrayList<Integer>();
		for(String str : arr) {
			cartId.add(Integer.parseInt(str));
		}
		
		cartService.deleteSelectedCart(cartId);
	}

	/**
	 * 장바구니 전체 삭제
	 */
	public void deleteAllCart(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String userId = request.getParameter("userId");
		
		cartService.deleteAllCart(userId);
	}
}

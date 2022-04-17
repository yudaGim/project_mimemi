package mimemi.mvc.service;

import java.sql.SQLException;
import java.util.List;

import mimemi.mvc.dao.OrderDAO;
import mimemi.mvc.dao.OrderDAOImpl;
import mimemi.mvc.dto.OrderDTO;

public class OrderServiceImpl implements OrderService {
	private OrderDAO orderDao = new OrderDAOImpl();

	/**
	 * �ֹ� ���
	 * @param OrderDTO(String userId, int addrId, String payMethod, int payPoint, String orderMemo,
	 * 			String takeMethod, String doorPwd, String usercouId)
	 */
	@Override
	public void insertOrder(OrderDTO order) throws SQLException {
		// TODO Auto-generated method stub
	}

	/**
	 * �ֹ� ���
	 * : �ֹ� ��ȣ�� �̿��� �ش� �ֹ� �ǿ� ������ ��� ��� �������� ��� ó����
	 * @param int orderId
	 */
	@Override
	public void deleteOrder(int orderId) throws SQLException {
		// TODO Auto-generated method stub
	}

	/**
	 * �ֹ� ��ü ��ȸ
	 * @param String field(���� ����)
	 * @return List<OrderDTO>
	 * */
	@Override
	public List<OrderDTO> selectAll(int pageNum, String field) throws SQLException {
		List<OrderDTO> orderList = orderDao.selectAll(pageNum, field);
			
		return orderList;
	}

	/**
	 * ���̵�� Ư�� �ֹ� ��ȸ
	 * @param int orderId(���� ����)
	 * @return OrderDTO
	 * */
	@Override
	public OrderDTO selectById(int orderId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
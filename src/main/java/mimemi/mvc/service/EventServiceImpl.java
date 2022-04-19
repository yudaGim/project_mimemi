package mimemi.mvc.service;

import java.sql.SQLException;
import java.util.List;

import mimemi.mvc.dao.EventDAO;
import mimemi.mvc.dao.EventDAOImpl;
import mimemi.mvc.dto.EventDTO;

public class EventServiceImpl implements EventService {
	private EventDAO eventDAO = new EventDAOImpl();
	
	/**
	 * 이벤트 게시글 등록
	 * @param: EventDTO(int eventId, String eventTitle, String eventContent, String eventAttach, String eventImg,
	 * 			String eventStartdate, String eventEnddate)
	 * */
	@Override
	public void insert(EventDTO event) throws SQLException {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 이벤트 게시글 수정
	 * @param: EventDTO(int eventId, String eventTitle, String eventContent, String eventStartdate,
	 * 			String eventEnddate)
	 * 			게시글 제목과 내용, 시작일과 종료일만 변경
	 * @return: int(수정 성공한 레코드 수)
	 * */
	@Override
	public void updateEvent(EventDTO event) throws SQLException {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 이벤트 게시글 썸네일 이미지 수정
	 * @param: int eventId, String eventImg
	 * @return: int(수정 성공한 레코드 수)
	 * */
	@Override
	public void updateEventImg(int eventId, String eventImg) throws SQLException {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 이벤트 게시글 상세 이미지 수정
	 * @param: int eventId, String eventAttach
	 * @return: int(수정 성공한 레코드 수)
	 * */
	@Override
	public void updateEventAttach(int eventId, String eventAttach) throws SQLException {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 이벤트 게시글 전체 조회
	 * @return: List<EventDTO>
	 * */
	@Override
	public List<EventDTO> selectAll() throws SQLException {
		List<EventDTO> eventList = eventDAO.selectAll();

		return eventList;
	}
	
	/**
	 * 이벤트 게시글 진행 상황별 조회
	 * sysdate를 기준으로 진행 예정/ 진행중 / 진행 완료를 나누어 조회
	 * @param: String state(진행 예정, 진행중, 진행 완료에 따라 쿼리문 변경)
	 * @return: List<EventDTO>
	 * */
	@Override
	public List<EventDTO> selectByDate(String state) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 이벤트 게시글 상세 조회
	 * 글번호 기준으로 게시글 상세 조회
	 * @param: int eventNo
	 * @return: EventDTO
	 * */
	@Override
	public EventDTO selectByEventNo(int eventNo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 이벤트 전체 검색(페이지 처리)
	 * @param int pageNo
	 * 특정 페이지를 클릭했을 때 그 페이지로 넘어간다.
	 * */
	@Override
	public List<EventDTO> getEventList(int pageNo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

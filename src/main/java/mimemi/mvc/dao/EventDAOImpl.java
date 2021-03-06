package mimemi.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mimemi.mvc.dto.EventDTO;
import mimemi.mvc.dto.ReviewDTO;
import mimemi.mvc.paging.PageCnt;
import mimemi.mvc.util.DbUtil;

public class EventDAOImpl implements EventDAO {
	private Properties proFile = new Properties();
	
	/**
	 * dbQuery.properties 로딩해 Properties 객체에 저장
	 * */
	public EventDAOImpl() {
		try {
			proFile.load(getClass().getClassLoader().getResourceAsStream("dbQuery.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 이벤트 게시글 등록
	 * @param: EventDTO(int eventId, String eventTitle, String eventContent, String eventAttach, String eventImg,
	 * 			String eventStartdate, String eventEnddate)
	 * @return: int(등록 성공한 레코드 수)
	 * */
	@Override
	public int insert(EventDTO event) throws SQLException {
		//INSERT INTO EVENT(EVENT_NO,EVENT_TITLE,EVENT_CONTENT,EVENT_ATTACH,EVENT_IMG,EVENT_REGDATE,EVENT_STARTDATE,EVENT_ENDDATE) values(EVENT_NO_SEQ.NEXTVAL,?,?,?,?,sysdate,?,?)
		Connection con =null;
		PreparedStatement ps = null;
		String sql ="INSERT INTO EVENT(EVENT_NO,EVENT_TITLE,EVENT_CONTENT,EVENT_ATTACH,EVENT_IMG,EVENT_REGDATE,EVENT_STARTDATE,EVENT_ENDDATE) values(EVENT_NO_SEQ.NEXTVAL,?,?,?,?,sysdate,?,?)";
		//String sql=proFile.getProperty("");
		int result =0;
		
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, event.getEventTitle());
			ps.setString(2, event.getEventContent());
			ps.setString(3, event.getEventAttach());
			ps.setString(4, event.getEventImg());
			ps.setString(5, event.getEventStartdate());
			ps.setString(6, event.getEventEnddate());
			
			result=ps.executeUpdate();
		}finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}
	
	/**
	 * 이벤트 게시글 수정
	 * @param: EventDTO(int eventId, String eventTitle, String eventContent, String eventStartdate,
	 * 			String eventEnddate)
	 * 			게시글 제목과 내용, 시작일과 종료일만 변경
	 * @return: int(수정 성공한 레코드 수)
	 * */
	@Override
	public int updateEvent(EventDTO event) throws SQLException {
		Connection con =null;
		PreparedStatement ps = null;
		String sql ="update event set EVENT_TITLE=?, EVENT_CONTENT=?, EVENT_STARTDATE=?, EVENT_ENDDATE=? where EVENT_NO=?";
		//String sql=proFile.getProperty("");
		int result = 0;
		try {
			con= DbUtil.getConnection();
			con.setAutoCommit(false);
			
			ps =con.prepareStatement(sql);
			ps.setString(1, event.getEventTitle());
			ps.setString(2, event.getEventContent());
			ps.setString(3, event.getEventStartdate());
			ps.setString(4, event.getEventEnddate());
			ps.setInt(5, event.getEventNo());
			
			result = ps.executeUpdate();
			if(result==0) {
				con.rollback();
				throw new SQLException("이벤트 수정에 실패했습니다.");
			}else {
				//수정한 파일이 값이 있다면, 파일 수정한다.
				if(event.getEventAttach()!=null) {
					int re = updateFaqAttachCon(con,event.getEventNo(),event.getEventAttach());
						if(re!=1) {
							con.rollback();
							throw new SQLException("이미지 파일 수정에 실패했습니다.");
						}
				}
				
				if(event.getEventImg()!=null) {
					int re = updateFaqImgCon(con,event.getEventNo(),event.getEventImg());
						if(re!=1) {
							con.rollback();
							throw new SQLException("썸네일 파일 수정에 실패했습니다.");
						}
				}
				con.commit();
			}
		}finally {
			con.commit();
			DbUtil.dbClose(ps, con);
		}
		return result;
	}
	
	/*이벤트 게시글 수정할 때, 첨부파일만 수정하는 메소드*/
	public int updateFaqAttachCon(Connection con, int eventNo, String eventAttach) throws SQLException {
		PreparedStatement ps =null;
		String sql ="update event set EVENT_ATTACH =? where EVENT_NO =?";
		//String sql=proFile.getProperty("");
		int result =0;
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, eventAttach);
			ps.setInt(2, eventNo);
			
			result=ps.executeUpdate();
			System.out.println("첨부파일수정 ...dao");
		}finally {
			DbUtil.dbClose(ps, null);
		}
		return result;
	} 
	
	/*이벤트 게시글 수정할 때, 썸네일만 수정하는 메소드*/
	public int updateFaqImgCon(Connection con, int eventNo, String evenImg) throws SQLException {
		PreparedStatement ps =null;
		String sql ="update event set EVENT_IMG =? where EVENT_NO =?";
		//String sql=proFile.getProperty("");
		int result =0;
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, evenImg);
			ps.setInt(2, eventNo);
			
			result=ps.executeUpdate();
			System.out.println("썸네일수정 ...dao");
		}finally {
			DbUtil.dbClose(ps, null);
		}
		return result;
	} 

	/**
	 * 이벤트 게시글 썸네일 이미지 수정
	 * @param: int eventId, String eventImg
	 * @return: int(수정 성공한 레코드 수)
	 * */
	@Override
	public int updateEventImg(int eventId, String eventImg) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 이벤트 게시글 상세 이미지 수정
	 * @param: int eventId, String eventAttach
	 * @return: int(수정 성공한 레코드 수)
	 * */
	@Override
	public int updateEventAttach(int eventId, String eventAttach) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 이벤트 게시글 전체 조회
	 * @return: List<EventDTO>
	 * */
	@Override
	public List<EventDTO> selectAll(String field ,int pageNo) throws SQLException {
		Connection con =null;
		PreparedStatement ps =null;
		ResultSet rs =null;
		
		List<EventDTO> eventList = new ArrayList<EventDTO>();
		SimpleDateFormat eventFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		//event.selectAll=select * from EVENT order by EVENT_NO desc
		String sql = null;
		
		if(field != null) {
			if (field.equals("reg")) {
				sql = "SELECT*FROM (select a.* ,rownum from (select * from EVENT order by event_startdate desc ) a) where rownum >=? and rownum <=?"; //전체
				//sql = proFile.getProperty("");
			} else if (field.equals("preEvent")) {
				sql = "SELECT*FROM (select a.* ,rownum from (SELECT*FROM EVENT where EVENT_STARTDATE > sysdate order by EVENT_STARTDATE desc ) a) where rownum >=? and rownum <=?"; //진행전
				//sql = proFile.getProperty("");
			} else if (field.equals("Eventing")) {
				sql = "SELECT*FROM (select a.* ,rownum from (SELECT*FROM EVENT where EVENT_ENDDATE >= sysdate and EVENT_STARTDATE <= sysdate  order by EVENT_STARTDATE desc ) a) where rownum >=? and rownum <=?"; //진행중
				//sql = proFile.getProperty("");
			}else if (field.equals("afterEvent")) {
				sql = "SELECT*FROM (select a.* ,rownum from ( SELECT*FROM EVENT where EVENT_ENDDATE< sysdate order by EVENT_STARTDATE desc ) a) where rownum >=? and rownum <=?";//진행완료
				//sql = proFile.getProperty("");
			}  
		}
		
		
		try {
			int totalCount =this.getTotalCount(field);
			int totalPage =totalCount%PageCnt.getPagesize()==0 ? totalCount/PageCnt.getPagesize() :  totalCount/PageCnt.getPagesize()+1;
			PageCnt pagecnt = new PageCnt();
			pagecnt.setPageCnt(totalPage);
			pagecnt.setPageNo(pageNo);
			
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			//페이지 처리 : ?에 들어갈 값 설정하기
			ps.setInt(1, ((pageNo-1)*PageCnt.pagesize)+1);
			ps.setInt(2, pageNo*PageCnt.pagesize);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				EventDTO event = new EventDTO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						eventFormat.format(rs.getDate(6)),
						eventFormat.format(rs.getDate(7)), 
						eventFormat.format(rs.getDate(8))
						);
				
				eventList.add(event);
			}
			
			
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}
			
	
		return eventList;
	}
	
	/**
	 * 전체 레코드 수 가져오기
	 * */
	private int getTotalCount(String field) throws SQLException{
		Connection con =null;
		PreparedStatement ps=null;
		ResultSet rs =null;
		int totalCount=0;
		String sql=null;
		//String sql=proFile.getProperty("review.totalCount");
		if(field != null) {
			if (field.equals("reg")) {
				sql = "SELECT*FROM EVENT order by EVENT_REGDATE desc"; //전체
				//sql = proFile.getProperty("review.selectAllReg");
			} else if (field.equals("preEvent")) {
				sql = "SELECT*FROM EVENT where EVENT_STARTDATE > sysdate order by EVENT_STARTDATE desc"; //진행전
				//sql = proFile.getProperty("review.selectAllHigirate");
			} else if (field.equals("Eventing")) {
				sql = "SELECT*FROM EVENT where EVENT_ENDDATE >= sysdate and EVENT_STARTDATE <= sysdate  order by EVENT_STARTDATE desc"; //진행중
				//sql = proFile.getProperty("review.selectAllRowrate");
			}else if (field.equals("afterEvent")) {
				sql = " SELECT*FROM EVENT where EVENT_ENDDATE< sysdate order by EVENT_STARTDATE desc ";//진행완료
				//sql = proFile.getProperty("review.selectAllView");
			}  
		}
		
		try {
			con=DbUtil.getConnection();
			ps =con.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				totalCount=rs.getInt(1);
			}
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return totalCount;
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
		Connection con =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="select * from event where EVENT_NO=?";
		//String sql=proFile.getProperty("");
		EventDTO event = null; 
		SimpleDateFormat eventFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, eventNo);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				event = new EventDTO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						eventFormat.format(rs.getDate(6)),
						eventFormat.format(rs.getDate(7)),
						eventFormat.format(rs.getDate(8))
						
						);
				 
				
			}
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return event;
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

	@Override
	public List<EventDTO> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int eventNo) throws SQLException {
		Connection con =null;
		PreparedStatement ps = null;
		String sql="delete from event where EVENT_NO=?";
		//String sql=proFile.getProperty("");
		int result=0;
		
		try {
			con =DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, eventNo);
			
			result= ps.executeUpdate();
			System.out.println("dao: 삭제한 이벤트번호 "+eventNo);
			
		}finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}
	

}

package mimemi.mvc.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import mimemi.mvc.dao.AskDAO;
import mimemi.mvc.dao.AskDAOImpl;
import mimemi.mvc.dto.AskDTO;
import mimemi.mvc.dto.UserDTO;

public class AskServiceImpl implements AskService {

	private AskDAO askDao = new AskDAOImpl();
	
	@Override
	public void insertAsk(AskDTO askDTO) throws SQLException {
		
		int result = askDao.insertAsk(askDTO);
		if(result==0)throw new SQLException("등록되지 않았습니다");

	}
	


	@Override
	public void updateAsk(AskDTO askDTO) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAskAttach(int askNo, String askAttach) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAsk(int askNo, String path) throws SQLException {
		
			int result = askDao.deleteAsk(askNo);
			if(result == 0) throw new SQLException("삭제되지 않았습니다");
			
			
	}

	@Override
	public List<AskDTO> selectAllAsk(int pageNum, String field) throws SQLException {
		
		List<AskDTO> list = askDao.selectAllAsk(pageNum, field);
		
		return list;
	}
	
	

	@Override
	public List<AskDTO> selectAllByPaging(int pageNo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AskDTO selectByAskNo(int askNo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateState(int askNo, String state) throws SQLException {
		// TODO Auto-generated method stub

	}


	/**
	 * 1:1 상세보기
	 * */
	@Override
	public AskDTO selectByUserId(String userId) throws SQLException {
		
		AskDTO askDto = askDao.selectByuserId(userId);
		
		if(askDto==null)throw new SQLException("상세보기에 오류가 발생했습니다");
		
		
		return askDto;
	}

}

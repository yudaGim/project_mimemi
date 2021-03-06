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
	public void updateAsk(AskDTO askDTO,String path) throws SQLException {
		AskDTO askDto = askDao.selectByAskNo(askDTO.getAskNo());
		
		if(askDto==null) throw new SQLException("문의를 찾을 수 없습니다.");
		
		
		String dbAttach=askDto.getAskAttach();
		
		int result = askDao.updateAsk(askDTO);
		
		if(result==0) {
			if(dbAttach!=null) {
				new java.io.File(path+"/"+dbAttach).delete();
			}
			throw new SQLException("수정되지 않았습니다");
		}else {
			if(dbAttach!=null) {
				new java.io.File(path+"/"+dbAttach).delete(); //경로확인하기
			}
		}
	}

	@Override
	public void updateAskAttach(int askNo, String askAttach) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAsk(AskDTO ask, String path) throws SQLException {
		
			int result = askDao.deleteAsk(ask.getAskNo());
			if(result == 0) throw new SQLException("삭제되지 않았습니다");
			
			if(ask.getAskAttach()!=null) {
				new java.io.File(path+"/"+ask.getAskAttach()).delete();
			}
			
			
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
		
		AskDTO askDto = askDao.selectByAskNo(askNo);
		
		if(askDto==null)throw new SQLException("상세보기에 오류가 발생했습니다");
		
		
		return askDto;
		
	}

	@Override
	public void updateState(AskDTO askDto) throws SQLException {
		
		
		int result=askDao.updateState(askDto);
		if(result==0)throw new SQLException("수정되지 않았습니다.");
		
	}

	
	/**
	 * 검색기능-제목, 내용
	 * */

	@Override
	public List<AskDTO> selectByKeyword(String keyWord, String filed, int pageNo)throws SQLException {
		
		List<AskDTO> list = askDao.selectByKeyword(keyWord, filed, pageNo);
		
		return list;
	}


	

}

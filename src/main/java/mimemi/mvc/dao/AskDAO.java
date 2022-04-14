package mimemi.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mimemi.mvc.dto.AnswerDTO;
import mimemi.mvc.dto.AskDTO;

public interface AskDAO {
	/**
	 * 1:1 ���� ���
	 * @param AskDTO(String userId, String askTitle, String askContent, String askAttach,
			String askCategory)
	 * */
	int insertAsk(AskDTO askDTO) throws SQLException;

	/**
	 * 1:1 ���� ���� 
	 * @param AskDTO(int askNo, String userId, String askTitle, String askContent, 
			String askCategory)
	 * */
	int updateAsk(AskDTO askDTO) throws SQLException;
	
	/**
	 * 1:1 ���� �Խñ� ÷������ ����
	 * @param: int askNo, String askAttach
	 * @return: int(���� ������ ���ڵ� ��)
	 * */
	int updateAskAttach(int askNo, String askAttach) throws SQLException;
	
	/**
	 * 1:1 ���� ����
	 * @param int askNo
	 * */
	int deleteAsk(int askNo) throws SQLException;
	
	/**
	 * 1:1 ���� ��ü �˻�
	 * */
	List<AskDTO> selectAllAsk() throws SQLException;
	
	/**
	 * 1:1 ���� ��ü �˻�(������ ó��)
	 * @param int pageNo
	 * */
	List<AskDTO> selectAllByPaging(int pageNo) throws SQLException;
	
	/**
	 * ���� ��ȣ�� �ҷ�����
	 * @param int askNo
	 * */
	AskDTO selectByAskNo(int askNo) throws SQLException;
	
	/**
	 * 1:1 ���� �亯 ���� ���� ���
	 * */
	int updateState(int askNo, String state) throws SQLException;
	
	/**
	 * ���ǿ� ���� �亯(���) ���� ��������
	 * @param int askNo
	 * @return List<AnswerDTO>
	 * */
	List<AnswerDTO> selectAnsByAskNo(int askNo) throws SQLException;
}
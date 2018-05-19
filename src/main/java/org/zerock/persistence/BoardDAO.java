package org.zerock.persistence;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;

import java.util.List;

public interface BoardDAO {

    void regist(BoardVO boardVO) throws Exception;

    BoardVO read(Integer bno) throws Exception;

    void modify(BoardVO boardVO) throws Exception;

    void remove(Integer bno) throws Exception;

    List<BoardVO> listAll() throws Exception;

    List<BoardVO> listPage(int page) throws Exception;

    List<BoardVO> listCriteria(Criteria criteria) throws Exception;

    int countPaging(Criteria cri) throws Exception;

    List<BoardVO> listSearch(SearchCriteria cri) throws Exception;

    int listSearchCount(SearchCriteria cri) throws Exception;

    void updateReplyCnt(Integer bno, int amount)throws Exception;

    void updateViewCnt(Integer bno) throws Exception;

    void addAttach(String fullName)throws Exception;

}
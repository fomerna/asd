package org.zerock.persistence;


import org.zerock.domain.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDAOImpl implements BoardDAO {
    private static final String NAMESPACE = "org.zerock.mappers.BoardMapper";

    private final SqlSession sqlSession;

    @Inject
    public BoardDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void regist(BoardVO boardVO) throws Exception {
        sqlSession.insert(NAMESPACE + ".regist", boardVO);
    }

    @Override
    public BoardVO read(Integer bno) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".read", bno);
    }

    @Override
    public void modify(BoardVO boardVO) throws Exception {
        sqlSession.update(NAMESPACE + ".modify", boardVO);
    }

    @Override
    public void remove(Integer bno) throws Exception {
        sqlSession.delete(NAMESPACE + ".remove", bno);
    }

    @Override
    public List<BoardVO> listAll() throws Exception {
        return sqlSession.selectList(NAMESPACE + ".listAll");
    }

    @Override
    public List<BoardVO> listPage(int page) throws Exception {

        if (page <= 0) {
            page = 1;
        }

        page = (page - 1) * 10;

        return sqlSession.selectList(NAMESPACE + ".listPage", page);
    }

    @Override
    public List<BoardVO> listCriteria(Criteria cri) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".listCriteria", cri);
    }

    @Override
    public int countPaging(Criteria cri) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".countPaging", cri);
    }

    @Override
    public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".listSearch", cri);
    }

    @Override
    public int listSearchCount(SearchCriteria cri) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".listSearchCount", cri);
    }

    @Override
    public void updateReplyCnt(Integer bno, int amount) throws Exception {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("bno", bno);
        paramMap.put("amount", amount);

        sqlSession.update(NAMESPACE + ".updateReplyCnt", paramMap);
    }

    @Override
    public void updateViewCnt(Integer bno) throws Exception {
        sqlSession.update(NAMESPACE + ".updateViewCnt", bno);
    }

    @Override
    public void addAttach(String fullName) throws Exception {
        sqlSession.insert(NAMESPACE + ".addAttach", fullName);
    }
}
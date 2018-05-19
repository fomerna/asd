package org.zerock.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.persistence.BoardDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;

    @Inject
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Transactional
    @Override
    public void regist(BoardVO boardVO) throws Exception {
        boardDAO.regist(boardVO);

        String[] files = boardVO.getFiles();

        if (files == null) {
            return;
        }

        for (String fileName : files) {
            boardDAO.addAttach(fileName);
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public BoardVO read(Integer bno) throws Exception {
        boardDAO.updateViewCnt(bno);
        return boardDAO.read(bno);
    }

    @Override
    public void modify(BoardVO boardVO) throws Exception {
        boardDAO.modify(boardVO);
    }

    @Override
    public void remove(Integer bno) throws Exception {
        boardDAO.remove(bno);

    }

    @Override
    public List<BoardVO> listAll() throws Exception {
        return boardDAO.listAll();
    }

    @Override
    public List<BoardVO> listCriteria(Criteria cri) throws Exception {
        return boardDAO.listCriteria(cri);
    }

    @Override
    public int listCountCriteria(Criteria cri) throws Exception {
        return boardDAO.countPaging(cri);
    }

    @Override
    public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
        return boardDAO.listSearch(cri);
    }

    @Override
    public int listSearchCount(SearchCriteria cri) throws Exception {
        return boardDAO.listSearchCount(cri);
    }
}
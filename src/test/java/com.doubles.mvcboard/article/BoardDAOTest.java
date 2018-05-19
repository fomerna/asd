package com.doubles.mvcboard.article;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.persistence.BoardDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class BoardDAOTest {

    private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

    @Inject
    private BoardDAO boardDAO;

    @Test
    public void testRegist() throws Exception {

        for (int i = 1; i <= 2; i++) {
            BoardVO boardVO = new BoardVO();
            boardVO.setTitle(i + "번째 글 제목입니다...");
            boardVO.setContent(i + "번재 글 내용입니다...");
            boardVO.setWriter("user0" + (i % 10));

            boardDAO.regist(boardVO);
        }

    }

    @Test
    public void testRead() throws Exception {
        logger.info(boardDAO.read(30).toString());
    }

    @Test
    public void testModify() throws Exception {
        BoardVO article = new BoardVO();
        article.setBno(11);
        article.setTitle("글 수정 테스트 제목");
        article.setContent("글 수정 테스트 내용");
        boardDAO.modify(article);
    }

    @Test
    public void testRemove() throws Exception {
        boardDAO.remove(902);
    }

    @Test
    public void testListPage() throws Exception {

        int page = 1;

        List<BoardVO> list = boardDAO.listPage(page);

        for (BoardVO boardVO : list) {
            logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
        }

    }

    @Test
    public void testListCriteria() throws Exception {
        Criteria criteria = new Criteria();
        criteria.setPage(1);
        criteria.setPerPageNum(20);

        List<BoardVO> articles = boardDAO.listCriteria(criteria);

        for (BoardVO article : articles) {
            logger.info(article.getBno() + " : " + article.getTitle());
        }
    }

    @Test
    public void testURI() throws Exception {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/board/read")
                .queryParam("bno", 12)
                .queryParam("perPageNum", 20)
                .build();
        logger.info("/board/read?bno=12&perPageNum=20");
        logger.info(uriComponents.toString());
    }

    @Test
    public void testURI2() throws Exception {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/{module}/{page}")
                .queryParam("bno", 12)
                .queryParam("perPageNum", 20)
                .build()
                .expand("board", "Read")
                .encode();
        logger.info("/board/read?bno=12&perPageNum=20");
        logger.info(uriComponents.toString());
    }


    @Test
    public void testDynamic1() throws Exception {

        SearchCriteria cri = new SearchCriteria();
        cri.setPage(1);
        cri.setKeyword("글");
        cri.setSearchType("t");

        logger.info("======================");

        List<BoardVO> list = boardDAO.listSearch(cri);

        for (BoardVO boardVO : list) {
            logger.info(boardVO.getBno() + " : " + boardVO.getTitle());
        }

        logger.info("======================");

        logger.info("searched count : " + boardDAO.listSearchCount(cri));
    }
}
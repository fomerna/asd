package com.doubles.mvcboard.article;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.domain.ReplyVO;
import org.zerock.persistence.ReplyDAO;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class ReplyDAOTest {

    private static final Logger logger = LoggerFactory.getLogger(ReplyDAOTest.class);

    @Inject
    private ReplyDAO dao;

    @Test
    public void testReplyCreate() throws Exception {

        for (int i = 1; i <= 2; i++) {
            ReplyVO vo = new ReplyVO();
            vo.setBno(8);
            vo.setReplytext(i + "번째 댓글입니다..");
            vo.setReplyer("user0" + (i % 10));
            dao.create(vo);
        }

    }

    @Test
    public void testReplyList() throws Exception {

        logger.info(dao.list(1000).toString());

    }

    @Test
    public void testReplyUpdate() throws Exception {

        ReplyVO vo = new ReplyVO();
        vo.setBno(1000);
        vo.setReplytext(2 + "2222번째 댓글 수정...");
        dao.update(vo);

    }

    @Test
    public void testReplyDelete() throws Exception {

        dao.delete(3);

    }

    @Test
    public void testReplyPaging() throws Exception {

        Criteria cri = new Criteria();
        cri.setPerPageNum(5);
        cri.setPage(3);

        List<ReplyVO> replies = dao.listPage(1000, cri);

        for (ReplyVO reply : replies) {
            logger.info(reply.getRno() + " : " + reply.getReplytext());
        }

    }

}

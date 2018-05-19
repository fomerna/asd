/*
package com.doubles.mvcboard.article.controller;

import ArticleVO;
import ArticleService;
import Criteria;
import PageMaker;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.inject.Inject;

@Controller
@RequestMapping("/article/paging")
public class ArticlePagingController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ArticlePagingController.class);

    private final ArticleService articleService;

    @Inject
    public ArticlePagingController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String writeGET() {

        logger.info("paging writeGET() called...");

        return "article/paging/write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writePOST(ArticleVO articleVO,
                            RedirectAttributes redirectAttributes) throws Exception {

        logger.info("paging writePOST() called...");

        articleService.create(articleVO);
        redirectAttributes.addFlashAttribute("msg", "regSuccess");

        return "redirect:/article/paging/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Criteria criteria) throws Exception {

        logger.info("paging list() called ...");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);
        pageMaker.setTotalCount(articleService.countArticles(criteria));

        model.addAttribute("articles", articleService.listCriteria(criteria));
        model.addAttribute("pageMaker", pageMaker);

        return "article/paging/list";
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(@RequestParam("bno") int bno,
                       @ModelAttribute("criteria") Criteria criteria,
                       Model model) throws Exception {

        logger.info("paging read() called ...");
        model.addAttribute("article", articleService.read(bno));

        return "article/paging/read";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyGET(@RequestParam("bno") int bno,
                            @ModelAttribute("criteria") Criteria criteria,
                            Model model) throws Exception {

        logger.info("paging modifyGet() called ...");
        model.addAttribute("article", articleService.read(bno));

        return "article/paging/modify";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPOST(ArticleVO articleVO,
                             Criteria criteria,
                             RedirectAttributes redirectAttributes) throws Exception {

        logger.info("paging modifyPOST() called ...");
        articleService.update(articleVO);
        redirectAttributes.addAttribute("page", criteria.getPage());
        redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
        redirectAttributes.addFlashAttribute("msg", "modSuccess");

        return "redirect:/article/paging/list";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("bno") int bno,
                         Criteria criteria,
                         RedirectAttributes redirectAttributes) throws Exception {

        logger.info("paging remove() called ...");
        articleService.delete(bno);
        redirectAttributes.addAttribute("page", criteria.getPage());
        redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
        redirectAttributes.addFlashAttribute("msg", "delSuccess");

        return "redirect:/article/paging/list";
    }

}
*/

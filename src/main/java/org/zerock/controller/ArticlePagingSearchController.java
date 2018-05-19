/*
package com.doubles.mvcboard.article.controller;

import ArticleService;
import PageMaker;
import SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
@RequestMapping("/article/paging/search")
public class ArticlePagingSearchController {

    private static final Logger logger = LoggerFactory.getLogger(ArticlePagingSearchController.class);

    private final ArticleService articleService;

    @Inject
    public ArticlePagingSearchController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                       Model model) throws Exception {

        logger.info("search list() called ...");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(searchCriteria);
        //pageMaker.setTotalCount(articleService.countArticles(searchCriteria));
        pageMaker.setTotalCount(articleService.countSearchedArticles(searchCriteria));

        //model.addAttribute("articles", articleService.listCriteria(searchCriteria));
        model.addAttribute("articles", articleService.listSearch(searchCriteria));
        model.addAttribute("pageMaker", pageMaker);

        return "article/list";
    }



}
*/

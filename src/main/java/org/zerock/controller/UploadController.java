package org.zerock.controller;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

@Controller
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Resource(name = "uploadPath")
    private String uploadPath;

    @RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
    public void uploadForm() {

    }

    @RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
    public String uploadForm(MultipartFile file, Model model) throws Exception {
        logger.info("originalName: " + file.getOriginalFilename());
        logger.info("size: " + file.getSize());
        logger.info("contentType: " + file.getContentType());

        String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());

        model.addAttribute("savedName", savedName);

        return "uploadResult";
    }

    private String uploadFile(String orginalName, byte[] fileData) throws Exception {

        UUID uid = UUID.randomUUID();

        String savedName = uid.toString() + "_" + orginalName;

        File target = new File(uploadPath, savedName);

        FileCopyUtils.copy(fileData, target);

        return savedName;
    }

    @RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
    public void uploadAjax() {
    }

    @RequestMapping(value = "/uploadAjax",
            method = RequestMethod.POST,
            produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
        logger.info("originalName: " + file.getOriginalFilename());
        logger.info("size: " + file.getSize());
        logger.info("contentType: " + file.getContentType());

        return new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.CREATED);

    }

    private static String makeThumbnail(
            String uploadPath,
            String path,
            String fileName) throws Exception {

        BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));

        BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

        String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;

        File newFile = new File(thumbnailName);

        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

        ImageIO.write(destImg, formatName.toUpperCase(), newFile);
        return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');

    }


}

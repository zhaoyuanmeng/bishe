package com.course.business.controller.admin;


import com.course.server.domain.Upload;
import com.course.server.dto.ResponseDto;
import com.course.server.dto.UploadDto;
import com.course.server.service.UploadService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/upload")
public class UploadController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);
    public static final String BUSINESS_NAME = "留言反馈";

    @Resource
    private UploadService uploadService;




    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody UploadDto uploadDto) {

        ResponseDto responseDto = new ResponseDto();
        uploadService.save(uploadDto);
        responseDto.setContent(uploadDto);
        return responseDto;
    }
}

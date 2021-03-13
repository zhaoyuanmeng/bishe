package com.course.server.service;

import com.course.server.domain.Teacher;
import com.course.server.domain.Upload;
import com.course.server.dto.UploadDto;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.course.server.mapper.UploadMapper;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import javax.annotation.Resource;


@Service
public class UploadService {

    @Resource
    public UploadMapper uploadMapper;

    /**
     * 新增
     *         Teacher teacher = CopyUtil.copy(teacherDto, Teacher.class);
     if (StringUtils.isEmpty(teacherDto.getId())) {
     this.insert(teacher);
     } else {
     this.update(teacher);
     }
     */
    public void save(UploadDto uploadDto) {
        Upload upload = CopyUtil.copy(uploadDto, Upload.class);
        this.insert(upload);
    }


    /**
     *  teacher.setId(UuidUtil.getShortUuid());
     teacherMapper.insert(teacher);
     * @param upload
     */
    public void insert(Upload upload) {
        upload.setId(UuidUtil.getShortUuid());
        uploadMapper.insert(upload);
    }
}

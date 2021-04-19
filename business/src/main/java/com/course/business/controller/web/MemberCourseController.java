package com.course.business.controller.web;

import com.course.server.dto.MemberCourseDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.MemberCourseService;
import com.course.server.util.ValidatorUtil;
import com.course.server.vo.MemberCourseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController("webMemberCourseController")
@RequestMapping("/web/member-course")
public class MemberCourseController {

    private static final Logger LOG = LoggerFactory.getLogger(MemberCourseController.class);
    public static final String BUSINESS_NAME = "会员课程报名";

    @Resource
    private MemberCourseService memberCourseService;

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/enroll")
    public ResponseDto enroll(@RequestBody MemberCourseDto memberCourseDto) {
        // 保存校验
        ValidatorUtil.require(memberCourseDto.getMemberId(), "会员id");
        ValidatorUtil.require(memberCourseDto.getCourseId(), "课程id");

        ResponseDto responseDto = new ResponseDto();
        memberCourseDto = memberCourseService.enroll(memberCourseDto);
        responseDto.setContent(memberCourseDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/get-enroll")
    public ResponseDto getEnroll(@RequestBody MemberCourseDto memberCourseDto) {
        ResponseDto responseDto = new ResponseDto();
        memberCourseDto = memberCourseService.getEnroll(memberCourseDto);
        responseDto.setContent(memberCourseDto);
        return responseDto;
    }

//    新家的
    /**
     * 获取会员报名的课程列表
     * @param memberId
     * @return
     */
    @GetMapping("getMemberCourse/{memberId}")
    public List<MemberCourseVO> getMemberCourse(@PathVariable String memberId){
        List<MemberCourseVO> memberCourseList = memberCourseService.getMemberCourseList(memberId);
        return memberCourseList;
    }

    /**
     * 会员删除所报的课程
     * @param id
     */
    @GetMapping("deleteCourseMember/{id}")
    public void deleteCourseMember(@PathVariable String id){
        memberCourseService.delete(id);
    }
}

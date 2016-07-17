package org.michaelliu.rpc.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michaelliu.rpc.entity.Student;
import org.michaelliu.rpc.dao.StudentMapper;
import org.michaelliu.rpc.service.StudentService;
import org.michaelliu.rpc.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Michael on 2016/7/16.
 */
public class StudentServiceImpl implements StudentService {

    private static final Log log = LogFactory.getLog(StudentService.class);

    @Autowired
    private StudentMapper studentMapper;

    public Student saveOrUpdateSelective(Student student) throws Exception {
        log.info("saveOrUpdateSelective receives parameter: " + student);
        if (null == student) {
            throw new IllegalArgumentException("Student is null!");
        }
        Student studentEntity = null;
        try {
            int result;
            if (null == student.getId()) {
                result = studentMapper.insertSelective(student);
            } else {
                student.setUpdateTime(new Date());
                result = studentMapper.updateByPrimaryKeySelective(student);
            }
            if (result > 0) {
                studentEntity = student;
            } else {
                throw new IllegalStateException("Insertion fail to create/update new record in `tb_student`!");
            }
        } catch (Exception e) {
            log.error("saveOrUpdateSelective exception: " + e);
            throw e;
        }
        log.info("saveOrUpdateSelective returns: " + studentEntity);
        return studentEntity;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public StudentVo findById(Long id) {
        log.info("findById receives parameter: " + id);
        if (id == null) {
            throw new IllegalArgumentException("Student's id is empty!");
        }
        StudentVo studentVo = studentMapper.selectByPrimaryKey(id);
        log.info("findById returns: " + studentVo);
        return studentVo;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<StudentVo> findByName(String name) {
        log.info("findByName receives parameter: " + name);
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Student's name is empty!");
        }
        List<StudentVo> studentList = studentMapper.selectByName(name);
        log.info("findByName returns: " + studentList);
        return studentList;
    }

}

package org.mliuframework.rpc.service;

import org.mliuframework.rpc.entity.Student;
import org.mliuframework.rpc.vo.StudentVo;

import java.util.List;

/**
 * Created by Michael on 7/8/16.
 * RMI: rmi://localhost:1199/RmiStudentService
 * Hessian: http://localhost:8080/remoting/HessianStudentService
 */
public interface StudentService {

    Student saveOrUpdateSelective(Student student) throws Exception;

    StudentVo findById(Long id);

    List<StudentVo> findByName(String name);

}

package org.michaelliu.rpc.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michaelliu.rpc.entity.Student;
import org.michaelliu.rpc.service.StudentService;
import org.michaelliu.rpc.util.ConstantUtils;
import org.michaelliu.rpc.util.PropertyUtils;
import org.michaelliu.rpc.vo.RspStudentListVo;
import org.michaelliu.rpc.vo.RspStudentVo;
import org.michaelliu.rpc.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Michael on 7/11/16.
 */
@Controller
@RequestMapping("/rmi/student")
public class RmiStudentController {

    private static final Log log = LogFactory.getLog(RmiStudentController.class);

    @Autowired
    private StudentService rmiStudentService;

    /**
     * http://localhost:8080/rpc/rmi/student/save
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public RspStudentVo doSave(@RequestBody StudentVo studentVo) {
        log.info("doSave student receives: " + JSON.toJSONString(studentVo));
        RspStudentVo rspVo = new RspStudentVo();
        try {
            boolean failFlag = false;
            if (studentVo == null || !studentVo.containAllRequiredField()) {
                failFlag = true;
            } else {
                Student student = new Student.Builder().setId(studentVo.getId()).
                        setName(studentVo.getName()).setAge(studentVo.getAge()).build();
                Student studentEntity = rmiStudentService.saveOrUpdateSelective(student);
                if (null == studentEntity) {
                    failFlag = true;
                } else {
                    studentVo.setId(studentEntity.getId());
                    rspVo.setStatus(ConstantUtils.STATUS_SUCCESS);
                    rspVo.setStatusInfo(PropertyUtils.getStatusInfo(ConstantUtils.STATUS_PREFIX +
                            ConstantUtils.STATUS_SUCCESS));
                    rspVo.setStudent(studentVo);
                }
            }
            if (failFlag) {
                rspVo.setStatus(ConstantUtils.STATUS_FAIL);
                rspVo.setStatusInfo(PropertyUtils.getStatusInfo(ConstantUtils.STATUS_PREFIX +
                        ConstantUtils.STATUS_FAIL));
            }
        } catch (Exception e) {
            log.error("doSave student throws exception: " + e);
            rspVo.setStatus(ConstantUtils.STATUS_EXCEPTION);
            rspVo.setStatusInfo(PropertyUtils.getStatusInfo(ConstantUtils.STATUS_PREFIX +
                    ConstantUtils.STATUS_EXCEPTION) + ": " + e);
        }
        log.info("doSave student returns: " + JSON.toJSONString(rspVo));
        return rspVo;
    }

    /**
     * http://localhost:8080/rpc/rmi/student/findbyid
     */
    @RequestMapping(value = "/findbyid", method = RequestMethod.GET)
    @ResponseBody
    public RspStudentVo findById(@RequestParam Long id) {
        log.info("findById receives: " + id);
        RspStudentVo rspVo = new RspStudentVo();
        try {
            boolean failFlag = false;
            if (id == null) {
                failFlag = true;
            } else {
                StudentVo student = rmiStudentService.findById(id);
                if (student == null) {
                    failFlag = true;
                } else {
                    rspVo.setStatus(ConstantUtils.STATUS_SUCCESS);
                    rspVo.setStatusInfo(PropertyUtils.getStatusInfo(ConstantUtils.STATUS_PREFIX +
                            ConstantUtils.STATUS_SUCCESS));
                    rspVo.setStudent(student);
                }
            }
            if (failFlag) {
                rspVo.setStatus(ConstantUtils.STATUS_FAIL);
                rspVo.setStatusInfo(PropertyUtils.getStatusInfo(ConstantUtils.STATUS_PREFIX +
                        ConstantUtils.STATUS_FAIL));
            }
        } catch (Exception e) {
            log.error("findById throws exception: " + e);
            rspVo.setStatus(ConstantUtils.STATUS_EXCEPTION);
            rspVo.setStatusInfo(PropertyUtils.getStatusInfo(ConstantUtils.STATUS_PREFIX +
                    ConstantUtils.STATUS_EXCEPTION) + ": " + e);
        }
        log.info("findById returns: " + JSON.toJSONString(rspVo));
        return rspVo;
    }

    /**
     * http://localhost:8080/rpc/rmi/student/findbyname
     */
    @RequestMapping(value = "/findbyname", method = RequestMethod.POST)
    @ResponseBody
    public RspStudentListVo findByName(@RequestBody StudentVo studentVo) {
        log.info("findByName receives: " + JSON.toJSONString(studentVo));
        RspStudentListVo rspVo = new RspStudentListVo();
        try {
            if (studentVo == null || !studentVo.containAllRequiredField()) {
                rspVo.setStatus(ConstantUtils.STATUS_FAIL);
                rspVo.setStatusInfo(PropertyUtils.getStatusInfo(ConstantUtils.STATUS_PREFIX +
                        ConstantUtils.STATUS_FAIL));
            } else {
                List<StudentVo> studentList = rmiStudentService.findByName(studentVo.getName());
                rspVo.setStatus(ConstantUtils.STATUS_SUCCESS);
                rspVo.setStatusInfo(PropertyUtils.getStatusInfo(ConstantUtils.STATUS_PREFIX +
                        ConstantUtils.STATUS_SUCCESS));
                rspVo.setStudentList(studentList);
            }
        } catch (Exception e) {
            log.error("findByName throws exception: " + e);
            rspVo.setStatus(ConstantUtils.STATUS_EXCEPTION);
            rspVo.setStatusInfo(PropertyUtils.getStatusInfo(ConstantUtils.STATUS_PREFIX +
                    ConstantUtils.STATUS_EXCEPTION) + ": " + e);
        }
        log.info("findByName returns: " + JSON.toJSONString(rspVo));
        return rspVo;
    }

}

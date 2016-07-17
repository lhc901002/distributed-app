package org.michaelliu.rpc.vo;

/**
 * Created by Michael on 7/11/16.
 */
public class RspStudentVo extends RspVo {

    private StudentVo student;

    public RspStudentVo() {}

    public RspStudentVo(Integer status, String statusInfo) {
        super(status, statusInfo);
    }

    public StudentVo getStudent() {
        return student;
    }

    public void setStudent(StudentVo student) {
        this.student = student;
    }

}

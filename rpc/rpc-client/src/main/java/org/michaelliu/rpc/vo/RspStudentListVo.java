package org.michaelliu.rpc.vo;

import java.util.List;

/**
 * Created by Michael on 7/12/16.
 */
public class RspStudentListVo extends RspVo {

    private List<StudentVo> studentList;

    public List<StudentVo> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentVo> studentList) {
        this.studentList = studentList;
    }

}

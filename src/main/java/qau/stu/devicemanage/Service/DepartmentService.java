package qau.stu.devicemanage.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qau.stu.devicemanage.Entity.Department;
import qau.stu.devicemanage.Mapper.DepartmentMapper;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }
}

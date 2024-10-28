package qau.stu.devicemanage.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import qau.stu.devicemanage.Entity.Department;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    @Select("select * from department")
    List<Department> selectAll();
}

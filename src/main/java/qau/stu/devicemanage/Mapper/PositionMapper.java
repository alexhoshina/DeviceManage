package qau.stu.devicemanage.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import qau.stu.devicemanage.Entity.Position;

import java.util.List;

@Mapper
public interface PositionMapper {
    @Select("select * from position")
    List<Position> selectAll();
}

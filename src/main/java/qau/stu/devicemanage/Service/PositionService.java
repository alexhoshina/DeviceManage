package qau.stu.devicemanage.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qau.stu.devicemanage.Entity.Position;
import qau.stu.devicemanage.Mapper.PositionMapper;

import java.util.List;

@Service
public class PositionService {
    @Autowired
    private PositionMapper positionMapper;

    public List<Position> selectAll() {
        return positionMapper.selectAll();
    }
}

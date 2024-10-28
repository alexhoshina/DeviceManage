package qau.stu.devicemanage.Service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qau.stu.devicemanage.Entity.Chart;
import qau.stu.devicemanage.Entity.User;
import qau.stu.devicemanage.Mapper.UserMapper;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> selectUser(String username, Integer departmentId, Integer positionId) {
        return userMapper.selectUser(username, departmentId, positionId);
    }

    public void save(User user) {
        userMapper.save(user);
    }

    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public void delete(Integer id) {
        userMapper.delete(id);
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public List<Chart> getChartByDepartment() {
        return userMapper.getChartByDepartment();
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public boolean check(String username, String password, HttpSession session) {

            User user = userMapper.selectByName(username);
            if (user != null && user.getPassword().equals(password)) {
                session.setAttribute("current", user);
                return true;
            } else {
                return false;
            }
    }
}

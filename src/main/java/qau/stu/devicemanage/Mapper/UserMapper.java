package qau.stu.devicemanage.Mapper;

import org.apache.ibatis.annotations.*;
import qau.stu.devicemanage.Entity.Chart;
import qau.stu.devicemanage.Entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    // 调用查询用户数据的存储过程
    @Select("call GetUserDetails(#{userName} , #{departmentId} , #{positionId})")
    List<User> selectUser(@Param("userName")String userName, @Param("departmentId")Integer departmentId, @Param("positionId")Integer positionId);

    // 调用保存用户数据的存储过程
    @Select("call SaveOrUpdateUser(#{user.userId}, #{user.userName}, #{user.password}, #{user.positionId}, #{user.departmentId}, #{user.contact}, #{user.accessLevel})")
    void save(@Param("user")User user);

    // 根据用户ID查询用户数据
    @Select("select * from user where userID = #{id}")
    User selectById(@Param("id")Integer id);

    // 根据用户ID删除用户数据
    @Delete("delete from user where userID = #{id}")
    void delete(Integer id);

    @Select("select * from user")
    List<User> selectAll();

    @Select("call CountUsersByDepartment()")
    List<Chart> getChartByDepartment();

    @Update("update user set userName = #{userName}, password = #{password}, positionID = #{positionId}, departmentID = #{departmentId}, contact = #{contact}, accessLevel = #{accessLevel} where userID = #{userId}")
    void update(User user);

    @Select("select * from user where userName = #{username}")
    User selectByName(String username);
}

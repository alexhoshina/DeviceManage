package qau.stu.devicemanage.Entity;

import lombok.Data;

@Data
public class User {
    private Integer userId; // 用户ID
    private String userName; // 用户名
    private String password; // 密码
    private Integer departmentId; // 部门ID
    private Integer positionId; // 职位ID
    private String contact; // 联系方式
    private Integer accessLevel; // 权限等级
}

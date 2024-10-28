package qau.stu.devicemanage.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import qau.stu.devicemanage.Entity.User;
import qau.stu.devicemanage.Service.DepartmentService;
import qau.stu.devicemanage.Service.PositionService;
import qau.stu.devicemanage.Service.UserService;

@Controller
@RequestMapping("/")
public class WebController {
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;


    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("current");
        return "redirect:/login";
    }

    @RequestMapping("/profile")
    public String profile(Integer userid, Model model) {
        model.addAttribute("user", userService.selectById(userid));
        model.addAttribute("departments", departmentService.selectAll());
        model.addAttribute("positions", positionService.selectAll());
        return "profile";
    }

    @RequestMapping("/toUpdatePassword")
    public String toUpdatePassword(Integer userid,String msg, Model model) {
        model.addAttribute("userid", userid);
        model.addAttribute("msg", msg);
        return "updatePassword";
    }

    //修改密码
    @RequestMapping("/updatePassword")
    public String updatePassword(Integer userid, String currentPassword, String newPassword, RedirectAttributes redirectAttributes) {
        User user = userService.selectById(userid);

        // 检查当前密码是否为空
        if (isNullOrEmpty(currentPassword)) {
            redirectAttributes.addAttribute("msg", "原密码不能为空");
            redirectAttributes.addAttribute("userid", userid);
            return "redirect:/toUpdatePassword"; // 或者其他错误页面
        }

        // 检查当前密码是否正确
        if (!currentPassword.equals(user.getPassword())) {
            redirectAttributes.addAttribute("msg", "原密码不正确");
            redirectAttributes.addAttribute("userid", userid);
            return "redirect:/toUpdatePassword"; // 或者其他错误页面
        }

        // 检查新密码是否为空
        if (isNullOrEmpty(newPassword)) {
            redirectAttributes.addAttribute("msg", "新密码不能为空");
            redirectAttributes.addAttribute("userid", userid);
            return "redirect:/toUpdatePassword"; // 或者其他错误页面
        }

        // 更新密码
        user.setPassword(newPassword);
        userService.update(user);

        return "user/echarts"; // 或者其他成功页面
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    @RequestMapping("loginCheck")
    public String loginCheck(String username, String password, HttpSession session, Model model) {
        boolean flag = userService.check(username, password, session);
        System.out.println(flag);
        if (flag) {
            return "redirect:/";
        } else {
            String msg = "登录失败，用户名或密码错误";
            model.addAttribute("msg", msg);
            return "login"; // 返回登录页面，并显示错误消息
        }
    }

    @RequestMapping("/")
    public String index(HttpSession session) {
        if (isUserAdmin(session)) {
            return "admin/index";
        } else {
            return "user/index";
        }
    }

    @RequestMapping("/toUser")
    public String toUser() {
        return "user/index";
    }

    @RequestMapping("/toAdmin")
    public String toAdmin() {
        return "admin/index";
    }

    private boolean isUserAdmin(HttpSession session) {
        if (session.getAttribute("current") != null) {
            User tmp = (User) session.getAttribute("current");
            if(tmp.getAccessLevel() != null){
                return tmp.getAccessLevel() > 8;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }
}

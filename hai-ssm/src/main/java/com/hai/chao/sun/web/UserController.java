package com.hai.chao.sun.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hai.chao.sun.common.Response;
import com.hai.chao.sun.pojo.User;
import com.hai.chao.sun.service.UserService;
import com.hai.chao.sun.vo.EasyUiPageResult;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 通用页面跳转
     * @param pageName:页面名称
     * @return
     */
    @RequestMapping("/page/{pageName}")
    public ModelAndView toUsers(@PathVariable("pageName") String pageName) {
        return new ModelAndView(pageName);
    }

    @RequestMapping("/list")
    @ResponseBody
    public EasyUiPageResult<User> listUser(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "rows", defaultValue = "5") Integer pageSize) {
        return userService.querAllUser(pageNum, pageSize);
    }
    
    /**
     * 导出 
     * @param pageNum：页码
     * @param pageSize：每页显示条数
     * @return
     */
    @RequestMapping("/export/excel")
    @ResponseBody
    public ModelAndView exportExcel(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "rows", defaultValue = "5") Integer pageSize) {
        ModelAndView mv = new ModelAndView("excelView");
        EasyUiPageResult<User> pageResult = userService.querAllUser(pageNum, pageSize);
        List<User> users = pageResult.getRows();
        mv.addObject("userList",users);
        
        return mv;
    }
    
    
    @RequestMapping("/save")
    @ResponseBody
    public Response saveuser(User user) {
        //调用service保存用户
        try {
            Integer count = userService.saveUser(user);
            if(count == 1) {
                return Response.success("新增用户成功！");
            }else {
                return Response.fail("新增用户失败！");
            }
        }catch (Exception e) {
            return Response.fail("新增用户失败！");
        }
        
    }
   
}

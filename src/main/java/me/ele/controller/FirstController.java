package me.ele.controller;

import me.ele.annotation.UserAccess;
import me.ele.model.User;
import me.ele.service.Util;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liuhuan
 * @CreateDate: 2019/6/19
 * <p>Copyright: Copyright (c) 2019</p>
 */
@RestController
public class FirstController {

    @Resource
    Util util;

    @RequestMapping("/first")
    @UserAccess(desc = "first")
    public Object first() {
        User user = new User();
        user.setName("zhang");
        user.setAge(10);


        return util.print();
    }

    @RequestMapping("/doError")
    @UserAccess(desc = "error")
    public Object error() {
        return 1 / 0;
    }

    @RequestMapping("/second")
    @UserAccess(desc = "second")
    public Object second(@RequestParam String a, @RequestParam Integer b) {
        return "second controller";
    }

}

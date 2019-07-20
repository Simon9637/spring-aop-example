package me.ele.service;

import me.ele.annotation.UserAccess;
import org.springframework.stereotype.Service;

/**
 * @Author: liuhuan
 * @CreateDate: 2019/6/19
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Service
public class Util {
    @UserAccess(desc = "first")
    public String[] print() {
        return printhelo();
    }


    private String[] printhelo() {
        return new String[] {"hello"};
    }
}

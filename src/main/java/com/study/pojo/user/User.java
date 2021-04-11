package com.study.pojo.user;

import java.io.Serializable;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer uid;

    private String username;

    private String password;

    /*显示匿名用户*/
    public String getAnonymousName(){
        if(null==username)
            return null;

        if(username.length()<=1)
            return "*";

        if(username.length()==2)
            return username.substring(0,1) +"*";

        char[] cs =username.toCharArray();
        for (int i = 1; i < cs.length-1; i++) {
            cs[i]='*';
        }
        return new String(cs);

    }

    private static final long serialVersionUID = 1L;
}
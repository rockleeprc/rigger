package com.rigger;

import com.rigger.model.User;
import org.junit.Test;

import java.util.Optional;

public class OptionalTest {

    /**
     * orElse：不管对象是否为null都执行
     * orElseGet：当对象为null是执行
     */
    @Test
    public void orElse(){
        User user = getUser();
        Optional.ofNullable(user).orElse(new User(1));
        Optional.ofNullable(user).orElseGet(()->new User(3));
    }

    /**
     * ofNullable：参数可以为null
     */
    @Test
    public void ofNullable() {
        Optional<User> optional = Optional.ofNullable(null);
        System.out.println(optional.get());//NoSuchElementException
    }

    /**
     * of：参数不能为null
     */
    @Test
    public void of() {
        Optional<User> optional = Optional.of(null);//NullPointerException
        System.out.println(optional.get().getId());
    }

    /**
     * 创建空对象，不能调用
     */
    @Test
    public void empty() {
        Optional<User> optional = Optional.empty();
        System.out.println(optional.get());//NoSuchElementException
    }

    /**
     * map可以针对参数一直导航下去
     */
    @Test
    public void map() {
        User user = getUser();
        Integer result = Optional.ofNullable(user).map(u -> u.getId()).map(i -> i.intValue()).orElse(10);
        System.out.println(result);
    }

    /**
     * 判断对象是否为null，抛出NullPointerException
     */
    @Test
    public void orElseThrow() {
        User user = null;
        Optional.ofNullable(user).orElseThrow(() -> new NullPointerException());
    }

    private User getUser() {
        User user = new User();
        user.setId(1);
        user.setAge(18);
        user.setName("A");
        user.setAddress("CCC");
        return user;
    }
}

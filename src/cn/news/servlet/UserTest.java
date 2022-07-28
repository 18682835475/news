package cn.news.servlet;

import java.io.Serializable;
import java.util.List;

/**
 * @author LCB
 * @date 2022/7/4 11:51
 */
public class UserTest implements Serializable {
    private static final long serialVersionUID = 8959761497403478596L;
    private String name;
    private String password;
    private List<String> habits;

    public UserTest() {
    }

    public UserTest(String name, String password, List<String> habits) {
        this.name = name;
        this.password = password;
        this.habits = habits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getHabits() {
        return habits;
    }

    public void setHabits(List<String> habits) {
        this.habits = habits;
    }
}

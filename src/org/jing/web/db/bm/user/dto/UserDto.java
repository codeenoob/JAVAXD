package org.jing.web.db.bm.user.dto;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-25 <br>
 */
public class UserDto {
    private int id = -2;

    private String name;

    private String account;

    private String password;

    private int role;

    private int sex;

    private String crtDate;

    private String lastLoginDate;

    private String lastLogoutDate;

    private String lastOperDate;

    private String state;

    public UserDto() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(String crtDate) {
        this.crtDate = crtDate;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLogoutDate() {
        return lastLogoutDate;
    }

    public void setLastLogoutDate(String lastLogoutDate) {
        this.lastLogoutDate = lastLogoutDate;
    }

    public String getLastOperDate() {
        return lastOperDate;
    }

    public void setLastOperDate(String lastOperDate) {
        this.lastOperDate = lastOperDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

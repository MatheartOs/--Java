package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Contact implements Serializable {
    private int id;
    private String name;
    private String gender;
    private String group;
    private String address;
    private String email;
    private String phone;
    private String remark;
    private String createTime;

    public Contact(int id, String name, String gender, String group, String address, String email, String phone, String remark, String createTime) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.group = group;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.remark = remark;
        this.createTime = createTime;
    }

    // 兼容旧构造方法
    public Contact(int id, String name, String gender, String group, String address, String email, String phone, String remark) {
        this(id, name, gender, group, address, email, phone, remark, "");
    }

    // 兼容旧构造方法
    public Contact(int id, String name, String gender, String group, String address, String email, String phone) {
        this(id, name, gender, group, address, email, phone, "", "");
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
    public String getCreateTime() { return createTime; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }

    @Override
    public String toString() {
        return id + ". " + name + " [" + gender + "] (" + group + ") - " + phone + ", " + email + ", " + address;
    }
}

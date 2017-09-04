package com.mplus.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import com.mplus.core.entity.base.BaseEntity;
import com.mplus.utils.MD5Util;

@Entity
@Table(name = "user")
public class User extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -9071755205002858798L;

	@Id
    @Column(length=64)
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
    private String id;
    
	@Column(length=32)
    @NotEmpty(message = "用户名不能为空")
    private String username;
    
	@Column(length=32)
    @NotEmpty
    private String code;
    
	@Column(length=48)
    @NotEmpty(message = "密码不能为空")
    private String password;   
    
    public User() {}

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = MD5Util.MD5Salt(password);
	}

}

package com.house.house.common.bean;

import java.util.Date;

public class Comment {
  
  private Long id;
  private String content;
  private Long   houseId;
  private Date   createTime;
  private Integer blogId;
  private Integer type;
  private Long    userId;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Long getHouseId() {
    return houseId;
  }
  public void setHouseId(Long houseId) {
    this.houseId = houseId;
  }
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public Integer getType() {
    return type;
  }
  public void setType(Integer type) {
    this.type = type;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public Long getUserId() {
    return userId;
  }
  public void setUserId(Long userId) {
    this.userId = userId;
  }
  public Integer getBlogId() {
    return blogId;
  }
  public void setBlogId(Integer blogId) {
    this.blogId = blogId;
  }
  

  
}

package com.shengx1ao.entity;

import javax.persistence.*;

/**
 * Database Table Remarks:
 *   商品评价表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table comment_info
 */
@Table(name="comment_info")
public class CommentInfo {
    /**
     * Database Column Remarks:
     *   主键
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment_info.id
     *
     * @mbg.generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Database Column Remarks:
     *   评价商品
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment_info.goodsId
     *
     * @mbg.generated
     */
    @Column(name="goodsId")
    private Long goodsid;

    /**
     * Database Column Remarks:
     *   评价用户id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment_info.userId
     *
     * @mbg.generated
     */
    @Column(name="userId")
    private Long userid;

    /**
     * Database Column Remarks:
     *   用户等级
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment_info.level
     *
     * @mbg.generated
     */
    @Column(name="level")
    private Integer level;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment_info.createTime
     *
     * @mbg.generated
     */
    @Column(name="createTime")
    private String createtime;

    /**
     * Database Column Remarks:
     *   评价内容
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment_info.content
     *
     * @mbg.generated
     */
    @Column(name="content")
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment_info.id
     *
     * @return the value of comment_info.id
     *
     * @mbg.generated
     */
    @Transient
    private String userName;

    @Transient
    private String goodsName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment_info.id
     *
     * @param id the value for comment_info.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment_info.goodsId
     *
     * @return the value of comment_info.goodsId
     *
     * @mbg.generated
     */
    public Long getGoodsid() {
        return goodsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment_info.goodsId
     *
     * @param goodsid the value for comment_info.goodsId
     *
     * @mbg.generated
     */
    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment_info.userId
     *
     * @return the value of comment_info.userId
     *
     * @mbg.generated
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment_info.userId
     *
     * @param userid the value for comment_info.userId
     *
     * @mbg.generated
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment_info.level
     *
     * @return the value of comment_info.level
     *
     * @mbg.generated
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment_info.level
     *
     * @param level the value for comment_info.level
     *
     * @mbg.generated
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment_info.createTime
     *
     * @return the value of comment_info.createTime
     *
     * @mbg.generated
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment_info.createTime
     *
     * @param createtime the value for comment_info.createTime
     *
     * @mbg.generated
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment_info.content
     *
     * @return the value of comment_info.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment_info.content
     *
     * @param content the value for comment_info.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
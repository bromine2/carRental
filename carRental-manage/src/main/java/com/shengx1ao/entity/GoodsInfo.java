package com.shengx1ao.entity;

import cn.hutool.core.util.StrUtil;

import javax.persistence.*;
import java.util.List;

/**
 * Database Table Remarks:
 *   商品详情表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table goods_info
 */
@Table(name="goods_info")
public class GoodsInfo {
    /**
     * Database Column Remarks:
     *   主键
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.id
     *
     * @mbg.generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Database Column Remarks:
     *   商品名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.name
     *
     * @mbg.generated
     */
    @Column(name="name")
    private String name;

    /**
     * Database Column Remarks:
     *   商品价格
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.price
     *
     * @mbg.generated
     */
    @Column(name="price")
    private Double price;

    /**
     * Database Column Remarks:
     *   商品折扣
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.discount
     *
     * @mbg.generated
     */
    @Column(name="discount")
    private Double discount;

    /**
     * Database Column Remarks:
     *   商品销量
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.sales
     *
     * @mbg.generated
     */
    @Column(name="sales")
    private Integer sales;

    /**
     * Database Column Remarks:
     *   商品点赞数
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.hot
     *
     * @mbg.generated
     */
    @Column(name="hot")
    private Integer hot;

    /**
     * Database Column Remarks:
     *   是否推荐
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.recommend
     *
     * @mbg.generated
     */
    @Column(name="recommend")
    private String recommend;

    /**
     * Database Column Remarks:
     *   库存
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.count
     *
     * @mbg.generated
     */
    @Column(name="count")
    private Integer count;

    /**
     * Database Column Remarks:
     *   所属类别
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.typeId
     *
     * @mbg.generated
     */
    @Column(name="typeid")
    private Long typeid;

    /**
     * Database Column Remarks:
     *   商品图片id，用英文逗号隔开
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.fields
     *
     * @mbg.generated
     */
    @Column(name="fields")
    private String fields;

    /**
     * Database Column Remarks:
     *   所属卖家
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.userId
     *
     * @mbg.generated
     */
    @Column(name="userid")
    private Long userid;

    /**
     * Database Column Remarks:
     *   所属卖家等级
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.level
     *
     * @mbg.generated
     */
    @Column(name="level")
    private Integer level;

    /**
     * Database Column Remarks:
     *   商品描述
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods_info.description
     *
     * @mbg.generated
     */
    @Column(name="description")
    private String description;
    @Transient
    private List<Long> fileList;
    @Transient
    private String commentStatus;
    @Transient
    private String typeName;
    @Transient
    private String userName;
    @Transient
    private String descriptionView;

    public String getDescriptionView() {
        if(StrUtil.isEmpty(description)){
            return "";
        }
        if(description.length()>30){
            return description.substring(0,30)+"...";
        }
        return descriptionView;
    }

    public void setDescriptionView(String descriptionView) {
        this.descriptionView = descriptionView;
    }

    public List<Long> getFileList() {
        return fileList;
    }

    public void setFileList(List<Long> fileList) {
        this.fileList = fileList;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.id
     *
     * @return the value of goods_info.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.id
     *
     * @param id the value for goods_info.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.name
     *
     * @return the value of goods_info.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.name
     *
     * @param name the value for goods_info.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.price
     *
     * @return the value of goods_info.price
     *
     * @mbg.generated
     */
    public Double getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.price
     *
     * @param price the value for goods_info.price
     *
     * @mbg.generated
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.discount
     *
     * @return the value of goods_info.discount
     *
     * @mbg.generated
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.discount
     *
     * @param discount the value for goods_info.discount
     *
     * @mbg.generated
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.sales
     *
     * @return the value of goods_info.sales
     *
     * @mbg.generated
     */
    public Integer getSales() {
        return sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.sales
     *
     * @param sales the value for goods_info.sales
     *
     * @mbg.generated
     */
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.hot
     *
     * @return the value of goods_info.hot
     *
     * @mbg.generated
     */
    public Integer getHot() {
        return hot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.hot
     *
     * @param hot the value for goods_info.hot
     *
     * @mbg.generated
     */
    public void setHot(Integer hot) {
        this.hot = hot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.recommend
     *
     * @return the value of goods_info.recommend
     *
     * @mbg.generated
     */
    public String getRecommend() {
        return recommend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.recommend
     *
     * @param recommend the value for goods_info.recommend
     *
     * @mbg.generated
     */
    public void setRecommend(String recommend) {
        this.recommend = recommend == null ? null : recommend.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.count
     *
     * @return the value of goods_info.count
     *
     * @mbg.generated
     */
    public Integer getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.count
     *
     * @param count the value for goods_info.count
     *
     * @mbg.generated
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.typeId
     *
     * @return the value of goods_info.typeId
     *
     * @mbg.generated
     */
    public Long getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.typeId
     *
     * @param typeid the value for goods_info.typeId
     *
     * @mbg.generated
     */
    public void setTypeid(Long typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.fields
     *
     * @return the value of goods_info.fields
     *
     * @mbg.generated
     */
    public String getFields() {
        return fields;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.fields
     *
     * @param fields the value for goods_info.fields
     *
     * @mbg.generated
     */
    public void setFields(String fields) {
        this.fields = fields == null ? null : fields.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.userId
     *
     * @return the value of goods_info.userId
     *
     * @mbg.generated
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.userId
     *
     * @param userid the value for goods_info.userId
     *
     * @mbg.generated
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.level
     *
     * @return the value of goods_info.level
     *
     * @mbg.generated
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.level
     *
     * @param level the value for goods_info.level
     *
     * @mbg.generated
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods_info.description
     *
     * @return the value of goods_info.description
     *
     * @mbg.generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods_info.description
     *
     * @param description the value for goods_info.description
     *
     * @mbg.generated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}
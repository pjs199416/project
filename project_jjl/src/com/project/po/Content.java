package com.project.po;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 内容实体类
 * @author Administrator
 *
 */
public class Content implements Serializable {
	/** id 内容id */
	private Long id; 
	/** title 内容标题 */
	private String title; 
	/** description 内容描述 */
	private String description; 
	/** url 内容链接路径 */
	private String url; 
	/** pic 内容图片访问路径 */
	private String pic;  
	/** createdate 内容创建时间 */
	private Timestamp createdate;  
	/** price 价格 */
	private double price;  
	/** weight 重量 */
	private double weight; 
	/** num 库存 */
	private int num;
	/** status 使用状态 1代表启用 0代表废弃 */
	private int status = 1;
	/** 所属内容分类 */
	private ContentCategory contentCategory;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public ContentCategory getContentCategory() {
		return contentCategory;
	}
	public void setContentCategory(ContentCategory contentCategory) {
		this.contentCategory = contentCategory;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}

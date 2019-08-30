package com.project.po;

import java.io.Serializable;
import java.sql.Timestamp;

/**商品实体类
 * @author Administrator
 *
 */
public class Item  implements Serializable {
	/**--id　商品主键--*/
	private Long id;  
	/**--title　商品标题--*/
	private String title;
	/**--description　商品描述--*/
	private String description;
	/**--pic　商品图片访问路径--*/
	private String pic;
	/**--price　商品价格--*/
	private double price; 
	/**--weight　商品重量--*/
	private double weight; 
	/**--num　库存--*/
	private int num;
	/**--status　商品状态  1代表正常使用  0代表废弃--*/
	private int status = 1;
	/**--createdate　商品创建时间--*/
	private Timestamp createdate; 
	/** 商品 属于 哪个商品类别  多对一 关系 */
	private ItemCategory itemCategory = new ItemCategory();
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public ItemCategory getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}
	

	
}

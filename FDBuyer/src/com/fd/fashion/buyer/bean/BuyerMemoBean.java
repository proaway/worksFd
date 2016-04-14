package com.fd.fashion.buyer.bean;

import java.io.Serializable;
import java.util.Date;

import com.fd.util.PageInfo;

/**买家备注信息
 * @author zhangling
 *
 */
public class BuyerMemoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**买家备注ID*/
	private long id;
	/**买家ID*/
	private long buyerId;
	/**买家备注创建时间*/
	private Date createDate;
	/**买家备注内容 */
	private String memo;
	/**操作人*/
	private String operator;
	/**分页信息*/
	private PageInfo pageInfo;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
}

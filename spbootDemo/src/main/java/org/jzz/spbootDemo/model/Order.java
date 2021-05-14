package org.jzz.spbootDemo.model;


public class Order {
	String orderName;
	User user;
	Double amount;
	
	
	
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Order [orderName=" + orderName  + ", amount=" + amount + ", user=" + user + "]";
	}

	
}

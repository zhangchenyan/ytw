package cn.edu.seu.ytw.bean;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="ordera")
public class Order {
	private String id;
	private int sId;
	private int rId;

	
	
	@Id
	@GenericGenerator(name="idGenerator",strategy="uuid")
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(generator="idGenerator")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="sID")
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	
	@Column(name="rID")
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	

	
	
}
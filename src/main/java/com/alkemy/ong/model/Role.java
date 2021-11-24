package com.alkemy.ong.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "roles")
public class Role {
	
	 @Id
	 @GeneratedValue(strategy =  GenerationType.IDENTITY)
	 private Long id;
	 @Column(nullable = false)
	 private String name;
	 @Column(nullable = true)
	 private String description;
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date timestamp;
	 
	 public Role() {
		 
	 }

	public Role(Long id, String name, String description, Date timestamp) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(timestamp, other.timestamp);
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description=" + description + ", timestamp=" + timestamp + "]";
	}
	 
	
	 
}

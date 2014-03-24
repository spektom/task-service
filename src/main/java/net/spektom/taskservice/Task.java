package net.spektom.taskservice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Task model
 */
@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String text;
	private Date created = new Date();
	private Date completed;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCompleted() {
		return completed;
	}

	public void setCompleted(Date completed) {
		this.completed = completed;
	}
}

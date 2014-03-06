package de.is.project.shop.gui;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Container<T> {

	@XmlElementWrapper(name = "elements")
	@XmlElement(name = "element")
	List<T> list = new LinkedList<T>();

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}

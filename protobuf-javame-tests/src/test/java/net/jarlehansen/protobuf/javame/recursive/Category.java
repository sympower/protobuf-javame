package net.jarlehansen.protobuf.javame.recursive;

import java.util.Vector;

import net.jarlehansen.protobuf.javame.recursive.generated.ProtoCategory;

public class Category {
	public int id;
	public String name = "";
	public int end = 0;

	// Vector<NewBizCategory>
	public Vector children = null;

	public Category() {
	}

	public Category(ProtoCategory pCategory) {
		this.id = pCategory.getId();
		this.name = pCategory.getName();
		this.children = new Vector();
		for (int i = 0; i < pCategory.getCategory().size(); i++) {
			ProtoCategory ca = (ProtoCategory) pCategory.getCategory().get(i);
			this.children.addElement(new Category(ca));
		}
	}

	public ProtoCategory toProtocol() {
		ProtoCategory.Builder builder = ProtoCategory.newBuilder();
		builder.setId(this.id);
		builder.setName(this.name);
		if ((null != this.children) && (this.children.size() > 0)) {
			for (int i = 0; i < children.size(); i++) {
				builder.addElementCategory(((Category) children.elementAt(i))
						.toProtocol());
			}
		}
		return builder.build();
	}
}

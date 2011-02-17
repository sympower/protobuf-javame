package net.jarlehansen.protobuf.javame.recursive;

import java.util.Vector;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import net.jarlehansen.protobuf.javame.recursive.generated.ProtoCategory;
import net.jarlehansen.protobuf.javame.recursive.generated.ProtoCategoryResp;
import net.jarlehansen.protobuf.javame.recursive.generated.ProtoData;
import net.jarlehansen.protobuf.javame.recursive.generated.ProtoInventory;
import net.jarlehansen.protobuf.javame.recursive.generated.ProtoMisc;
import net.jarlehansen.protobuf.javame.recursive.generated.ProtoProduct;

public class RecursionTest {

	@Test
	public void testProtoCategoryResponse() throws Exception {
		ProtoCategoryResp.Builder builder = ProtoCategoryResp.newBuilder();

		ProtoProduct pro1 = ProtoProduct.newBuilder().setId(1001)
				.setName("Shoe").build();
		ProtoProduct pro2 = ProtoProduct.newBuilder().setId(1002)
				.setName("Shirt").build();
		ProtoProduct pro3 = ProtoProduct.newBuilder().setId(1003)
				.setName("Pants").build();

		ProtoData data1 = ProtoData.newBuilder().setData(201).build();
		ProtoData data2 = ProtoData.newBuilder().setData(202).build();
		ProtoData data3 = ProtoData.newBuilder().setData(203).build();

		ProtoInventory inv1 = ProtoInventory.newBuilder().setId(101)
				.setProduct(pro1).setData(data1).build();
		ProtoInventory inv2 = ProtoInventory.newBuilder().setId(102)
				.setProduct(pro2).setData(data2).build();
		ProtoInventory inv3 = ProtoInventory.newBuilder().setId(103)
				.setProduct(pro3).setData(data3).build();

		builder.setType(0);
		builder.setCat(buildCategory());

		builder.addElementInv(inv1);
		builder.addElementInv(inv2);
		builder.addElementInv(inv3);

		ProtoMisc mis = ProtoMisc.newBuilder().setMemo("xxx").build();
		builder.setMisc(mis);

		ProtoCategoryResp inputData = builder.build();
		byte[] data = inputData.toByteArray();
		
		ProtoCategoryResp outputData = ProtoCategoryResp.parseFrom(data);
		
		assertEquals(inputData.getType(), outputData.getType());
		assertEquals(inputData.getCat().getName(), outputData.getCat().getName());
		assertEquals(inputData.getMisc().getMemo(), outputData.getMisc().getMemo());
	}

	public ProtoCategory buildCategory() throws Exception {
		ProtoCategory ategory = this.addChildrenRecursely().toProtocol();
		return ategory;
	}

	public Category createNewCategoryBy(int categoryID, String categoryLabel,
			int end) {
		Category bizCat = new Category();
		bizCat.id = categoryID;
		bizCat.name = categoryLabel;
		bizCat.children = new Vector<Category>();
		bizCat.end = end;
		return bizCat;
	}

	public Category addChildrenRecursely() {
		Category ca = this.createNewCategoryBy(0, "ANY", 0);
		Vector v = new Vector();

		Category ca1 = this.createNewCategoryBy(1, "ANY_1", 1);
		Vector v1 = new Vector();
		Category ca2 = this.createNewCategoryBy(2, "ANY_2", 2);
		Vector v2 = new Vector();
		Category ca3 = this.createNewCategoryBy(3, "ANY_3", 3);
		Vector v3 = new Vector();

		v.add(ca1);
		v.add(ca2);
		v.add(ca3);
		ca.children = v;

		Category ca1_1 = this.createNewCategoryBy(11, "ANY_1_1", 11);
		Vector v1_1 = new Vector();
		Category ca1_2 = this.createNewCategoryBy(12, "ANY_1_2", 12);
		Vector v1_2 = new Vector();
		v1.add(ca1_1);
		v1.add(ca1_2);
		ca1.children = v1;

		Category ca2_1 = this.createNewCategoryBy(21, "ANY_2_1", 21);
		Category ca2_2 = this.createNewCategoryBy(22, "ANY_2_2", 22);
		Vector v2_2 = new Vector();
		Category ca2_3 = this.createNewCategoryBy(23, "ANY_2_3", 23);
		v2.add(ca2_1);
		v2.add(ca2_2);
		v2.add(ca2_3);
		ca2.children = v2;

		Category ca1_2_1 = this.createNewCategoryBy(121, "ANY_1_2_1", 121);
		Category ca1_2_2 = this.createNewCategoryBy(122, "ANY_1_2_2", 122);
		Category ca1_2_3 = this.createNewCategoryBy(123, "ANY_1_2_3", 123);
		Vector v1_2_3 = new Vector();
		v1_2.add(ca1_2_1);
		v1_2.add(ca1_2_2);
		v1_2.add(ca1_2_3);
		ca1_2.children = v1_2;

		Category ca2_2_1 = this.createNewCategoryBy(221, "ANY_2_2_1", 221);
		Category ca2_2_2 = this.createNewCategoryBy(222, "ANY_2_2_2", 222);
		Category ca2_2_3 = this.createNewCategoryBy(223, "ANY_2_2_3", 223);
		v2_2.add(ca2_2_1);
		v2_2.add(ca2_2_2);
		v2_2.add(ca2_2_3);
		ca2_2.children = v2_2;

		return ca;
	}
}

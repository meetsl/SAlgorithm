package algorithm;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	String value;
	List<TreeNode> children;

	public TreeNode() {
		children = new ArrayList<>();
	}

	public TreeNode(String value) {
		this.value = value;
		children = new ArrayList<>();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return value;
	}
}

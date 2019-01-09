package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * ���������ҳ�����Ҷ��·��
 * @author meetsl
 *
 */
public class TraversingTree {
	public static void main(String[] args) {
		TraversingTree dfs = new TraversingTree();
		// ����һ����
		TreeNode root = new TreeNode("A");
		// �ڶ���
		root.children.add(new TreeNode("B"));
		root.children.add(new TreeNode("C"));
		// ������
		root.children.get(0).children.add(new TreeNode("D"));
		root.children.get(0).children.add(new TreeNode("E"));
		root.children.get(1).children.add(new TreeNode("F"));
		root.children.get(1).children.add(new TreeNode("H"));
		root.children.get(1).children.add(new TreeNode("G"));
		// ���Ĳ�
		root.children.get(0).children.get(1).children.add(new TreeNode("I"));
		/**
		 * ���ṹ A
		 * 
		 *   B       C
		 * 
		 * D   E    F H G
		 * 
		 *     I
		 */
		dfs.recurTree(root);

		List<List<TreeNode>> result = dfs.bfsTree(root);
		System.out.println(result);

		System.out.println(dfs.dfsTree(root));
	}

	/**
	 * ������ȱ������ݹ鷽ʽ�� --- ����Tree��
	 */
	public void recurTree(TreeNode root) {
		List<List<String>> result = new ArrayList<>();
		List<String> path = new ArrayList<>();
		path.add(root.value);
		findPath(result, root, path, "E");
		System.out.println(result);
	}

	private void findPath(List<List<String>> result, TreeNode node, List<String> path, String target) {
		if (target.equals(node.value)) {//��������·��
			result.add(path);
			return;
		}

		if (node.children == null || node.children.size() <= 0) {
			//ɾ�������е�һ�� if �� for ѭ���е� if ��������ע�ͣ��ɲ�������Ҷ��·��
//			result.add(path);
			return;
		}

		for (int i = 0; i < node.children.size(); i++) {
			TreeNode child = node.children.get(i);
			List<String> cPath = new ArrayList<>();
			cPath.addAll(path);
			cPath.add(child.value);
			if (result.size() > 0)
				break;
			findPath(result, child, cPath, target);
		}
	}

	/**
	 * ������ȱ��� ---- ������������Ҷ��·��
	 * 
	 * @param root
	 *            ���ڵ�
	 * @return Ҷ��·���ļ���
	 */
	public List<List<TreeNode>> bfsTree(TreeNode root) {
		Queue<TreeNode> nodeQueue = new LinkedList<>();
		Queue<List<TreeNode>> qstr = new LinkedList<>();
		List<List<TreeNode>> result = new ArrayList<>();
		nodeQueue.add(root);
		ArrayList<TreeNode> arrayList = new ArrayList<>();
		qstr.add(arrayList);

		while (!nodeQueue.isEmpty()) {
			TreeNode curNode = nodeQueue.remove();
			List<TreeNode> curList = qstr.remove();

			if (curNode.children == null || curNode.children.size() <= 0) {
				curList.add(curNode);
				result.add(curList);
			} else {
				for (int i = 0; i < curNode.children.size(); i++) {
					TreeNode treeNode = curNode.children.get(i);
					nodeQueue.add(treeNode);
					List<TreeNode> list = new ArrayList<>(curList);
					list.add(curNode);
					qstr.add(list);
				}
			}
		}

		return result;
	}

	/**
	 * ������ȱ������ǵݹ鷽ʽ�� ----- ������������Ҷ��·��
	 * 
	 * @param root
	 *            ���ڵ�
	 * @return Ҷ��·���ļ���
	 */
	public List<List<TreeNode>> dfsTree(TreeNode root) {
		Stack<TreeNode> nodeStack = new Stack<>();
		Stack<List<TreeNode>> pathStack = new Stack<>();
		List<List<TreeNode>> result = new ArrayList<>();
		nodeStack.push(root);
		ArrayList<TreeNode> arrayList = new ArrayList<>();
		arrayList.add(root);
		pathStack.push(arrayList);

		while (!nodeStack.isEmpty()) {
			TreeNode curNode = nodeStack.pop();
			List<TreeNode> curPath = pathStack.pop();

			if (curNode.children == null || curNode.children.size() <= 0) {
				result.add(curPath);
			} else {
				int childSize = curNode.children.size();
				for (int i = childSize - 1; i >= 0; i--) {
					TreeNode node = curNode.children.get(i);
					nodeStack.push(node);
					List<TreeNode> list = new ArrayList<>(curPath);
					list.add(node);
					pathStack.push(list);
				}
			}
		}
		return result;
	}
}

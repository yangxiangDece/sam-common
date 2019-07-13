package com.yang.datastructures.tree.threadbinarytree;


/**
 * 线索二叉树
 * <p>
 * 一个节点的前一个节点，称为前驱节点
 * 一个节点的后一个节点，称为后继节点
 * <p>
 * 前序、中序、后序遍历中  那些左右子节点为空的 可以指向前驱 后继节点
 * 比如下放的树，前序遍历后为：1 2 4 5 3 6 7  那么 4的前驱为2，4的后继为5，所以可以将4的左节点直接设为2、将4的右节点直接设为5
 * 这样 4的前驱节点就是它的左节点，4的后继节点就是它的有节点，方便遍历查找
 * <p>
 * 根据前中后顺序分为：前序线索二叉树、中序线索二叉树、后序线索二叉树
 */

/**
 * 节点1
 * 节点2        节点3
 * 节点4 节点5   节点6  节点7
 */
public class ThreadBinaryTree {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        TreeNode root = new TreeNode(1, "节点1");
        TreeNode treeNode2 = new TreeNode(2, "节点2");
        TreeNode treeNode3 = new TreeNode(3, "节点3");
        TreeNode treeNode4 = new TreeNode(4, "节点4");
        TreeNode treeNode5 = new TreeNode(5, "节点5");
        TreeNode treeNode6 = new TreeNode(6, "节点6");
        TreeNode treeNode7 = new TreeNode(7, "节点7");

        // 构造二叉树
        /**
         *             节点1
         *       节点2        节点3
         *    节点4 节点5   节点6  节点7
         */
        root.setLeft(treeNode2);
        root.setRight(treeNode3);
        treeNode2.setLeft(treeNode4);
        treeNode2.setRight(treeNode5);
        treeNode3.setLeft(treeNode6);
        treeNode3.setRight(treeNode7);
        binaryTree.setRoot(root);

        System.out.println("前序遍历："); // 1 2 4 5 3 6 7
        binaryTree.preOrder();
        System.out.println("中序遍历："); // 4 2 5 1 6 3 7
        binaryTree.infixOrder();
        System.out.println("后序遍历："); // 4 5 2 6 7 3 1
        binaryTree.postOrder();

        // 中序线索二叉树
        binaryTree.threadNodes();

        System.out.println("5号节点的前驱节点是：" + treeNode5.getLeft());
        System.out.println("5号节点的后继节点是：" + treeNode5.getRight());

        // 遍历线索二叉树  原来的遍历二叉树方法已经无法使用了 必须重新写 线索二叉树的遍历方法
        System.out.println("线索化二叉树的遍历：");
        binaryTree.threadList();
    }
}

class BinaryTree {
    private TreeNode root;
    // 构造线索二叉树，需要创建要给指向当前节点的前驱节点的指针
    // 在递归中，pre总是保留前一个节点
    private TreeNode pre = null;

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void threadNodes() {
        threadNodes(this.root);
    }

    // 将二叉树 构造为 中序线索二叉树
    private void threadNodes(TreeNode node) {
        if (node == null) {
            return;
        }
        // 中序线索二叉树，所以步骤为：
        // 1、先线索化左节点
        threadNodes(node.getLeft());
        // 2、线索化当前节点
        // 处理当前节点的前驱节点

        /**
         *             节点1
         *       节点2        节点3
         *    节点4 节点5   节点6  节点7
         */
        if (node.getLeft() == null) {
            // 让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        // 处理后继节点
        if (pre != null && pre.getRight() == null) {
            // 让前驱节点的指针指向当前节点
            pre.setRight(node);
            pre.setRightType(1);
        }
        // 每处理一个节点后，保存下一个节点的前驱节点，也就是当前节点
        pre = node;

        // 3、再线索化右节点
        threadNodes(node.getRight());
    }

    // 遍历线索化二叉树
    public void threadList() {
        if (root == null) {
            System.out.println("二叉树为空，无法遍历...");
            return;
        }
        // 从根节点开始
        TreeNode node = root;
        while (node != null) {
            // 循环找到leftType==1的节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            // 打印当前节点
            System.out.println(node);
            // 如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                // 获取当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    // 前序遍历 父-左-右
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历...");
        }
    }

    // 中序遍历 左-父-右
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历...");
        }
    }

    // 后续遍历 左-右-父
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历...");
        }
    }

    // 前序遍历查找 父-左-右
    public TreeNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            System.out.println("二叉树为空，无法查找...");
            return null;
        }
    }

    // 中序遍历查找 左-父-右
    public TreeNode infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            System.out.println("二叉树为空，无法查找...");
            return null;
        }
    }

    // 后续遍历查找 左-右-父
    public TreeNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            System.out.println("二叉树为空，无法查找...");
            return null;
        }
    }

    // 删除节点
    public void delNode(int no) {
        if (this.root != null) {
            this.root.delNode(no);
        } else {
            System.out.println("二叉树为空，无法删除...");
        }
    }
}

class TreeNode {
    private int no;
    private String name;
    private TreeNode left;
    private TreeNode right;

    // 如果leftType==0 表示指向的是左子树，如果leftType==1 表示指向前驱节点
    private int leftType;
    // 如果rightType==0 表示指向的是右子树，如果rightType==1 表示指向后继节点
    private int rightType;

    public TreeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 前序遍历 父-左-右
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历 左-父-右
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后续遍历 左-右-父
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    // 前序遍历查找 父-左-右
    public TreeNode preOrderSearch(int no) {
        System.out.println("进入前序遍历查找...");
        if (this.no == no) {
            return this;
        }
        // 如果左节点不为空 继续递归前序遍历查找
        TreeNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            // 在左子树找到
            return resNode;
        }
        // 如果左边未找到 则向右边递归前序遍历查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    // 中序遍历查找 左-父-右
    public TreeNode infixOrderSearch(int no) {
        System.out.println("进入中序遍历查找...");
        // 如果左节点不为空 继续递归中序遍历查找
        TreeNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        // 如果左边未找到，则判断是否是父节点
        if (resNode == null) {
            if (this.no == no) {
                resNode = this;
            }
        }
        // 如果左边和父节点都未找到，则向右递归中序遍历查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    // 后序遍历查找 左-右-父
    public TreeNode postOrderSearch(int no) {
        System.out.println("进入后序遍历查找...");
        TreeNode resNode = null;
        // 如果左节点不为空 继续递归后序遍历查找
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        // 如果左边未找到，则向右递归后序遍历查找
        if (resNode == null && this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        // 如果左边和右边都未找到，则判断是否是父节点
        if (resNode == null && this.no == no) {
            resNode = this;
        }
        return null;
    }

    // 删除节点
    // 因为当前的二叉树是单向的，所以我们是判断当前节点的子节点是否需要删除，而不能判断当前节点是否需要删除。
    // 如果删除了当前节点，按理来说需要将它的父类的左子节点或右子节点设为null，但是当前二叉树是单向，无法完成。
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            // 找到了要删除的节点
            System.out.println("成功删除第" + this.left.no + "节点");
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            // 找到了要删除的节点
            System.out.println("成功删除第" + this.right.no + "节点");
            this.right = null;
            return;
        }
        // 如果当前节点的左右子节点都不是要删除的节点，则开始递归左右节点找到并删除
        // 向左递归查找并删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        // 向右递归查找并删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }
}

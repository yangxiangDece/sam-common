package com.yang.datastructures.tree;

/**
 * 二叉树
 * 1、前 中 后序遍历
 * 2、前 中 后序遍历查找
 * 3、删除节点
 */
public class BinaryTrees {

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

        System.out.println("前序遍历查找节点：" + binaryTree.preOrderSearch(5));
        System.out.println("中序遍历查找节点：" + binaryTree.preOrderSearch(3));
        System.out.println("后序遍历查找节点：" + binaryTree.preOrderSearch(4));

        System.out.println("删除节点：");
        binaryTree.delNode(7);
        System.out.println("删除节点后：");
        binaryTree.preOrder();
    }

    static class BinaryTree {
        private TreeNode root;

        public void setRoot(TreeNode root) {
            this.root = root;
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

    static class TreeNode {
        private int no;
        private String name;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int no, String name) {
            this.no = no;
            this.name = name;
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
}

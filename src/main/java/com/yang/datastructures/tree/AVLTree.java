package com.yang.datastructures.tree;

/**
 * AVL平衡二叉树
 */
public class AVLTree {

    public static void main(String[] args) {
//        int[] arrays = {4, 3, 6, 5, 7, 8}; // 左旋转
//        int[] arrays = {10, 12, 8, 9, 7, 6};// 右旋转
        int[] arrays = {10, 11, 7, 6, 8, 9};// 双旋转
        AVLTreeU tree = new AVLTreeU();
        for (int i = 0; i < arrays.length; i++) {
            tree.add(new AVLNode(arrays[i]));
        }
        System.out.println("中序遍历：");
        tree.infixOrder();

        System.out.println("二叉树的高度：" + tree.getRoot().height());
        System.out.println("二叉树的左子树高度：" + tree.getRoot().leftHeight());
        System.out.println("二叉树的右子树高度：" + tree.getRoot().rightHeight());
    }
}

class AVLTreeU {
    private AVLNode root;

    public void add(AVLNode node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public AVLNode getRoot() {
        return root;
    }

    public AVLNode search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public AVLNode searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉排序树为空，无法遍历...");
            return;
        }
        root.infixOrder();
    }
}

class AVLNode {
    int value;
    AVLNode left;
    AVLNode right;

    public AVLNode(int value) {
        this.value = value;
    }

    // 左旋转
    public void leftRotate() {
        // 创建新的节点，以当前根节点的值
        AVLNode newNode = new AVLNode(value);
        // 把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        // 把新节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        // 把当前节点的值替换成右子节点的值
        value = right.value;
        // 把当前节点的右子树设置成当前节点右子树的右子树
        right = right.right;
        // 把当前节点的左子树设置成新的节点
        left = newNode;
    }

    // 右旋转
    public void rightRotate() {
        // 创建新的节点，以当前根节点的值
        AVLNode newNode = new AVLNode(value);
        // 把新节点的右子树设置成当前节点的右子树
        newNode.right = right;
        // 把新节点的左子树设置成当前节点的左子树的右子树
        newNode.left = left.right;
        // 把当前节点的值替换成左子节点的值
        value = left.value;
        // 把当前节点的左子树设置成当前节点左子树的左子树
        left = left.left;
        // 把当前节点的右子树设置成新的节点
        right = newNode;
    }

    public int leftHeight() {
        if (left != null) {
            return left.height();
        }
        return 0;
    }

    public int rightHeight() {
        if (right != null) {
            return right.height();
        }
        return 0;
    }

    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    public void add(AVLNode node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        // 当添加完一个节点后，如果 右子树的高度 - 左子树的高度 > 1 则左旋转
        if (rightHeight() - leftHeight() > 1) {
            // 如果它的右子树的左子树高度大于右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                // 先对当前节点的右子树右旋转
                right.rightRotate();
                // 再对当前节点左旋转
                leftRotate();
            } else {
                // 直接左旋转
                leftRotate();
            }
        }
        // 当添加完一个节点后，如果 左子树的高度 - 右子树的高度 > 1 则右旋转
        if (leftHeight() - rightHeight() > 1) {
            // 如果它的左子树的右子树高度大于它的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                // 先对当前节点的左子树左旋转
                left.leftRotate();
                // 再对当前节点右旋转
                rightRotate();
            } else {
                // 直接右旋转
                rightRotate();
            }
        }
    }

    public AVLNode search(int value) {
        if (this.value == value) {
            return this;
        }
        if (value < this.value) {
            if (this.left != null) {
                return this.left.search(value);
            }
        } else {
            if (this.right != null) {
                return this.right.search(value);
            }
        }
        return null;
    }

    public AVLNode searchParent(int value) {
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;// 没有找到父节点
            }
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "AVLNode{" +
                "value=" + value +
                '}';
    }
}

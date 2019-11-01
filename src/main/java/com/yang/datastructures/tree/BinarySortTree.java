package com.yang.datastructures.tree;

/**
 * 二叉排序树
 */
public class BinarySortTree {

    public static void main(String[] args) {
        int[] arrays = {7, 3, 10, 12, 5, 1, 9, 0};
        BinarySortTreeU tree = new BinarySortTreeU();
        for (int i = 0; i < arrays.length; i++) {
            tree.add(new BinaryNode(arrays[i]));
        }
        tree.infixOrder();
        tree.delNode(10);
        System.out.println("删除后：");
        tree.infixOrder();
    }

    static class BinarySortTreeU {
        private BinaryNode root;

        public void add(BinaryNode node) {
            if (root == null) {
                root = node;
            } else {
                root.add(node);
            }
        }

        public void delNode(int value) {
            if (root == null) {
                return;
            } else {
                BinaryNode targetNode = search(value);
                if (targetNode == null) {
                    return;
                }
                if (root.left == null && root.right == null) {
                    // 表示只有一个父节点
                    root = null;
                    return;
                }
                BinaryNode parent = searchParent(value);
                if (targetNode.left == null && targetNode.right == null) {
                    // 如果要删除的节点是叶子节点 则判断targetNode 是 parent的 左节点还是右节点
                    if (parent.left != null && parent.left.value == value) {
                        // 是左节点
                        parent.left = null;
                    } else if (parent.right != null && parent.right.value == value) {
                        // 是右节点
                        parent.right = null;
                    }
                } else if (targetNode.left != null && targetNode.right != null) {
                    // 删除的目标节点有两颗子树

                    // 从右边找最小的
                    BinaryNode node = targetNode.right;
                    // 向左边递归查找 一直找到最小值
                    while (node.left != null) {
                        node = node.left;
                    }
                    // 删除最小节点
                    delNode(node.value);
                    targetNode.value = node.value;
                } else {
                    // 删除的目标节点有一颗子树
                    if (targetNode.left != null) {
                        if (parent == null) {
                            // 表示要删除的是根节点
                            root = targetNode.left;
                        } else {
                            // 表示targetNode有左子节点
                            if (parent.left.value == value) {
                                // 表示targetNode是parent的左子节点
                                parent.left = targetNode.left;
                            } else {
                                // 表示targetNode是parent的右子节点
                                parent.right = targetNode.left;
                            }
                        }
                    }
                    if (targetNode.right != null) {
                        if (parent == null) {
                            // 表示要删除的是根节点
                            root = targetNode.right;
                        } else {
                            // 表示targetNode有左子节点
                            if (parent.left.value == value) {
                                // 表示targetNode是parent的左子节点
                                parent.left = targetNode.right;
                            } else {
                                // 表示targetNode是parent的右子节点
                                parent.right = targetNode.right;
                            }
                        }
                    }
                }
            }
        }

        public BinaryNode search(int value) {
            if (root == null) {
                return null;
            } else {
                return root.search(value);
            }
        }

        public BinaryNode searchParent(int value) {
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

    static class BinaryNode {
        int value;
        BinaryNode left;
        BinaryNode right;

        public BinaryNode(int value) {
            this.value = value;
        }

        public void add(BinaryNode node) {
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
        }

        public BinaryNode search(int value) {
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

        public BinaryNode searchParent(int value) {
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
            return "BinaryNode{" +
                    "value=" + value +
                    '}';
        }
    }
}

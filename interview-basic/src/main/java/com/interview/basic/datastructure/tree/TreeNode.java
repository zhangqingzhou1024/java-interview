package com.interview.basic.datastructure.tree;

/**
 * 二叉树代码实现
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-16 00:33
 */
public class TreeNode {
    /**
     * 根节点
     */
    Node rootNode;

    public TreeNode() {
    }

    public TreeNode(int rootVal) {
        this.rootNode = new Node(rootVal, null, null);
    }

    /**
     * 插入节点
     *
     * @param val 值
     */
    private void insert(int val) {
        // 第一个节点
        Node insertNode = new Node(val, null, null);
        if (null == this.rootNode) {
            this.rootNode = insertNode;
        } else {
            // 初始化插入点
            Node currentNode = this.rootNode;
            // 构造循环
            while (true) {
                int nodeVal = currentNode.val;
                // 值小应该添加到左节点
                if (val < nodeVal) {
                    // 如果左节点为空，则直接插入，如果不为空继续迭代
                    if (null == currentNode.leftNode) {
                        currentNode.leftNode = insertNode;
                        return;
                    } else {
                        currentNode = currentNode.leftNode;
                    }

                }
                // 值大的 添加到右边
                else {
                    if (null == currentNode.rightNode) {
                        currentNode.rightNode = insertNode;
                        return;
                    } else {
                        currentNode = currentNode.rightNode;
                    }
                }

            }
        }

    }

    /**
     * 遍历查询
     *
     * @param showType 遍历类型
     */
    public void show(FineTypeEnum showType) {
        this.show(this.rootNode, showType);
    }

    /**
     * 遍历查询
     * 使用递归方法
     *
     * @param treeNode 节点数据
     * @param showType 遍历类型
     */
    private void show(Node treeNode, FineTypeEnum showType) {
        if (null == treeNode) {
            return;
        }
        // 前
        if (FineTypeEnum.PRE == showType) {
            System.out.println(" node value is ->" + treeNode.val);
            show(treeNode.leftNode, showType);
            show(treeNode.rightNode, showType);
        }
        // 后
        else if (FineTypeEnum.BACK == showType) {
            show(treeNode.leftNode, showType);
            show(treeNode.rightNode, showType);
            System.out.println(" node value is ->" + treeNode.val);
        }
        // 中
        else {
            show(treeNode.leftNode, showType);
            System.out.println(" node value is ->" + treeNode.val);
            show(treeNode.rightNode, showType);
        }

    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(6);

        treeNode.insert(4);
        treeNode.insert(3);
        treeNode.insert(14);
        treeNode.insert(8);
        treeNode.insert(10);

        treeNode.show(FineTypeEnum.PRE);
        System.out.println("**************************");
        treeNode.show(FineTypeEnum.MID);
        System.out.println("**************************");
        treeNode.show(FineTypeEnum.BACK);
        System.out.println("**************************");

    }

    /**
     * 遍历类型
     */
    enum FineTypeEnum {
        /**
         * 前序遍历
         */
        PRE,
        /**
         * 中须遍历
         */
        MID,
        /**
         * 后续遍历
         */
        BACK;
    }

    /**
     * 树形节点
     */
    static class Node {
        /**
         * 节点值
         */
        int val;
        /**
         * 左节点
         */
        Node leftNode;
        /**
         * 右节点
         */
        Node rightNode;

        public Node(int val, Node leftNode, Node rightNode) {
            this.val = val;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }
}

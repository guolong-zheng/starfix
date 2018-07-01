package repair.examples;

import repair.checker.Checker;

public class Tree {
    public Tree left;
    public Tree right;

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public void setRight(Tree right) {
        this.right = right;
    }

    public static void main(String[] args) {
        Tree a = new Tree();
        Tree b = new Tree();
        Tree c = new Tree();
        Tree d = new Tree();
        Tree e = new Tree();
        Tree f = new Tree();
        Tree g = new Tree();

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = g;
        c.left = e;
        e.right = f;
        e.left = g;
        e.right = null;
        d.left = null;
        d.right = null;
        f.left = null;
        f.right = null;

        String dataNode = "data Tree { Tree left; Tree right}";
        String pred = "pred tree(x) == x=null || x::Tree<left, right> * tree(left) * tree(right)";
        String state = "pre data == tree(a)";

        Checker.repair(a, dataNode, pred, state, "a");
    }
}

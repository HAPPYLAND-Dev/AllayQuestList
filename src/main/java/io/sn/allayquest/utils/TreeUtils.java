package io.sn.allayquest.utils;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.collection.Tree;

import java.util.stream.Collectors;

public class TreeUtils {

    public static <T> List<T> flatten(List<Tree<T>> trees) {
        List<T> rst = List.of();

        var cvt = trees.map(Tree::traverse).collect(Collectors.toList());
        var maxnm = maxChildrenNum(trees);
        for (int i = 0; i < maxnm; i++) {
            for (Seq<Tree.Node<T>> nodeseq : cvt) {
                try {
                    rst = rst.append(nodeseq.get(i).getValue());
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }
        return rst;
    }

    public static <T> Seq<T> traverse(Tree<T> tree) {
        return tree.traverse(Tree.Order.PRE_ORDER).map(Tree.Node::getValue);
    }

    public static <T> int maxChildrenNum(List<Tree<T>> trees) {
        return trees.map(Tree::nodeCount).foldLeft(0, Math::max);
    }

}

package io.sn.allayquest.utils;

import io.vavr.collection.List;
import io.vavr.collection.Tree;
import org.junit.jupiter.api.Test;

import static io.sn.allayquest.utils.TreeUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeUtilsTest {

    @Test
    void testMaxChildrenNum() {
        List<Tree<Integer>> trees = List.of(
                Tree.of(1,
                        Tree.of(5), Tree.of(6)),
                Tree.of(2,
                        Tree.of(3), Tree.of(4,
                                Tree.of(7), Tree.of(7))));
        assertEquals(5, maxChildrenNum(trees));
    }

    @Test
    void testTraverse() {
        assertEquals(List.of(2, 3, 4, 7, 7), traverse(Tree.of(2,
                Tree.of(3), Tree.of(4,
                        Tree.of(7), Tree.of(7)))));
    }

    @Test
    void testFlatten() {
        List<Tree<Integer>> trees = List.of(
                Tree.of(1,
                        Tree.of(5), Tree.of(6)),
                Tree.of(2,
                        Tree.of(3), Tree.of(4,
                                Tree.of(7), Tree.of(7))));


        assertEquals(List.of(1, 2, 5, 3, 6, 4, 7, 7), flatten(trees));
    }

}
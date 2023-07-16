package io.sn.allayquest.interfaces;

import io.vavr.collection.List;
import io.vavr.collection.Tree;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class QuestQuery {

    public abstract @NotNull List<Tree<Quest>> query(UUID player);

}

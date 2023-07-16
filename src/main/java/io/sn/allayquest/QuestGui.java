package io.sn.allayquest;

import io.sn.allayquest.interfaces.Quest;
import io.sn.allayquest.interfaces.QuestQuery;
import io.sn.allayquest.objects.QuestStatus;
import io.sn.allayquest.utils.TreeUtils;
import io.vavr.collection.List;
import io.vavr.collection.Queue;
import io.vavr.collection.Tree;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class QuestGui {

    private Queue<Quest> queue;
    private final UUID player;
    private final QuestQuery query;

    public QuestGui(UUID player, QuestQuery query) {
        this.queue = Queue.empty();
        this.player = player;
        this.query = query;

        update();
    }

    public void update() {
        @NotNull List<Tree<Quest>> qtree = query.query(getPlayer());

        this.queue = this.queue.enqueueAll(TreeUtils.flatten(qtree));

        while (this.queue.dequeue()._1.getStatus() == QuestStatus.COMPLETED) {
            this.queue = this.queue.dequeue()._2;
        }
    }

    public java.util.List<ItemStack> buildQuestList() {
        return this.queue.map(Quest::getIcon).collect(Collectors.toList());
    }

}

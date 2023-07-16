package io.sn.allayquest.interfaces;

import io.sn.allayquest.objects.QuestStatus;
import org.bukkit.inventory.ItemStack;

public interface Quest {

    ItemStack getIcon();

    QuestStatus getStatus();

}

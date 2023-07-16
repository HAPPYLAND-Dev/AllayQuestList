package io.sn.allayquest.utils;

import io.sn.allayquest.interfaces.Quest;
import io.sn.allayquest.objects.QuestStatus;
import org.bukkit.inventory.ItemStack;

public class QuestFactory {

    public static Quest buildQuest(ItemStack icon, QuestStatus status) {
        return new Quest() {
            @Override
            public ItemStack getIcon() {
                return icon;
            }

            @Override
            public QuestStatus getStatus() {
                return status;
            }

            @Override
            public String toString() {
                return String.format("[%s] %s", getStatus().toString(), getIcon().toString());
            }
        };
    }

}

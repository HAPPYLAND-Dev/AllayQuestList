package io.sn.allayquest;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import io.sn.allayquest.interfaces.Quest;
import io.sn.allayquest.interfaces.QuestQuery;
import io.sn.allayquest.objects.QuestStatus;
import io.sn.allayquest.utils.QuestFactory;
import io.vavr.collection.List;
import io.vavr.collection.Tree;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Getter
class QuestGuiTest {

    private ServerMock server;
    private PluginTester plug;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
        plug = MockBukkit.load(PluginTester.class);
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void onTest() {
        getServer().getLogger().info("Testing ....");

        getServer().addPlayer("Tester1");

        UUID uid = getServer().getPlayer(0).getUniqueId();

        QuestGui gui = new QuestGui(uid, new QuestQuery() {
            @Override
            public @NotNull List<Tree<Quest>> query(UUID player) {
                Tree<Quest> tree_1 = Tree.of(
                        QuestFactory.buildQuest(new ItemStack(Material.STONE), QuestStatus.COMPLETED),
                        Tree.of(QuestFactory.buildQuest(new ItemStack(Material.BLACK_STAINED_GLASS), QuestStatus.NOT_TAKEN)),
                        Tree.of(QuestFactory.buildQuest(new ItemStack(Material.WHITE_STAINED_GLASS), QuestStatus.NOT_TAKEN))
                );
                Tree<Quest> tree_2 = Tree.of(
                        QuestFactory.buildQuest(new ItemStack(Material.DEEPSLATE), QuestStatus.COMPLETED),
                        Tree.of(QuestFactory.buildQuest(new ItemStack(Material.BLUE_STAINED_GLASS), QuestStatus.NOT_TAKEN)),
                        Tree.of(
                                QuestFactory.buildQuest(new ItemStack(Material.LIME_STAINED_GLASS), QuestStatus.NOT_TAKEN),
                                Tree.of(QuestFactory.buildQuest(new ItemStack(Material.ORANGE_STAINED_GLASS), QuestStatus.NOT_TAKEN)),
                                Tree.of(QuestFactory.buildQuest(new ItemStack(Material.CYAN_STAINED_GLASS), QuestStatus.NOT_TAKEN))
                        )
                );

                getServer().getLogger().info("\n\n" + tree_1.draw() + "\n");
                getServer().getLogger().info("\n\n" + tree_2.draw() + "\n");
                return List.of(tree_1, tree_2);
            }
        });

        getServer().getLogger().info(gui.buildQuestList().toString());

        assertEquals(java.util.List.of(
                new ItemStack(Material.BLACK_STAINED_GLASS),
                new ItemStack(Material.BLUE_STAINED_GLASS),
                new ItemStack(Material.WHITE_STAINED_GLASS),
                new ItemStack(Material.LIME_STAINED_GLASS),
                new ItemStack(Material.ORANGE_STAINED_GLASS),
                new ItemStack(Material.CYAN_STAINED_GLASS)
        ), gui.buildQuestList());
    }

}
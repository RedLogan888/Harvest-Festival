package joshie.harvestmoon.buildings.placeable.entities;

import joshie.harvestmoon.buildings.placeable.PlaceableHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

public class PlaceableItemFrame extends PlaceableHanging {
    private ItemStack stack;
    private int rotation;
    private String chestType;

    public PlaceableItemFrame() {
        super(0, 0, 0, 0);
    }

    public PlaceableItemFrame(ItemStack stack, int rotation, int facing, int offsetX, int offsetY, int offsetZ) {
        super(facing, offsetX, offsetY, offsetZ);
        this.stack = stack;
        this.rotation = rotation;
    }

    public PlaceableItemFrame(ItemStack stack, int rotation, int facing, int offsetX, int offsetY, int offsetZ, String chestType) {
        this(stack, rotation, facing, offsetX, offsetY, offsetZ);
        this.chestType = chestType;
    }

    @Override
    public Entity getEntity(World world, int x, int y, int z, boolean n1, boolean n2, boolean swap) {
        int facing = getFacing(n1, n2, swap);
        EntityItemFrame frame = new EntityItemFrame(world, getX(x, facing), y, getZ(z, facing), facing);
        ItemStack loot = null;
        if (chestType != null) {
            try {
                WeightedRandomChestContent chest = (WeightedRandomChestContent) WeightedRandom.getRandomItem(world.rand, ChestGenHooks.getItems(chestType, world.rand));
                while (loot == null) {
                    ItemStack[] stacks = ChestGenHooks.generateStacks(world.rand, chest.theItemId, chest.theMinimumChanceToGenerateItem, chest.theMaximumChanceToGenerateItem);
                    if (stacks != null && stacks.length >= 1) {
                        loot = stacks[0].copy();
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        if (stack != null) loot = stack.copy();
        frame.setDisplayedItem(loot);
        frame.setItemRotation(rotation);
        return frame;
    }

    public String getItemStack(ItemStack stack) {
        if (stack == null) {
            return "null";
        }

        String name = PlaceableHelper.getBestGuessName(stack);
        return "new ItemStack(" + name + ", 1, " + stack.getItemDamage() + ")";
    }

    @Override
    public String getStringFor(Entity e, int x, int y, int z) {
        EntityItemFrame frame = (EntityItemFrame) e;
        return "list.add(new PlaceableItemFrame(" + getItemStack(frame.getDisplayedItem()) + ", " + frame.getRotation() + ", " + frame.hangingDirection + ", " + x + ", " + y + ", " + z + "));";
    }
}

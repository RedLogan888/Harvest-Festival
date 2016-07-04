package joshie.harvest.npc.gift;

import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.crops.HFCrops;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static joshie.harvest.api.npc.gift.IGiftHandler.Quality.*;

public class GiftsYulif extends Gifts {
    @Override
    public Quality getQuality(ItemStack stack) {
        if (stack.getItem() == Items.MELON) {
            return AWESOME;
        }

        if (stack.getItem() == Items.SUGAR || stack.getItem() == Items.QUARTZ || stack.getItem() == Item.getItemFromBlock(Blocks.CAKE)) {
            return GOOD;
        }

        if (HFCrops.CORN.matches(stack) || HFCrops.PINEAPPLE.matches(stack)) {
            return GOOD;
        }

        if (GiftRegistry.is(stack, GiftCategory.CONSTRUCTION)) {
            return Quality.GOOD;
        }

        if (GiftRegistry.is(stack, GiftCategory.FARMING)) {
            return Quality.BAD;
        }

        return DECENT;
    }
}
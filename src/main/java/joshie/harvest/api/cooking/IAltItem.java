package joshie.harvest.api.cooking;

import net.minecraft.item.ItemStack;

/** Implement this on items that you wish to give a different item when placed in a cooking utensil **/
public interface IAltItem {
    /** Return null if this item has no alternative **/
    public ItemStack getAlternativeWhenCooking(ItemStack stack);
}

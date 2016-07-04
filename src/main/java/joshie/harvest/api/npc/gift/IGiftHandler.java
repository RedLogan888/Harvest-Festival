package joshie.harvest.api.npc.gift;

import net.minecraft.item.ItemStack;

/** This is attached to npcs to test for their favourite gifts **/
public interface IGiftHandler {
    /** Return the quality of this gift based on the passed in stack
     *  @param stack    the item
     * @return  the quality of the item in the npcs opinion */
    Quality getQuality(ItemStack stack);

    /** Quality of the gifts **/
    enum Quality {
        AWESOME(800), GOOD(500), DECENT(300), DISLIKE(-500), BAD(-800), TERRIBLE(-5000);

        private final int points;

        Quality(int points) {
            this.points = points;
        }

        public int getRelationPoints() {
            return points;
        }
    }
}

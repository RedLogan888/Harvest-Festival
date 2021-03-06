package joshie.harvest.api.animals;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Extra data for animal **/
public interface AnimalStats<N extends NBTBase> extends INBTSerializable<N> {
    /** Called when the entity joins the world
     *  @param animal   the entity **/
    AnimalStats setEntity(EntityAnimal animal);

    /** Returns the animal the stats are attached to**/
    EntityAnimal getAnimal();

    /** Returns the type this animal is **/
    IAnimalType getType();

    /** Returns products per day,
     * the amount of products this animal can make each day **/
    int getProductsPerDay();

    /** Mark this stat as having produced this many items
     *  @param productsPerDay   the amount to set the produced value to **/
    void setProduced(int productsPerDay);

    /** Returns true if this animal is able to produce a product today **/
    boolean canProduce();

    /** Called whenever the new day ticks over
     * @return true if the animal is still alive, false if it is now dead */
    boolean newDay();

    /** Called every two hours **/
    void onBihourlyTick();

    /** @return the happiness of this animal **/
    int getHappiness();

    /** Affects the happiness of this animal
     *
     * @param happiness the amount to change the happiness by  */
    void affectHappiness(int happiness);

    /** Copy happiness, from adult to child
     *  @param player               a player if they're available
     *  @param parentHappiness      the parent animal current happiness
     *  @param percentage           the percentage of the stats to copy **/
    void copyHappiness(@Nullable EntityPlayer player, int parentHappiness, double percentage);

    /** Perform an action on this animal
     *  @param world    the world object, should never be null
     *  @param stack    the stack performing the action
     *  @param action   the action itself
     *  @return true if it was successfully performed **/
    boolean performAction(@Nonnull World world, @Nullable ItemStack stack, AnimalAction action);

    /** Performs a test on the animal
     *  @param test     the test to perform**/
    boolean performTest(AnimalTest test);

    /** Marks the animal as dead, ready to be removed next turn **/
    void setDead();
}


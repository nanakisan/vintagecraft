package at.tyron.vintagecraft.Interfaces;

import net.minecraft.item.ItemStack;

public interface IItemSmithable extends IItemWorkable {

	public boolean workableOn(int anviltier, ItemStack itemstack, ItemStack itemstackoptional);
	
	
	public ItemStack markOddlyShaped(ItemStack itemstack, boolean flag);
	public boolean isOddlyShaped(ItemStack itemstack);
}

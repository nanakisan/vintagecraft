package at.tyron.vintagecraft.Item;

import at.tyron.vintagecraft.World.BlocksVC;
import at.tyron.vintagecraft.WorldProperties.Terrain.EnumTallGrass;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTallGrassVC extends ItemBlock {

	
	
	public ItemTallGrassVC(Block block) {
		super(block);
        this.setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if (getGrassType(stack) == null) {
			return super.getUnlocalizedName() + ".unknown";
		}
		
		return super.getUnlocalizedName() + "." + getGrassType(stack).getName();
	}
	

	
	public static EnumTallGrass getGrassType(ItemStack itemstack) {
		return EnumTallGrass.fromMeta(itemstack.getItemDamage());
	}
	
	
	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	
	
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        return BlocksVC.topsoil.getBlockColor();
    }

	
}

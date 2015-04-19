package at.tyron.vintagecraft.Block;

import at.tyron.vintagecraft.VintageCraft;
import at.tyron.vintagecraft.WorldProperties.Terrain.EnumRockType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;


// Covers bottom layer of the world

public class BlockUpperMantle extends BlockVC {

	public BlockUpperMantle(Material material) {
		super(material);
		
		this.setDefaultState(this.blockState.getBaseState());
		setCreativeTab(VintageCraft.terrainTab);
	}
	

	public BlockUpperMantle() {
		this(Material.rock);
	}


	
	public int getHarvetLevel(IBlockState state) {
		return 9999;
	}
}

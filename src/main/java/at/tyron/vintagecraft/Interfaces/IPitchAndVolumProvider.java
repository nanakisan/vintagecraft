package at.tyron.vintagecraft.Interfaces;

import net.minecraft.util.BlockPos;

public interface IPitchAndVolumProvider {
	float getPitch();
	float getVolumne();
	
	public boolean isDonePlaying(IPitchAndVolumProvider self);
	
	public BlockPos getPosition();
}

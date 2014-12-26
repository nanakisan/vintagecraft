package at.tyron.vintagecraft.client.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.tyron.vintagecraft.ModInfo;
import at.tyron.vintagecraft.TileEntity.TEOre;
import at.tyron.vintagecraft.World.BlocksVC;
import at.tyron.vintagecraft.WorldProperties.EnumMaterialDeposit;
import at.tyron.vintagecraft.WorldProperties.EnumRockType;
import at.tyron.vintagecraft.block.BlockOreVC;

import com.google.common.primitives.Ints;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.model.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.model.*;
import net.minecraftforge.common.property.IExtendedBlockState;

public class BlockOreVCModel implements IBakedModel, ISmartBlockModel, ISmartItemModel {
	private TextureAtlasSprite base, overlay;
    private boolean hasStateSet = false;
    private final IExtendedBlockState state;

    public BlockOreVCModel(TextureAtlasSprite base, TextureAtlasSprite overlay) {
        this(base, overlay, null);
    }

    public BlockOreVCModel(TextureAtlasSprite base, TextureAtlasSprite overlay, IExtendedBlockState state) {
        this.base = base;
        this.overlay = overlay;
        this.state = state;
    }

    @Override
    public List<BakedQuad> getFaceQuads(EnumFacing side){
        return Collections.emptyList();
    }

    private int[] vertexToInts(float x, float y, float z, int color, TextureAtlasSprite texture, float u, float v) {
        return new int[] {
            Float.floatToRawIntBits(x),
            Float.floatToRawIntBits(y),
            Float.floatToRawIntBits(z),
            color,
            Float.floatToRawIntBits(texture.getInterpolatedU(u)),
            Float.floatToRawIntBits(texture.getInterpolatedV(v)),
            0
        };
    }

    private BakedQuad createSidedBakedQuad(float x1, float x2, float z1, float z2, float y, TextureAtlasSprite texture, int tintIndex, EnumFacing side) {
        Vec3 v1 = rotate(new Vec3(x1 - .5, y - .5, z1 - .5), side).addVector(.5, .5, .5);
        Vec3 v2 = rotate(new Vec3(x1 - .5, y - .5, z2 - .5), side).addVector(.5, .5, .5);
        Vec3 v3 = rotate(new Vec3(x2 - .5, y - .5, z2 - .5), side).addVector(.5, .5, .5);
        Vec3 v4 = rotate(new Vec3(x2 - .5, y - .5, z1 - .5), side).addVector(.5, .5, .5);
        
        return new BakedQuad(Ints.concat(
            vertexToInts((float)v1.xCoord, (float)v1.yCoord, (float)v1.zCoord, -1, texture, 0, 0),
            vertexToInts((float)v2.xCoord, (float)v2.yCoord, (float)v2.zCoord, -1, texture, 0, 16),
            vertexToInts((float)v3.xCoord, (float)v3.yCoord, (float)v3.zCoord, -1, texture, 16, 16),
            vertexToInts((float)v4.xCoord, (float)v4.yCoord, (float)v4.zCoord, -1, texture, 16, 0)
        ), tintIndex, side);
    }

    @Override
    public List<BakedQuad> getGeneralQuads() {
        List<BakedQuad> ret = new ArrayList<BakedQuad>();
        
        for(EnumFacing f : EnumFacing.values()) {
            ret.add(createSidedBakedQuad(0, 1, 0, 1, 1, base, -1, f));
            ret.add(createSidedBakedQuad(0, 1, 0, 1, 1.005f, overlay, -1, f));
        }
        return ret;
    }

    @Override
    public boolean isGui3d() { return true; }

    @Override
    public boolean isAmbientOcclusion() { return true; }

    @Override
    public boolean isBuiltInRenderer() { return false; }

    @Override
    public TextureAtlasSprite getTexture() { return this.base; }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }

    @Override
    public IBakedModel handleBlockState(IBlockState blockstate) {
    	return handleState((IExtendedBlockState)blockstate);
    }

    @Override
    public IBakedModel handleItemState(ItemStack stack) {
        IExtendedBlockState itemstate = ((IExtendedBlockState)BlocksVC.rawore.getDefaultState())
        		.withProperty(BlockOreVC.properties[0], EnumRockType.ANDESITE)
        		.withProperty(BlockOreVC.properties[1], EnumMaterialDeposit.LIMONITE);
        
        return handleState((IExtendedBlockState)itemstate);
    }
    
    
    
    
    public IBakedModel handleState(IExtendedBlockState extendedstate) {
    	EnumRockType rocktype = (EnumRockType)extendedstate.getValue(BlockOreVC.properties[0]);
    	EnumMaterialDeposit oretype = (EnumMaterialDeposit)extendedstate.getValue(BlockOreVC.properties[1]);
    	
    	if (rocktype != null) {
    		base = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(ModInfo.ModID + ":blocks/rock/" + rocktype.getName());
    	}
    	if (oretype != null) {
    		overlay = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(ModInfo.ModID + ":blocks/ore/" + oretype.getName());
    	} 
        
    	
        return new BlockOreVCModel(base, overlay, extendedstate);
    }
    
    
    private static Vec3 rotate(Vec3 vec, EnumFacing side) {
        switch(side) {
            case DOWN:  return new Vec3( vec.xCoord, -vec.yCoord, -vec.zCoord);
            case UP:    return new Vec3( vec.xCoord,  vec.yCoord,  vec.zCoord);
            case NORTH: return new Vec3( vec.xCoord,  vec.zCoord, -vec.yCoord);
            case SOUTH: return new Vec3( vec.xCoord, -vec.zCoord,  vec.yCoord);
            case WEST:  return new Vec3(-vec.yCoord,  vec.xCoord,  vec.zCoord);
            case EAST:  return new Vec3( vec.yCoord, -vec.xCoord,  vec.zCoord);
        }
        return null;
    }

    private static Vec3 revRotate(Vec3 vec, EnumFacing side) {
        switch(side) {
            case DOWN:  return new Vec3( vec.xCoord, -vec.yCoord, -vec.zCoord);
            case UP:    return new Vec3( vec.xCoord,  vec.yCoord,  vec.zCoord);
            case NORTH: return new Vec3( vec.xCoord, -vec.zCoord,  vec.yCoord);
            case SOUTH: return new Vec3( vec.xCoord,  vec.zCoord, -vec.yCoord);
            case WEST:  return new Vec3( vec.yCoord, -vec.xCoord,  vec.zCoord);
            case EAST:  return new Vec3(-vec.yCoord,  vec.xCoord,  vec.zCoord);
        }
        return null;
    }
}
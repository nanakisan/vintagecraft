package at.tyron.vintagecraft.client;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import at.tyron.vintagecraft.CommonProxy;
import at.tyron.vintagecraft.ModInfo;
import at.tyron.vintagecraft.World.BlocksVC;
import at.tyron.vintagecraft.World.ItemsVC;
import at.tyron.vintagecraft.WorldProperties.EnumMaterialDeposit;
import at.tyron.vintagecraft.block.BlockOreVC;
import at.tyron.vintagecraft.block.BlockTopSoil;
import at.tyron.vintagecraft.client.Model.BlockOreVCModel;
import at.tyron.vintagecraft.item.ItemOre;
import at.tyron.vintagecraft.item.ItemStone;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;

public class ClientProxy extends CommonProxy implements IResourceManagerReloadListener {
	private static final ResourceLocation grassColormap = new ResourceLocation("textures/colormap/grass.png");
	
	
	

	
	@Override
	public void registerRenderInformation() {
		
		IReloadableResourceManager IRRM = (IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager();
		IRRM.registerReloadListener(this);	
	}

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
	/*	try {
			BlockGrass.setGrassBiomeColorizer(TextureUtil.readImageData(resourceManager, grassColormap));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        
        Item item = Item.getItemFromBlock(BlocksVC.rawore);
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        if (renderItem != null) {
            renderItem.getItemModelMesher().register(item, new ItemMeshDefinition() {
	            @Override
                public ModelResourceLocation getModelLocation(ItemStack stack) {
                    return BlocksVC.oremodelLocation;
                }
            });
	       
	        
            
            
            renderItem.getItemModelMesher().register(ItemsVC.stone, new ItemMeshDefinition() {
	            @Override
	             public ModelResourceLocation getModelLocation(ItemStack stack) {
	            	 //System.out.println(ModInfo.ModID + ":item/stone/" + ItemStone.getRockType(stack));
	                 return new ModelResourceLocation(ModInfo.ModID + ":stone/" + ItemStone.getRockType(stack), "inventory");
	            	//return new ModelResourceLocation("stone");
	                 
	             }
	
	         });
	        
	        
            renderItem.getItemModelMesher().register(ItemsVC.ore, new ItemMeshDefinition() {
	            @Override
	             public ModelResourceLocation getModelLocation(ItemStack stack) {
	            		//System.out.println(ModInfo.ModID + ":ore/" + ItemOre.getOreType(stack).getName());
	                 return new ModelResourceLocation(ModInfo.ModID + ":ore/" + ItemOre.getOreType(stack).getName(), "inventory");
	             }
	
	         });
        }
        
    }
	
	
	@SubscribeEvent
    public void onModelBakeEvent(ModelBakeEvent event) {
		//System.out.println("bake event!");
        TextureAtlasSprite base = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/slime");
        TextureAtlasSprite overlay = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/redstone_block");
        
        event.modelRegistry.putObject(BlocksVC.oremodelLocation, new BlockOreVCModel(base, overlay));
    }
	
	
	@SubscribeEvent
	public void registerTextures(TextureStitchEvent.Pre event) {
        TextureMap map = event.map;
        for (EnumMaterialDeposit deposit : EnumMaterialDeposit.values()) {
        	if (deposit.block == BlocksVC.rawore) {
        		event.map.registerSprite(new ResourceLocation(ModInfo.ModID + ":blocks/ore/" + deposit.getName()));
        	}
        }
	}
	
	
	
	public boolean isFancyGraphics() {
		return Minecraft.getMinecraft().isFancyGraphicsEnabled();
	}

}
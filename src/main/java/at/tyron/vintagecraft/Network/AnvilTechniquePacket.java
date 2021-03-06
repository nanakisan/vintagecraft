package at.tyron.vintagecraft.Network;

import at.tyron.vintagecraft.Interfaces.IItemHeatable;
import at.tyron.vintagecraft.Interfaces.IItemSmithable;
import at.tyron.vintagecraft.Inventory.ContainerAnvil;
import at.tyron.vintagecraft.World.Crafting.EnumAnvilTechnique;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class AnvilTechniquePacket implements IMessage {
	EnumAnvilTechnique technique;
	int anviltier;
	
	public AnvilTechniquePacket() {
	}
	

	public AnvilTechniquePacket(EnumAnvilTechnique technique, int anviltier) {
		this.technique = technique;
		this.anviltier = anviltier;
	}


	@Override
	public void fromBytes(ByteBuf buf) {
		technique = EnumAnvilTechnique.fromId(buf.readInt());
		anviltier = buf.readInt();
		
	}


	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(technique.getId());
		buf.writeInt(anviltier);
		
	}


    public static class Handler implements IMessageHandler<AnvilTechniquePacket, IMessage> {
		@Override
		public IMessage onMessage(AnvilTechniquePacket message, MessageContext ctx) {
			if (ctx.side == Side.CLIENT) return null;
			
			
			EntityPlayerMP player = ctx.getServerHandler().playerEntity;
			
			if (player.openContainer instanceof ContainerAnvil) {
    			ContainerAnvil container = (ContainerAnvil) player.openContainer;
    			
    			ItemStack itemstack = container.getSlot(0).getStack();
    			if (itemstack == null || !(itemstack.getItem() instanceof IItemSmithable)) {
    				container.detectAndSendChanges();
    				return null;
    			}

    			if (itemstack.getItem() instanceof IItemHeatable) {
    				((IItemHeatable)itemstack.getItem()).updateTemperature(itemstack, player.worldObj);
    			}

    	        IItemSmithable smithable = (IItemSmithable)itemstack.getItem();	        
    	        if (!smithable.workableOn(message.anviltier, itemstack, container.getSlot(1).getStack())) {
    	        	container.detectAndSendChanges();
    	        	return null;
    	        }
    	        
    	        
    	        // Smithing is very Energy intensive
    	        player.addExhaustion(3F); 
    	        
    	        ItemStack hammer = container.getSlot(3).getStack();
    	        
    	        
    	        if (hammer != null) {
    	        	hammer.damageItem(1, player);

    	        	if (hammer.stackSize == 0) {
    	        		container.getSlot(3).putStack(null);
    	        		player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "random.break", 1f, 1f);
    	        	}
    	        }
    	        
    	        
    	        
    	        if (container.teanvil.onAnvilUse(player)) {
    	        	smithable.applyTechnique(itemstack, message.technique);
    	        	container.checkCraftable();	
    	        }
    		}
			
			return null;
		}
    }
    

}

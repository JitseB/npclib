package net.jitse.npclib.nms.v1_15_R1.packets;

import java.util.Collection;

import net.jitse.npclib.api.state.NPCState;
import net.minecraft.server.v1_15_R1.DataWatcher;
import net.minecraft.server.v1_15_R1.DataWatcherObject;
import net.minecraft.server.v1_15_R1.DataWatcherRegistry;
import net.minecraft.server.v1_15_R1.EntityPose;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityMetadata;

public class PacketPlayOutEntityMetadataWrapper {

    public PacketPlayOutEntityMetadata create(Collection<NPCState> activateStates, int entityId) {
        DataWatcher dataWatcher = new DataWatcher(null);
        byte masked = NPCState.getMasked(activateStates);
        
        dataWatcher.register(new DataWatcherObject<EntityPose>(6, DataWatcherRegistry.s), getMaskedPose(activateStates));
        dataWatcher.register(new DataWatcherObject<>(0, DataWatcherRegistry.a), masked);
        
        return new PacketPlayOutEntityMetadata(entityId, dataWatcher, true);
    }
    
    private EntityPose getMaskedPose(Collection<NPCState> states) {
    	if (states.contains(NPCState.CROUCHED))
    		return EntityPose.CROUCHING;
//    	if (states.contains(NPCState.SLEEPING))
//    		return EntityPose.SLEEPING;
    	return EntityPose.STANDING;
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.mixins;

import com.stereowalker.tiered.Tiered;
import com.stereowalker.tiered.api.PotentialAttribute;
import com.stereowalker.unionlib.core.registries.RegistryHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@RegistryHolder(
    registry = Item.class,
    namespace = "tiered"
)
@Mixin(
    value = {Tiered.ItemRegistries.class},
    remap = false
)
public class TieredMixin {
    public TieredMixin() {
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static void reforge(AnvilUpdateEvent event) {
        if (!event.getLeft().isDamaged() && event.getLeft().getTagElement("Tiered") != null) {
            PotentialAttribute reforgedAttribute = (PotentialAttribute)Tiered.ATTRIBUTE_DATA_LOADER.getItemAttributes().get(new ResourceLocation(event.getLeft().getTagElement("Tiered").getString("Tier")));
            if (reforgedAttribute.getReforgeItem() != null) {
                if (event.getRight().getItem().getRegistryName().equals(new ResourceLocation(reforgedAttribute.getReforgeItem()))) {
                    ItemStack copy = event.getLeft().copy();
                    copy.removeTagKey("Tiered");
                    event.setOutput(copy);
                    event.setCost(reforgedAttribute.getReforgeExperienceCost());
                }
            } else {
                Tiered.LOGGER.info(reforgedAttribute.getID() + " cannot be reforged because it either does not provide any reforging info or the info it provides is not complete");
            }
        }

    }
}

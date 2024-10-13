//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star.regirster;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items {
    public static final DeferredRegister<Item> ITEMS;
    public static final RegistryObject<Item> CUSTOM_SHEATH;
    public static final RegistryObject<Item> DEFENSE;

    public Items() {
    }

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "star");
        CUSTOM_SHEATH = ITEMS.register("custom_sheath", () -> {
            return new Item((new Item.Properties()).rarity(Rarity.EPIC));
        });
        DEFENSE = ITEMS.register("defense", () -> {
            return new Item((new Item.Properties()).rarity(Rarity.EPIC));
        });
    }
}

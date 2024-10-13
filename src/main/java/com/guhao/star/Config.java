//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.guhao.star;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

public class Config {
    public static Config INSTANCE = null;
    public static final Map<Item, Float> modelMap = new HashMap();
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> KATANA_ITEM;
    public static final ForgeConfigSpec.BooleanValue SLOW_TIME;
    public static final ForgeConfigSpec SPEC;

    public Config() {
    }

    public static void load() {
        List<? extends String> katanaitem = (List)KATANA_ITEM.get();
        Boolean time = (Boolean)SLOW_TIME.get();
        Iterator var2 = katanaitem.iterator();

        while(var2.hasNext()) {
            String katana = (String)var2.next();
            String[] entry = katana.split(" ");
            Item katanaItem = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation(entry[0]));
            modelMap.put(katanaItem, Float.parseFloat(entry[1]));
        }

    }

    static {
        KATANA_ITEM = BUILDER.comment(new String[]{"items considered as katana", "example: minecraft:apple 0"}).defineListAllowEmpty(Collections.singletonList("katana_items"), ArrayList::new, (obj) -> {
            return true;
        });
        SLOW_TIME = BUILDER.comment("slow time.").define("Multiplayer please turn off", true);
        SPEC = BUILDER.build();
    }
}

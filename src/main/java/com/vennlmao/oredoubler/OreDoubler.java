package com.vennlmao.oredoubler;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class OreDoubler extends JavaPlugin {

    private static OreDoubler instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getCommand("oredoubler").setExecutor(new OreCommand(this));
        getLogger().info("OreDoubler enabled! Author: VennLMAO");
    }

    @Override
    public void onDisable() {
        getLogger().info("OreDoubler disabled.");
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new OreChunkGenerator(this);
    }

    public static OreDoubler getInstance() {
        return instance;
    }
}

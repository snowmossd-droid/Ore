package com.vennlmao.oredoubler;

import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;

public class OreChunkGenerator extends ChunkGenerator {

    private final OreDoubler plugin;

    public OreChunkGenerator(OreDoubler plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        List<BlockPopulator> populators = new ArrayList<>();
        populators.add(new OrePopulator(plugin));
        return populators;
    }
}

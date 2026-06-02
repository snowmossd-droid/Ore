package com.vennlmao.oredoubler;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;
import java.util.Set;

public class OrePopulator extends BlockPopulator {

    private final OreDoubler plugin;

    private static final Set<Material> REPLACEABLE = Set.of(
        Material.STONE, Material.DEEPSLATE, Material.TUFF,
        Material.GRANITE, Material.DIORITE, Material.ANDESITE,
        Material.NETHERRACK, Material.BASALT, Material.BLACKSTONE
    );

    public OrePopulator(OreDoubler plugin) {
        this.plugin = plugin;
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        ConfigurationSection ores = plugin.getConfig().getConfigurationSection("ores");
        if (ores == null) return;

        for (String key : ores.getKeys(false)) {
            ConfigurationSection sec = ores.getConfigurationSection(key);
            if (sec == null || !sec.getBoolean("enabled", true)) continue;

            Material ore = Material.matchMaterial(key);
            if (ore == null) continue;

            int min = sec.getInt("min", 4);
            int max = sec.getInt("max", 8);

            int chunkX = chunk.getX() * 16;
            int chunkZ = chunk.getZ() * 16;
            int minY = world.getMinHeight();
            int maxY = world.getMaxHeight() - 1;

            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int y = minY + random.nextInt(maxY - minY);
            int veinSize = min + (max > min ? random.nextInt(max - min + 1) : 0);

            generateVein(world, random, x, y, z, ore, veinSize);
        }
    }

    private void generateVein(World world, Random random, int ox, int oy, int oz, Material ore, int size) {
        for (int i = 0; i < size; i++) {
            int x = ox + random.nextInt(5) - 2;
            int y = oy + random.nextInt(5) - 2;
            int z = oz + random.nextInt(5) - 2;

            if (y < world.getMinHeight() || y >= world.getMaxHeight()) continue;

            Block block = world.getBlockAt(x, y, z);
            if (REPLACEABLE.contains(block.getType())) {
                Material place = (block.getType() == Material.DEEPSLATE) ? getDeepslateVariant(ore) : ore;
                block.setType(place);
            }
        }
    }

    private Material getDeepslateVariant(Material ore) {
        return switch (ore) {
            case COAL_ORE     -> Material.DEEPSLATE_COAL_ORE;
            case IRON_ORE     -> Material.DEEPSLATE_IRON_ORE;
            case COPPER_ORE   -> Material.DEEPSLATE_COPPER_ORE;
            case GOLD_ORE     -> Material.DEEPSLATE_GOLD_ORE;
            case REDSTONE_ORE -> Material.DEEPSLATE_REDSTONE_ORE;
            case LAPIS_ORE    -> Material.DEEPSLATE_LAPIS_ORE;
            case DIAMOND_ORE  -> Material.DEEPSLATE_DIAMOND_ORE;
            case EMERALD_ORE  -> Material.DEEPSLATE_EMERALD_ORE;
            default           -> ore;
        };
    }
}

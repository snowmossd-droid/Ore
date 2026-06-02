package com.vennlmao.oredoubler;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class OreCommand implements CommandExecutor {

    private final OreDoubler plugin;

    public OreCommand(OreDoubler plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.GOLD + "[OreDoubler] " + ChatColor.YELLOW + "Dùng: /oredoubler reload");
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("oredoubler.admin")) {
                sender.sendMessage(ChatColor.RED + "Bạn không có quyền dùng lệnh này.");
                return true;
            }
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.GOLD + "[OreDoubler] " + ChatColor.GREEN + "Đã tải lại config!");
            sender.sendMessage(ChatColor.YELLOW + "Lưu ý: Xóa world để áp dụng thay đổi gen quặng.");
            return true;
        }
        sender.sendMessage(ChatColor.RED + "Lệnh không hợp lệ. Dùng: /oredoubler reload");
        return true;
    }
}

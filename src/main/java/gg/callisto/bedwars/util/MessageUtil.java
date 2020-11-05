package gg.callisto.bedwars.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class MessageUtil {

    public static String prefix = ChatColor.WHITE + "[" + ChatColor.DARK_AQUA + "Bedwars" + ChatColor.WHITE + "] " + ChatColor.RESET;

    public static void sendMessage(MessageType type, CommandSender sender, String message) {
        replaceMarker(message, "%", ChatColor.WHITE + "", ChatColor.GRAY + "");
        if (type == MessageType.ERROR) {
            sender.sendMessage(prefix + ChatColor.RED + message);
        } else if (type == MessageType.INFO) {
            sender.sendMessage(prefix + ChatColor.GRAY + message);
        } else {
            sender.sendMessage(prefix + ChatColor.RED + message);
        }
    }

    public static void broadcastMessage(MessageType type, String message){
        replaceMarker(message, "%", ChatColor.WHITE + "", ChatColor.GRAY + "");
        if(type == MessageType.ERROR) {
            Bukkit.broadcastMessage(prefix + ChatColor.RED + message);
        } else if(type == MessageType.INFO){
            Bukkit.broadcastMessage(prefix + ChatColor.GRAY + message);
        } else {
            Bukkit.broadcastMessage(prefix + ChatColor.GREEN + message);
        }
    }

    private static String replaceMarker(String subject, String marker, String begin, String end){
        String[] strings = subject.split(" ");

        int i = 0;

        while(i < strings.length){
            String string = strings[i];
            if(string.contains(marker)) {
                string = begin + string + end;
                strings[i] = string;
            }
            i++;
        }

        return String.join(" ", strings);

    }

}

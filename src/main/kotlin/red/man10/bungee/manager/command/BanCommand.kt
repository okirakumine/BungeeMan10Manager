package red.man10.bungee.manager.command

import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.plugin.Command
import red.man10.bungee.manager.Man10BungeePlugin.Companion.plugin

object BanCommand : Command("mban","bungeemanager.ban"){

    override fun execute(sender: CommandSender?, args: Array<out String>?) {

        if (sender==null)return
        if (args==null)return

        plugin.log("ban command")
    }

}
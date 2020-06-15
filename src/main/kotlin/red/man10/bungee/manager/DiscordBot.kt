package red.man10.bungee.manager
import net.dv8tion.jda.api.AccountType
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import sun.plugin2.main.server.Plugin
import javax.security.auth.login.LoginException


class DiscordBot(plugin: Man10BungeePlugin) : ListenerAdapter() {
    var plugin:Man10BungeePlugin? = plugin

    lateinit var jda: JDA
    var token:String? = null

    var guild:Guild? = null;

    var guildID:Long = 0
    var chatChannelID:Long = 0
    var logChannelID:Long = 0
    var systemChannelID:Long = 0
    var notificationChannelID:Long = 0
    var adminChannelID:Long = 0

    var chatChannel:TextChannel? = null
    var systemChannel:TextChannel? = null
    var logChannel:TextChannel? = null
    var notificationChannel:TextChannel? = null
    var adminChannel:TextChannel? = null


    //      チャットチャンネル出力
    fun chat(text:String){
        chatChannel?.sendMessage(text)?.queue()
    }
    //      ログチャンネル出力
    fun log(text:String){
        logChannel?.sendMessage(text)?.queue()
    }
    //      システム出力
    fun system(text:String){
        systemChannel?.sendMessage(text)?.queue()
    }
    //      通知
    fun notification(text:String){
        notificationChannel?.sendMessage(text)?.queue()
    }
    //      Admin用
    fun admin(text:String){
        adminChannel?.sendMessage(text)?.queue()
    }

    init{

    }
    fun shutdown(){
        jda.shutdown()
        plugin?.log("discord shutdown")
    }

    fun setup(){
        plugin?.log("discord setup")

        if(token == null){
            plugin?.error("Discord token is not initialized.")
            return
        }
        try {

            jda = JDABuilder(AccountType.BOT).setToken(token).addEventListeners(this).build()
            jda.awaitReady()

            guild = jda.getGuildById(this.guildID);
            chatChannel = guild?.getTextChannelById(this.chatChannelID)
            logChannel = guild?.getTextChannelById(this.logChannelID)
            systemChannel = guild?.getTextChannelById(this.systemChannelID)
            notificationChannel = guild?.getTextChannelById(this.notificationChannelID)
            adminChannel = guild?.getTextChannelById(this.adminChannelID)

            } catch (e: LoginException) {
                e.printStackTrace()
                plugin?.error(e.localizedMessage)
                return
            }
            plugin?.log("discord setup done!")
        }

        fun checkChannel(channel:TextChannel?){
            if(channel == null){
                plugin?.log("channel null")
                return
            }
            plugin?.log("cantalk:${channel.canTalk()}")

        }

        override fun onReady(event: ReadyEvent) {
            plugin?.log("Discord bot ready")
        }

        override fun onMessageReceived(event: MessageReceivedEvent) {
            val msg = event.message

        }
}
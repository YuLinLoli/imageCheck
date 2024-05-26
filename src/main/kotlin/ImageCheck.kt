package com.yulin


import com.yulin.cg.BuildConfig
import com.yulin.config.Config
import com.yulin.config.ImageCheckApiConfig
import com.yulin.module.HelpModule
import com.yulin.module.ImageCheckModule
import com.yulin.module.OrderModule.orderModule
import kotlinx.coroutines.delay
import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.extension.PluginComponentStorage
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.globalEventChannel



object ImageCheck : KotlinPlugin(
    JvmPluginDescription(
        id = BuildConfig.id,
        name = BuildConfig.name,
        version = BuildConfig.yulinVersion
    ) {
        //可以写依赖的插件
    }
) {
    @OptIn(ConsoleExperimentalApi::class)
    override fun PluginComponentStorage.onLoad() {
        Config.reload()
        Config.save()
        ImageCheckApiConfig.reload()
        ImageCheckApiConfig.save()

        if (Config.master == 0L) {
            logger.error("请关闭Mirai并编辑配置文件完成master的设定！")
            MiraiConsole.shutdown()
        }
    }
    override fun onEnable() {
        Config.reload()
        Config.save()
        globalEventChannel().subscribeAlways<GroupMessageEvent> {
            //设置运行
            orderModule(this)
            //图片检查运行
            ImageCheckModule.imageCheck(this)
            //帮助运行
            HelpModule.help(this)
        }
        globalEventChannel().subscribeAlways<FriendMessageEvent> {
            //设置运行
            orderModule(this)
            //帮助运行
            HelpModule.help(this)
        }


    }


}
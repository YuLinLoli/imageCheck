package com.yulin


import com.yulin.cg.BuildConfig
import com.yulin.config.Config
import com.yulin.module.HelpModule
import com.yulin.module.ImageCheckModule
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
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

    override fun onEnable() {
        Config.reload()
        Config.save()
        globalEventChannel().subscribeAlways<GroupMessageEvent> {
            if (Config.master == 0L) {
                logger.error("请关闭Mirai并编辑配置文件完成设定！")
                return@subscribeAlways
            }
            ImageCheckModule.imageCheck(this)
            HelpModule.help(this)
        }


    }


}
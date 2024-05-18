package com.yulin.util

import com.yulin.ImageCheck.logger
import com.yulin.config.Config
import java.io.*

object CacheUtil {

    /**
     * 判断文件是什么格式
     * file: byteArrayOutputStream
     * return: String
     */
    fun getMimeType(byteArrayOutputStream: ByteArrayOutputStream): String? {
        val bufferedInputStream = BufferedInputStream(ByteArrayInputStream(byteArrayOutputStream.toByteArray()))
        val types: HashMap<String, String> = hashMapOf(
            "ffd8ffe0" to "jpg",
            "89504e47" to "png",
            "47494638" to "gif",
            "49492a00" to "tif",
            "424d8200" to "bmp",
            "41433130" to "dwg",
            "38425053" to "psd",
            "71777741" to "ani",
            "3c21444f" to "html",
            "3c21646f" to "htm",
            "48544d4c" to "hlp",
            "504b0304" to "doc",
            "d0cf11e0" to "xls",
            "5374616E" to "txt",
            "25504446" to "pdf",
            "52617221" to "rar",
            "57415645" to "wav",
            "41564920" to "avi",
            "2e7261fd" to "ram",
            "49443303" to "mp3",
            "000001ba" to "mpg",
            "000001b3" to "mpg",
            "6d6f6f76" to "mov",
            "68746D6C" to "mht",
            "504b0304" to "ppt"
            // 更多文件头部字符串和对应文件类型的映射...
        )

        val bytes = ByteArray(8)
        bufferedInputStream.read(bytes, 0, 8)
        val fileHeader = if (bytes.joinToString("") { "%02x".format(it) }.length > 8) {
            bytes.joinToString("") { "%02x".format(it) }.take(8)
        } else {
            bytes.joinToString("") { "%02x".format(it) }
        }
        bufferedInputStream.close()

        return types[fileHeader]
    }

    /**
     *
     */
    fun saveToLocal(infoStream: ByteArrayOutputStream, imageName: String): String {
        try {
            val directory = File(Config.imageSaveLocal)
            val imageDir = File(directory.path + File.separator)
            if (!imageDir.exists()) {
                imageDir.mkdirs()
            }
            FileOutputStream(imageDir.path + imageName).use {
                it.write(infoStream.toByteArray())
            }
            infoStream.close()
            logger.info("图片缓存成功！")
            return imageDir.path + imageName
        } catch (e: Exception) {
            infoStream.close()
            logger.info("图片缓存失败！")
            logger.warning(e)
        }
        return ""
    }

}
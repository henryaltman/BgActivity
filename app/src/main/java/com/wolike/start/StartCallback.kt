package com.wolike.start

import com.wolike.start.Start


open class StartCallback {
    // 最终结果
    open fun result(result: Int) {}

    // 判断 Activity 是否启动成功
    open fun judgeResult(): Int = Start.START_RESULT_UNKNOWN
}
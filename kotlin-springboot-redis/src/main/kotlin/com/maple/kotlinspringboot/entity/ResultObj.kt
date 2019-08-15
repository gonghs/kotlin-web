package com.maple.kotlinspringboot.entity

/**
 * 控制台返回结果的包装对象
 *
 * @author maple
 * @version 1.0
 * @since 2019-02-24 18:20
 */
class ResultObj<T> private constructor(val data: T?, val success: Boolean, val errorCode: String, val msg: String) {
    /**
     * 默认为成功
     */
    private constructor(data: T?) : this(data, true, GlobalConst.SUCCESS, GlobalConst.DEFAULT_SUCCESS_MSG)

    private constructor(data: T?, errorCode: String, msg: String) : this(data, false, errorCode, msg)

    companion object {

        fun <T> success(t: T): ResultObj<T> = ResultObj(t)

        fun <T> failure(data: T?, errorCode: String, msg: String): ResultObj<T> = ResultObj(data, errorCode, msg)
    }

    override fun toString(): String {
        return "ResultObj(data=$data, success=$success, errorCode='$errorCode', msg='$msg')"
    }

}
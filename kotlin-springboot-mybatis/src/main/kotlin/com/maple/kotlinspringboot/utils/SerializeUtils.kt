package com.maple.kotlinspringboot.utils

import com.caucho.hessian.io.HessianInput
import com.caucho.hessian.io.HessianOutput
import java.io.*

object SerializeUtils{

    @Throws(IOException::class)
    fun hessianDeserialize(by: ByteArray?): Any {
        by?: throw NullPointerException()
        return hessianDeserialize(ByteArrayInputStream(by))
    }

    @Throws(IOException::class)
    fun hessianDeserialize(input:InputStream): Any{
        return HessianInput(input).readObject()
    }

    fun hessianSerialize(obj: Any?): ByteArray {
        obj?: throw NullPointerException()
        try {
            val os = ByteArrayOutputStream()
            val ho = HessianOutput(os)
            ho.writeObject(obj)
            return os.toByteArray()
        } catch (e: Exception) {
            throw e
        }
    }

    @Throws(IOException::class)
    fun hessianSerialize(obj: Any,out:OutputStream){
        HessianOutput(out).writeObject(obj)
    }

    fun javaSerialize(obj: Any?):ByteArray{
        obj?: throw NullPointerException()
        val os = ByteArrayOutputStream()
        val out = ObjectOutputStream(os)
        out.writeObject(obj)
        return os.toByteArray()
    }

    fun javaSerialize(by: ByteArray?):Any{
        by?: throw NullPointerException()
        val `is` = ByteArrayInputStream(by)
        val `in` = ObjectInputStream(`is`)
        return `in`.readObject()
    }

}
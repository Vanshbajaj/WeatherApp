package com.open.data.proto

import androidx.datastore.core.Serializer


import java.io.InputStream
import java.io.OutputStream

object MyDataSerializer : Serializer<MyData> {
    override val defaultValue: MyData = MyData.getDefaultInstance()


    override suspend fun readFrom(input: InputStream): MyData {
        return MyData.parseFrom(input)
    }

    override suspend fun writeTo(t: MyData, output: OutputStream) {
        t.writeTo(output)
    }
}



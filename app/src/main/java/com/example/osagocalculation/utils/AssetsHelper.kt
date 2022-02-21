package com.example.osagocalculation.utils

import android.content.Context
import java.io.InputStream

object AssetsHelper {

    fun readFile(context: Context, fileName: String): InputStream {
        return context.assets.open(fileName)
    }

}

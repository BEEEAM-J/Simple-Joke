package com.beeeam.feature.extension

fun String.dateFormat(): String {
    return this.split(" ")[0]
}
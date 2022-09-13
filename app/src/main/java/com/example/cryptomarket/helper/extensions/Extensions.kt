package com.example.cryptomarket.helper.extensions

fun String.fromSVGtoPNG(): String {
    val png: CharSequence = "png"
    return this.replaceRange(this.length - 3, this.length, png)
}
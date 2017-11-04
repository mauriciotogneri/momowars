package com.mauriciotogneri.momowars.utils

import com.mauriciotogneri.momowars.node.require

object Hash
{
    val shajs = require("sha.js")

    fun sha512(input: String): String
    {
        return "${js("new this.shajs.sha512()").update(input).digest("hex")}"
    }
}
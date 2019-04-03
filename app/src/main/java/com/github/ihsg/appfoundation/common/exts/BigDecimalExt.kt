package com.github.ihsg.appfoundation.common.exts

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

fun BigDecimal.toCurrencyStr(): String = this.setScale(2, RoundingMode.HALF_EVEN).toString()

fun BigDecimal.toCurrencyWithUnitStr(): String = "${this.toCurrencyStr()}元"

fun BigDecimal.toPercentWithFixedStr(fractionDigits: Int): String {
    val f = DecimalFormat()
    f.maximumFractionDigits = fractionDigits
    f.minimumFractionDigits = fractionDigits
    return f.format(this.multiply(BigDecimal(100)))
}

fun BigDecimal.toPercentWithFixedAndUnitStr(fractionDigits: Int) = "${this.toPercentWithFixedStr(fractionDigits)}%"

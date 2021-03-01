package com.abhijith.videoplaybackonrv.sample.util.misc


/**
 *
 */
fun Int.randomUpTo(max : Int) = (this + (Math.random() * (max - this)).toInt())


/**
 *
 */
fun Long.randomUpTo(max : Long) = (this + (Math.random() * (max - this)).toLong())


/**
 *
 */
fun Float.randomUpTo(max : Float) = (this + (Math.random() * (max - this)))


/**
 *
 */
fun Double.randomUpTo(max : Double) = (this + (Math.random() * (max - this)))


/**
 *
 */
fun randomPositiveInt() = 1.randomUpTo(Int.MAX_VALUE)


/**
 *
 */
fun randomInt(includeNegativeNumbers : Boolean = true) = (if(includeNegativeNumbers) Int.MIN_VALUE else 0).randomUpTo(Int.MAX_VALUE)


/**
 *
 */
fun randomPositiveLong() = 1L.randomUpTo(Long.MAX_VALUE)


/**
 *
 */
fun randomLong(includeNegativeNumbers : Boolean = true) = (if(includeNegativeNumbers) Long.MIN_VALUE else 0L).randomUpTo(Long.MAX_VALUE)


/**
 *
 */
fun randomPositiveFloat() = 1.0F.randomUpTo(Float.MAX_VALUE)


/**
 *
 */
fun randomFloat(includeNegativeNumbers : Boolean = true) = (if(includeNegativeNumbers) Float.MIN_VALUE else 0.0F).randomUpTo(Float.MAX_VALUE)


/**
 *
 */
fun randomPositiveDouble() = 1.0.randomUpTo(Double.MAX_VALUE)


/**
 *
 */
fun randomDouble(includeNegativeNumbers : Boolean = true) = (if(includeNegativeNumbers) Double.MIN_VALUE else 0.0).randomUpTo(Double.MAX_VALUE)
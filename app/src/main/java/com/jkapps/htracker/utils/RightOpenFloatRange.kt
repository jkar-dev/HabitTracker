package com.jkapps.htracker.utils

data class RightOpenFloatRange(val from: Float, val to: Float)

infix fun Float.rightOpen(to: Float) = RightOpenFloatRange(this, to)
operator fun RightOpenFloatRange.contains(f: Float) = from <= f && f < to
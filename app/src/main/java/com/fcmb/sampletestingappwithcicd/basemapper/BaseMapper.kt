package com.fcmb.businesszone_mobileapplication.util.basemapper
/**
 * this serves as a contract to convert data class from one
 * type to another*/
interface BaseMapper<From, To> {
    fun map(input: From): To
}

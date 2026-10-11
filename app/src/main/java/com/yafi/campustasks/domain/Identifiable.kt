package com.yafi.campustasks.domain
interface Identifiable { val id: Long }
fun <T : Identifiable> findById(items: List<T>, id: Long): T? =
    items.firstOrNull { it.id == id }

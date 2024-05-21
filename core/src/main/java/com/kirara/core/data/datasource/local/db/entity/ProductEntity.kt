package com.kirara.core.data.datasource.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "table_shop", primaryKeys = ["id"])
data class ProductEntity (
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "price")
    val price: Long? = null,

    @ColumnInfo(name = "thumbnail")
    val thumbnail: String? = null,

    @ColumnInfo(name = "title")
    val title: String? = null
)

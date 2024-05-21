package com.kirara.core.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kirara.core.data.datasource.local.db.dao.ProductDao
import com.kirara.core.data.datasource.local.db.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)

abstract  class AppDatabase : RoomDatabase() {
    abstract  fun ProductDao() : ProductDao
}
package com.kirara.core.data.model.mapper

import com.kirara.core.data.datasource.local.db.entity.ProductEntity
import com.kirara.core.data.model.Product

object ProductMapper {
    fun mapFromProductToEntity(product: Product) =
        ProductEntity(product.id, product.description, product.price, product.thumbnail, product.title)
}
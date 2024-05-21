package com.kirara.core.domain.usecase.product.db

import com.kirara.core.data.datasource.local.db.entity.ProductEntity
import com.kirara.core.domain.repository.product.DbProductRepository
import com.kirara.core.domain.usecase.BaseUseCaseSuspend
import javax.inject.Inject


class InsertProductDbUseCase @Inject constructor(
    private val dbProductRepository: DbProductRepository
) : BaseUseCaseSuspend<ProductEntity, Long>() {
    override suspend fun execute(params: ProductEntity): Long {
        return dbProductRepository.insertProductDb(params)
    }
}
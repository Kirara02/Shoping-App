package com.kirara.core.domain.usecase.product.db

import com.kirara.core.data.datasource.local.db.entity.ProductEntity
import com.kirara.core.domain.repository.product.DbProductRepository
import com.kirara.core.domain.usecase.BaseUseCaseSuspend
import javax.inject.Inject


class DeleteProductDbUseCase @Inject constructor(
    private val dbProductRepository: DbProductRepository
) : BaseUseCaseSuspend<ProductEntity, Int>() {
    override suspend fun execute(params: ProductEntity): Int {
        return dbProductRepository.deleteProductDb(params)
    }
}
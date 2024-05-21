package com.kirara.core.domain.usecase.product.db

import com.kirara.core.data.datasource.local.db.entity.ProductEntity
import com.kirara.core.domain.repository.product.DbProductRepository
import com.kirara.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetProductsDbUseCase @Inject constructor(
    private val dbProductRepository: DbProductRepository
) : BaseUseCase<Unit, Flow<MutableList<ProductEntity>>>() {
    override fun execute(params: Unit): Flow<MutableList<ProductEntity>> {
        return dbProductRepository.getProductsDb()
    }
}
package com.kirara.core.domain.usecase.product

import com.kirara.core.data.datasource.remote.ApiService
import com.kirara.core.data.model.ProductResponse
import com.kirara.core.domain.repository.product.ProductRepository
import com.kirara.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : BaseUseCase<Unit, Flow<ProductResponse>>(){
    override fun execute(params: Unit): Flow<ProductResponse> {
        return productRepository.getProductsApiCall()
    }
}
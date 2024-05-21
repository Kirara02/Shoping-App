package com.kirara.core.domain.usecase.product

import com.kirara.core.data.model.ProductResponse
import com.kirara.core.domain.repository.product.ProductRepository
import com.kirara.core.domain.usecase.BaseUseCase
import com.kirara.core.domain.usecase.BaseUseCaseSuspend
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductUseCase @Inject constructor(
    private  val productRepository: ProductRepository
) : BaseUseCaseSuspend<String, Flow<ProductResponse>>(){
    override suspend fun execute(params: String): Flow<ProductResponse> {
        return productRepository.searchProductApiCall(params)
    }

}
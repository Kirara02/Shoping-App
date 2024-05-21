package com.kirara.core.domain.usecase.product

import com.kirara.core.data.model.Product
import com.kirara.core.domain.repository.product.ProductRepository
import com.kirara.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : BaseUseCase<Int, Flow<Product>>(){
    override fun execute(params: Int) : Flow<Product>{
        return productRepository.getProductByIdApiCall(params)
    }
}
package com.kirara.core.domain.repository.product

import com.kirara.core.data.model.Product
import com.kirara.core.data.model.ProductResponse
import kotlinx.coroutines.flow.Flow

interface  ProductRepository {
    fun getProductsApiCall() : Flow<ProductResponse> // this is sample not using `suspend`
    fun getProductByIdApiCall(id: Int) : Flow<Product>
    suspend fun searchProductApiCall(query: String) : Flow<ProductResponse> // this is sample using `suspend`
}
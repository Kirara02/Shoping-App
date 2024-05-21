package com.kirara.sample.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirara.core.data.UiState
import com.kirara.core.data.datasource.local.db.entity.ProductEntity
import com.kirara.core.data.model.Product
import com.kirara.core.data.model.mapper.ProductMapper
import com.kirara.core.data.model.mapper.ProductMapper.mapFromProductToEntity
import com.kirara.core.domain.usecase.product.GetProductByIdUseCase
import com.kirara.core.domain.usecase.product.db.GetProductByIdDbUseCase
import com.kirara.core.domain.usecase.product.db.InsertProductDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val getProductByIdDbUseCase: GetProductByIdDbUseCase,
    private val insertProductDbUseCase: InsertProductDbUseCase,
) : ViewModel() {
    private val _uiStateProduct: MutableStateFlow<UiState<Product>> = MutableStateFlow(UiState.Loading)
    val uiStateProduct: StateFlow<UiState<Product>> = _uiStateProduct

    private val _uiStateDbProduct: MutableStateFlow<UiState<ProductEntity>> = MutableStateFlow(UiState.Loading)
    val uiStateDbProduct: StateFlow<UiState<ProductEntity>> = _uiStateDbProduct

    fun getProductByIdApiCall(id: Int) {
        getProductByIdUseCase.execute(id).onEach {
            _uiStateProduct.value = UiState.Success(it)
        }.catch { e ->
            _uiStateProduct.value = UiState.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }

    fun getProductByIdDb(id: Long){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                getProductByIdDbUseCase.execute(id).catch {
                    _uiStateDbProduct.value = UiState.Error(it.message.toString())
                }.collect{ product ->
                    _uiStateDbProduct.value = UiState.Success(product)
                }
            }catch (e: Exception){
                _uiStateDbProduct.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun insertProductDb(product: Product){
        viewModelScope.launch {
            val longInsertStatus = insertProductDbUseCase.execute(mapFromProductToEntity(product))
            if(longInsertStatus > 0) getProductByIdDb((product.id ?: -1).toLong())
        }
    }
}
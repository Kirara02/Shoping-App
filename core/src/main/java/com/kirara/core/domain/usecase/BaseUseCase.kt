package com.kirara.core.domain.usecase

abstract class BaseUseCase<in Params, out T>{
    abstract  fun execute(params: Params) : T
}
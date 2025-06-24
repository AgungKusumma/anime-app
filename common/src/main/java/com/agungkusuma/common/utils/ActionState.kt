package com.agungkusuma.common.utils

sealed class ActionState {
    class Success(val data: Any? = null) : ActionState()
    class Error(val data: Any? = null) : ActionState()
    class Loading(val data: Any? = null) : ActionState()
}
package com.vjsarathi.colantask.presentation.listener

interface GenericClickListener<T> {

    fun onClick(item: T?, position: Int? = null)
}
package com.example.transportschedule.koin

import com.example.transportschedule.database.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module { single { DatabaseDriverFactory(get()) } }
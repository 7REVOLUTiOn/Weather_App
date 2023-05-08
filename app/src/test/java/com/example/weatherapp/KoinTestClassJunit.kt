package com.example.weatherapp

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapp.di.repositories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.test.KoinTest
import org.mockito.Mockito

@ExperimentalCoroutinesApi
open class KoinTestClassJunit : KoinTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()//библиотека для тестирования лайвдат
    val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
        startKoin {
            androidContext(Mockito.mock(Application::class.java))
            modules(repositories)
        }
    }



    @After
    fun after() {
        stopKoin()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    fun doWithModule(module: Module, foo: () -> Unit) {
        loadKoinModules(module)
        foo()
        repositories.let {
            unloadKoinModules(it)
            loadKoinModules(it)
        }// запускается модуль и перезаписывается новый модуль, а после отработки все возращается в дефолтное состояние
    }
}
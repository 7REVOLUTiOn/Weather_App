package com.example.weatherapp.data.implementations

import com.example.weatherapp.KoinTestClassJunit
import com.example.weatherapp.data.beans.CityBean
import com.example.weatherapp.data.mock.CityBeanMock
import com.example.weatherapp.data.retrofit.CitiesAPI
import com.example.weatherapp.domain.interfaces.IGetCitiesFromRemoteRepository
import com.example.weatherapp.utils.TRezult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.koin.core.inject
import org.koin.dsl.module


@OptIn(ExperimentalCoroutinesApi::class)
class GetCitiesFromRemoteRepositoryTest : KoinTestClassJunit() {

    @Test
    fun `Тестирование частично битых данных`() {
        val data = CityBeanMock().getSomeInvalid()

        val test = {
            runBlocking {
                val rep by inject<IGetCitiesFromRemoteRepository>()
                val rezult = rep.getCitiesFromRemoteRepository()
                assertEquals(rezult is TRezult.Success, true)
                assertEquals(data.size, 10)
                assertEquals(rezult.data!!.size, 7)
            }
        }

        doWithModule(
            module(override = true) {
                factory<IGetCitiesFromRemoteRepository> {
                    GetCitiesFromRemoteRepositoryImpl(
                        getCitiesFromRemoteRepUseCase = { data }
                    )
                }
            }
        ) {
            test.invoke()
        }

        doWithModule(
            module(override = true) {
                factory<CitiesAPI> {
                    object : CitiesAPI {
                        override suspend fun getCitiesFromGit(): List<CityBean> {
                            return data
                        }
                    }
                }
            }
        ) {
            test.invoke()
        }
    }

}
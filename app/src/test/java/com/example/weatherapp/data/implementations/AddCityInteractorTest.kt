package com.example.weatherapp.data.implementations

import com.example.weatherapp.KoinTestClassJunit
import com.example.weatherapp.data.localRep.LocalRepositoryImpl
import com.example.weatherapp.data.mock.CityBeanMock
import com.example.weatherapp.data.mock.DAOMock
import com.example.weatherapp.domain.interfaces.IAddCityInteractor
import com.example.weatherapp.domain.interfaces.IGetCitiesFromRemoteRepository
import com.example.weatherapp.domain.interfaces.ILocalRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.core.inject
import org.koin.dsl.module


@OptIn(ExperimentalCoroutinesApi::class)
class AddCityInteractorTest:KoinTestClassJunit() {



    @Test
    fun `Тестирование работы addCityInteractor`() {

        val dataFromRemoteRep = CityBeanMock().getValidBeans()

        val test = {
            runBlocking {
                val addCityInteractor by inject<IAddCityInteractor>()
                val result = addCityInteractor.getAndSortSitiesFromRemoteAndLocalRep()
                assertEquals(result.data?.size,10)
            }
        }

        doWithModule(
            module(override = true) {
                factory<IGetCitiesFromRemoteRepository> {
                    GetCitiesFromRemoteRepositoryImpl(
                        getCitiesFromRemoteRepUseCase = { dataFromRemoteRep }
                    )
                }
            }
        ) {

        }

        doWithModule(
            module(override = true) {
                factory<ILocalRepository> {

                    LocalRepositoryImpl(
                        getAllDataFromDatabaseUseCase =  DAOMock::getAllDataFromDatabase,
                        addCityToLocalRepositoryUseCase = DAOMock::addOrUpdateCityToDatabase,
                        deleteDataByCityNameFromDatabaseUseCase = DAOMock::deleteDataByCityNameFromDatabase,
                        updateCityWeatherDataUseCase = DAOMock::addOrUpdateCityToDatabase,
                        getDataByCityNameFromDatabaseUseCase = DAOMock::getDataByCityNameFromDatabase
                    )
                }
            }
        ) {
            test.invoke()
        }
    }
}
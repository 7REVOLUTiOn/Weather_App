package com.example.weatherapp.di

import com.example.weatherapp.data.dataBase.Dao
import com.example.weatherapp.data.dataBase.MainDB
import com.example.weatherapp.data.gpsRep.DataCurrentLocationrRepImpl
import com.example.weatherapp.data.gpsRep.IDataCurrentLocationrRep
import com.example.weatherapp.data.implementations.GetCitiesFromRemoteRepositoryImpl
import com.example.weatherapp.data.implementations.GetWeatherFromRemoteRepositoryImpl
import com.example.weatherapp.data.localRep.LocalRepositoryImpl
import com.example.weatherapp.data.retrofit.CitiesAPI
import com.example.weatherapp.data.retrofit.WeatherAPI
import com.example.weatherapp.domain.entities.CityWeatherEntity
import com.example.weatherapp.domain.implementations.AddCityInteractorImpl
import com.example.weatherapp.domain.implementations.GetWeatherByLocationInteractorImpl
import com.example.weatherapp.domain.implementations.ShowWeatherInteractorImpl
import com.example.weatherapp.domain.interfaces.*
import com.example.weatherapp.presentation.addCityScreen.CityListViewModel
import com.example.weatherapp.presentation.weatherScreen.screens.CurrentLocationViewModel
import com.example.weatherapp.presentation.weatherScreen.screens.ShowWeatherFragmentViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val repositories = module {


    factory<ILocalRepository> {
        val dao = get<Dao>()
        LocalRepositoryImpl(
            getAllDataFromDatabaseUseCase = dao::getAllDataFromDatabase,
            addCityToLocalRepositoryUseCase = dao::addOrUpdateCityToDatabase,
            deleteDataByCityNameFromDatabaseUseCase = dao::deleteDataByCityNameFromDatabase,
            updateCityWeatherDataUseCase = dao::addOrUpdateCityToDatabase,
            getDataByCityNameFromDatabaseUseCase = dao::getDataByCityNameFromDatabase
        )
    }

    factory<IGetCitiesFromRemoteRepository> {
        val api = get<CitiesAPI>()
        GetCitiesFromRemoteRepositoryImpl(
            getCitiesFromRemoteRepUseCase = api::getCitiesFromGit
        )
    }

    factory<IGetWeatherFromRemoteRepository> {
        val api = get<WeatherAPI>()
        GetWeatherFromRemoteRepositoryImpl(
            getWeatherByCoordinatesFromRemoteRepositoryUseCase = api::getWeatherFromYandex
        )
    }


    single<IAddCityInteractor> {
        val remoteRepGetCities = get<IGetCitiesFromRemoteRepository>()
        val localRep = get<ILocalRepository>()
        AddCityInteractorImpl(
            getCitiesFromRemoteRepositoryUseCase = remoteRepGetCities::getCitiesFromRemoteRepository,
            getDataFromLocalRepositoryUseCase = { localRep.getAllCityWeatherEntityFromDb() },
            addCityToFavoriteUseCase = { localRep.addCityToLocalRepository(it) },
            deleteCityFromFavotiteUseCase = { localRep.deleteCityFromLocalRepository(it) }
        )
    }

    single<IGetWeatherByLocationInteractor> {
        val gpsRep = get<IDataCurrentLocationrRep>()
        val getWeather = get<IGetWeatherFromRemoteRepository>()
        GetWeatherByLocationInteractorImpl(
            getWeatherFromRemoteRepository = getWeather::getWeatherFromRemoteRepository,
            startLocalListener = gpsRep::startListener,
            currentLocationAsCityEntity = gpsRep.location,
            stopListenerLocation = gpsRep::closeListener
        )
    }

    factory<IShowWeatherInteractor> {
        val remoteRepWeather = get<IGetWeatherFromRemoteRepository>()
        val localRep = get<ILocalRepository>()
        ShowWeatherInteractorImpl(
            getWeatherFromRemoteRepositoryUseCase = remoteRepWeather::getWeatherFromRemoteRepository,
            updateDatabaseUseCase = localRep::updateCity
        )
    }

    factory<CitiesAPI> { CitiesAPI.getInstance() }
    factory<WeatherAPI> { WeatherAPI.getInstance() }

    single<Dao> {
        MainDB.getDbWeather(application = androidApplication()).getDao()
    }



    factory<IDataCurrentLocationrRep> {
        DataCurrentLocationrRepImpl(
            application = androidApplication()
        )
    }

    viewModel<CityListViewModel> {
        val addCityInteractorImpl = get<IAddCityInteractor>()
        CityListViewModel(
            getCitiesListUseCase = addCityInteractorImpl::getAndSortSitiesFromRemoteAndLocalRep,
            addCityToFavoriteUseCase = addCityInteractorImpl::addLikedCityToLocalRep,
            deleteCityFromFavoriteUseCase = addCityInteractorImpl::deleteCityFromLocalRep,
            favoriteListFlow = addCityInteractorImpl.cityFavoriteList
        )
    }

    viewModel<ShowWeatherFragmentViewModel> { (cityWeatherEntity: CityWeatherEntity) ->
        val showWeatherInteractorImpl = get<IShowWeatherInteractor>()
        val addCityInteractorImpl = get<IAddCityInteractor>()
        ShowWeatherFragmentViewModel(
            cityWeatherEntity = cityWeatherEntity,
            showWeatherUseCase = showWeatherInteractorImpl::getWeather,
            deleteFragment = addCityInteractorImpl::deleteCityFromLocalRep,
        )
    }

    viewModel<CurrentLocationViewModel> {
        val getWeatherByLocationInteractor = get<IGetWeatherByLocationInteractor>()
        CurrentLocationViewModel(
            getWeatherByLocation = getWeatherByLocationInteractor::getWeatherFromCacheOrForce,
        )
    }
}

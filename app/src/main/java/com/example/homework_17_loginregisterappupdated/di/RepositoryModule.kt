package com.example.homework_17_loginregisterappupdated.di

import com.example.homework_17_loginregisterappupdated.data.login.LoginRepositoryImp
import com.example.homework_17_loginregisterappupdated.data.login.LoginService
import com.example.homework_17_loginregisterappupdated.data.register.RegisterRepositoryImp
import com.example.homework_17_loginregisterappupdated.data.register.RegisterService
import com.example.homework_17_loginregisterappupdated.domain.login.LoginRepository
import com.example.homework_17_loginregisterappupdated.domain.register.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideLoginRepository(loginService: LoginService): LoginRepository {
        return LoginRepositoryImp(loginService = loginService)
    }

    @Singleton
    @Provides
    fun provideRegisterRepository(registerService: RegisterService): RegisterRepository {
        return RegisterRepositoryImp(registerService = registerService)
    }
}
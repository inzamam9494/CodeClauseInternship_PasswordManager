package com.example.passwordmanager.di

import android.app.Application
import androidx.room.Room
import com.example.passwordmanager.feature_password.data.data_source.Database
import com.example.passwordmanager.feature_password.data.repository.PasswordRepositoryImpl
import com.example.passwordmanager.feature_password.domain.repository.PasswordRepository
import com.example.passwordmanager.feature_password.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            "PasswordDB"
        ).build()
    }

    @Provides
    @Singleton
    fun providesPasswordRepo(db: Database): PasswordRepository{
        return PasswordRepositoryImpl(db.passwordDao)
    }

    @Provides
    @Singleton
    fun providesUseCases(passwordRepository: PasswordRepository): UseCases{
        return UseCases(
            allPasswords = GetPasswords(passwordRepository),
            getPasswordById = GetPasswordById(passwordRepository),
            addPassword = AddPassword(passwordRepository),
            deletePassword = DeletePassword(passwordRepository)
        )
    }

}
package uz.javokhirdev.moodiary.app.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.javokhirdev.moodiary.data.db.AppDatabase
import uz.javokhirdev.moodiary.data.db.days.DaysDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "days_database")
            .build()
    }

    @Provides
    fun provideDaysDao(appDatabase: AppDatabase): DaysDao {
        return appDatabase.daysDao()
    }
}
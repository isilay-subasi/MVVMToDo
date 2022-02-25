package com.example.todoapp.di

import android.app.Application
import androidx.room.Room
import com.example.todoapp.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /*
    * @InsallIn() Modül oluştururken Hilt'e onun nereye oluşturacağını söylemeliyiz.
    * Yani yukarıdaki kodda hilt'e bu modülün SingletonComponent için olduğunu söylüyorum.
    * SingletonComponent,Bileşen Hiyerarşisinin en üst sınıfıdır.
    * */

    @Provides
    @Singleton
    fun provideDatabase(
        app : Application,
        callback : TaskDatabase.Callback
    )= Room.databaseBuilder(app , TaskDatabase::class.java,"task_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()

    @Provides
    fun provideTaskDao(db : TaskDatabase) = db.taskDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope
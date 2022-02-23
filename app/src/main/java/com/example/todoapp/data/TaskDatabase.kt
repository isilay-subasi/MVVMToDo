package com.example.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoapp.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class] , version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

    class Callback @Inject constructor(
        private val database : Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope : CoroutineScope
    ) : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            //db operations
            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Wash the dishes"))
                dao.insert(Task("hello"))
                dao.insert(Task("you should abstain from",important = true))
                dao.insert(Task("however",completed = true))
                dao.insert(Task("feed"))
                dao.insert(Task("soul",completed = true))
                dao.insert(Task("account for"))
                dao.insert(Task("explained"))
            }

        }
    }
}
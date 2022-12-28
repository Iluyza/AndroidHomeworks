package ru.itis.persikill.androidhomeworks.database

import androidx.room.*
import ru.itis.persikill.androidhomeworks.enteties.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun findAllTasks(): List<Task>

    @Query("SELECT * FROM task WHERE id = :id")
    fun getTaskById(id: Int): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(task: Task)

    @Update
    fun updateTasks(vararg tasks: Task)

    @Delete
    fun deleteTasks(vararg tasks: Task)

    @Query("DELETE FROM task WHERE id = :id")
    fun deleteTaskById(id: Int)

    @Query("DELETE FROM task")
    fun deleteAll()
}

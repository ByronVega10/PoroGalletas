package com.example.porogalletas.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.porogalletas.R
import com.example.porogalletas.data.platillo.PlatilloDao
import com.example.porogalletas.data.platillo.PlatilloEntity
import com.example.porogalletas.data.usuario.UsuarioDao
import com.example.porogalletas.data.usuario.UsuarioEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [UsuarioEntity::class, PlatilloEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun platilloDao(): PlatilloDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "porogalletas_db_v2"
                )
                    .fallbackToDestructiveMigration() // 👈 ESTA LÍNEA
                    .addCallback(DatabaseCallback(context))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback(
        private val context: Context
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            CoroutineScope(Dispatchers.IO).launch {
                val database = getDatabase(context)

                // 👤 Usuarios de prueba
                database.usuarioDao().insertarUsuario(
                    UsuarioEntity(
                        nombre = "Admin",
                        correo = "admin@poro.cl",
                        clave = "123456"
                    )
                )

                database.usuarioDao().insertarUsuario(
                    UsuarioEntity(
                        nombre = "Usuario",
                        correo = "user@poro.cl",
                        clave = "abcdef"
                    )
                )

                // 🍽️ Platillos de prueba
                database.platilloDao().insertarPlatillos(
                    listOf(
                        PlatilloEntity(
                            nombre = "Poro Clásico",
                            ingredientes = "Harina, Queso, Mantequilla",
                            precio = 3500,
                            imagenRes = R.drawable.plato1
                        ),
                        PlatilloEntity(
                            nombre = "Poro Dulce",
                            ingredientes = "Harina, Chocolate, Azúcar",
                            precio = 3000,
                            imagenRes = R.drawable.plato2
                        )
                    )
                )
            }
        }
    }
}
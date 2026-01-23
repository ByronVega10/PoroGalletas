package com.example.porogalletas.data.usuario

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    suspend fun registrar(usuario: UsuarioEntity): Long {
        return usuarioDao.insertarUsuario(usuario)
    }

    suspend fun login(correo: String, clave: String): UsuarioEntity? {
        return usuarioDao.login(correo, clave)
    }

    suspend fun obtenerUsuarios(): List<UsuarioEntity> {
        return usuarioDao.obtenerUsuarios()
    }
}
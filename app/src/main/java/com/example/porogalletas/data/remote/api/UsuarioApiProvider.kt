import com.example.porogalletas.data.remote.api.UsuarioApi

import com.example.porogalletas.data.remote.network.RetrofitClient

object UsuarioApiProvider {
    val api: UsuarioApi by lazy {
        RetrofitClient.retrofit.create(UsuarioApi::class.java)
    }
}
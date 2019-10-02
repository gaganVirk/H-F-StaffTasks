package nz.co.handfchipping.Network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response


class TokenInterceptor(context: Context): Interceptor {

    private val context = context

    override fun intercept(chain: Interceptor.Chain): Response {

        val sharedPreferences = context.getSharedPreferences("hfsystems", Context.MODE_PRIVATE)

        val token = sharedPreferences.getString("token", null)

        return if (token == null) {
            chain.proceed(chain.request())
        } else {
            val authenticatedRequest = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()

            chain.proceed(authenticatedRequest)
        }
    }
}
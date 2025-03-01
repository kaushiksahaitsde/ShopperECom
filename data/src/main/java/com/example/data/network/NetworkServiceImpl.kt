package com.example.data.network

import com.example.data.model.DataProductModel
import com.example.domain.model.Product
import com.example.domain.network.NetworkService
import com.example.domain.network.ResultWrapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import io.ktor.utils.io.errors.IOException

class NetworkServiceImpl(val client: HttpClient):NetworkService{
    override suspend fun getProducts(): ResultWrapper<List<Product>> {
     return makeWebRequest(
         url = "https://fakestoreapi.com/products",
         method = HttpMethod.Get,
         mapper = { dataModels:List<DataProductModel>->
             dataModels.map {
                 it.toProduct()
             }

         }
     )
    }

    @OptIn(InternalAPI::class)
    suspend inline fun <reified T, R> makeWebRequest(
        url: String,
        method: HttpMethod,
        body: Any? = null,
        headers: Map<String, String> = emptyMap(),
        parameters: Map<String, String> = emptyMap(),
        noinline mapper: ((T) -> R)? = null
    ): ResultWrapper<R> {
        return try {
            val response: T = client.request(url) {
                this.method = method // ✅ Matches tutorial method assignment

                // ✅ Apply query parameters exactly as in the tutorial
                url {
                    this.parameters.appendAll(Parameters.build {
                        parameters.forEach { (key, value) ->
                            append(key, value)
                        }
                    })
                }

                // ✅ Apply headers exactly as in the tutorial
                headers.forEach { (key, value) ->
                    header(key, value)
                }

                // ✅ Set body for POST, PUT, etc., as in the tutorial
                if (body != null) {
                    this.setBody(body)
                }

                // ✅ Set content type as in the tutorial
                contentType(ContentType.Application.Json)
            }.body<T>() // ✅ Extract response body as T

            // ✅ Transform response using mapper if provided, otherwise cast response to R
            val result: R = mapper?.invoke(response) ?: response as R

            ResultWrapper.Success(result) // ✅ Returns success result as in the tutorial

        } catch (e: ClientRequestException) {
            ResultWrapper.Failure(e) // ✅ Matches tutorial exception handling
        } catch (e: ServerResponseException) {
            ResultWrapper.Failure(e)
        } catch (e: IOException) {
            ResultWrapper.Failure(e)
        } catch (e: Exception) {
            ResultWrapper.Failure(e)
        }
    }

}





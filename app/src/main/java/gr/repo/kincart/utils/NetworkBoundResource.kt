package gr.repo.kincart.utils

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
        crossinline query: () -> Flow<ResultType>, // responsible for getting the data from database
        crossinline fetch: suspend () -> RequestType, // responsible for getting the data from API
        crossinline  saveFetchResult: suspend (RequestType) -> Unit, // responsible for getting the data from API and saving it to db
        crossinline shouldFetch: (ResultType) -> Boolean = { true } // responsible for getting new data from API or not
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}
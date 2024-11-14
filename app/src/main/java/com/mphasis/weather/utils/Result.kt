package com.mphasis.weather.utils

// A sealed class to represent the result of a network operation or any asynchronous task.
// This class encapsulates three possible states: Loading, Success, and Error,
// making it easier to manage and handle different outcomes in a type-safe manner.

sealed class Result<out T : Any> {

    // Represents a loading state, typically used when an operation is in progress
    // (e.g., when data is being fetched from a remote server or database).
    // This is used to show loading indicators like spinners.
    object Loading : Result<Nothing>()

    // Represents a successful outcome of the operation.
    // Holds the data of type T that represents the successful result of the operation (e.g., an API response).
    // The generic type T ensures that you can return any type of data in case of success.
    data class Success<out T : Any>(val data: T) : Result<T>()

    // Represents an error or failure outcome of the operation.
    // Contains an error message describing the failure (e.g., network errors, API failure).
    // Since this doesn't need to carry any data, the class is parameterized with `Nothing`.
    data class Error(val errorMessage: String) : Result<Nothing>()
}

package com.example.android.databinding.basicsample.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * A simple VM for [com.example.android.databinding.basicsample.ui.PlainOldActivity].
 */
class SimpleViewModel : ViewModel() {
    private val _name = MutableLiveData("Sukma")
    private val _lastName = MutableLiveData("Rizki")
    private val _likes = MutableLiveData(0)

    val name : LiveData<String> = _name
    val lastName : LiveData<String> = _lastName
    var likes : LiveData<Int> = _likes
        private set // This is to prevent external modification of the variable.

    /**
     * Increments the number of likes.
     */
    fun onLike() {
        _likes.value = (_likes.value?: 0) + 1
//        _likes.value = (_likes.value?: 0) - 1
    }

    /**
     * Returns popularity in buckets: [Popularity.NORMAL], [Popularity.POPULAR] or [Popularity.STAR]
     */
    val popularity: LiveData<Popularity> =
        Transformations.map(_likes) {
             when {
                it > 10 -> Popularity.STAR
                it > 5 -> Popularity.POPULAR
                else -> Popularity.NORMAL
            }
        }
}

enum class Popularity {
    NORMAL,
    POPULAR,
    STAR
}

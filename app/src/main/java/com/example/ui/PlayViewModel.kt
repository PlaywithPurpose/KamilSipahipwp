package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.*
import com.example.network.GeminiApiClient
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PlayViewModel(private val repository: PlayRepository) : ViewModel() {

    // Persistent Bookings & Wishlist
    val bookings: StateFlow<List<BookingEntity>> = repository.allBookings
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val wishlist: StateFlow<List<WishlistEntity>> = repository.wishlistItems
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // UI state for Bookings Form
    var activeFormItemName = MutableStateFlow<String?>(null)
    var activeFormType = MutableStateFlow<String>("Workshop") // "Workshop", "Class", "Product"
    var isBookingFormOpen = MutableStateFlow(false)

    // Form inputs
    val childName = MutableStateFlow("")
    val childAge = MutableStateFlow("")
    val parentName = MutableStateFlow("")
    val parentPhone = MutableStateFlow("")
    val notes = MutableStateFlow("")
    val formSuccessMessage = MutableStateFlow<String?>(null)

    // AI Companion State
    val aiPrompt = MutableStateFlow("")
    val aiResponse = MutableStateFlow<String?>(null)
    val isAiLoading = MutableStateFlow(false)

    fun openBookingForm(itemName: String, type: String) {
        activeFormItemName.value = itemName
        activeFormType.value = type
        isBookingFormOpen.value = true
        formSuccessMessage.value = null
    }

    fun closeBookingForm() {
        isBookingFormOpen.value = false
        resetFormInputs()
    }

    private fun resetFormInputs() {
        childName.value = ""
        childAge.value = ""
        parentName.value = ""
        parentPhone.value = ""
        notes.value = ""
    }

    fun submitBooking() {
        val itemNameVal = activeFormItemName.value ?: return
        val typeVal = activeFormType.value
        val nameVal = childName.value.trim()
        val ageVal = childAge.value.toIntOrNull() ?: 6
        val parentNameVal = parentName.value.trim()
        val phoneVal = parentPhone.value.trim()
        val notesVal = notes.value.trim()

        if (nameVal.isEmpty() || parentNameVal.isEmpty() || phoneVal.isEmpty()) {
            formSuccessMessage.value = "Please fill in all required fields (Child, Parent, Phone)."
            return
        }

        viewModelScope.launch {
            val booking = BookingEntity(
                type = typeVal,
                itemName = itemNameVal,
                childName = nameVal,
                childAge = ageVal,
                parentName = parentNameVal,
                parentPhone = phoneVal,
                notes = notesVal,
                isOrder = (typeVal == "Product")
            )
            repository.insertBooking(booking)
            formSuccessMessage.value = "Success! Your inquiry has been logged locally."
            resetFormInputs()
            // Wait shortly, then close the dialog automatically!
            kotlinx.coroutines.delay(1800)
            isBookingFormOpen.value = false
            formSuccessMessage.value = null
        }
    }

    fun deleteBooking(id: Int) {
        viewModelScope.launch {
            repository.deleteBooking(id)
        }
    }

    fun toggleWishlist(product: Product) {
        viewModelScope.launch {
            val currentWishlist = wishlist.value
            val exists = currentWishlist.any { it.itemName == product.title }
            if (exists) {
                repository.removeWishlistByName(product.title)
            } else {
                repository.addWishlist(
                    WishlistEntity(
                        itemName = product.title,
                        itemType = "Product",
                        price = product.price
                    )
                )
            }
        }
    }

    fun checkWishlisted(itemName: String): Flow<Boolean> {
        return repository.isWishlisted(itemName)
    }

    // AI interaction
    fun askAi(customPrompt: String? = null) {
        val finalPrompt = customPrompt ?: aiPrompt.value.trim()
        if (finalPrompt.isEmpty()) return

        viewModelScope.launch {
            isAiLoading.value = true
            aiResponse.value = "Connecting to the Artisanal Craft AI Companion... Let's weave a spark of creativity!"
            val responseText = GeminiApiClient.generateContent(
                "You are an inspiring, warm, creative AI crafting teacher for " +
                "'Kamil Sipahi - Play with Purpose', an academy offering Islamically Integrated art " +
                "workshops and woodcraft. You are talking to a parent or student. Explain ideas steps in " +
                "a beautiful, simple, positive manner with focus on hands-on play and intentional patience. " +
                "Keep the tone encouraging, and use direct, structured instructions.\n\n" +
                "Request: $finalPrompt"
            )
            aiResponse.value = responseText
            isAiLoading.value = false
        }
    }

    fun resetAi() {
        aiPrompt.value = ""
        aiResponse.value = null
        isAiLoading.value = false
    }
}

class PlayViewModelFactory(private val repository: PlayRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlayViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

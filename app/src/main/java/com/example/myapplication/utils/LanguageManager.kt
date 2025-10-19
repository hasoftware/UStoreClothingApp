package com.example.myapplication.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import java.util.Locale

object LanguageManager {
    private const val PREFS_NAME = "language_prefs"
    private const val KEY_LANGUAGE = "selected_language"
    
    private var _currentLanguage by mutableStateOf("en")
    val currentLanguage: String get() = _currentLanguage
    
    private var prefs: SharedPreferences? = null
    
    fun initialize(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        _currentLanguage = prefs?.getString(KEY_LANGUAGE, getSystemLanguage()) ?: "en"
    }
    
    fun setLanguage(language: String) {
        _currentLanguage = language
        prefs?.edit()?.putString(KEY_LANGUAGE, language)?.apply()
    }
    
    private fun getSystemLanguage(): String {
        return Locale.getDefault().language.takeIf { it in listOf("en", "vi", "fr", "ru") } ?: "en"
    }
    
    fun getLocale(): Locale {
        return when (_currentLanguage) {
            "vi" -> Locale("vi", "VN")
            "en" -> Locale("en", "US")
            "fr" -> Locale("fr", "FR")
            "ru" -> Locale("ru", "RU")
            else -> Locale("en", "US")
        }
    }
    
    fun applyLanguage(context: Context): Context {
        val locale = getLocale()
        Locale.setDefault(locale)
        
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        
        return context.createConfigurationContext(config)
    }
    
    fun getLanguageDisplayName(languageCode: String): String {
        return when (languageCode) {
            "en" -> "English"
            "vi" -> "Tiếng Việt"
            "fr" -> "Français"
            "ru" -> "Русский"
            else -> "English"
        }
    }
    
    fun getLanguageNativeName(languageCode: String): String {
        return when (languageCode) {
            "en" -> "English"
            "vi" -> "Tiếng Việt"
            "fr" -> "Français"
            "ru" -> "Русский"
            else -> "English"
        }
    }
    
    fun getCurrencyDisplayName(currencyCode: String): String {
        return when (currencyCode) {
            "USD" -> "$ USD"
            "VND" -> "₫ VND"
            "EUR" -> "€ EURO"
            "RUB" -> "P RUB"
            else -> "$ USD"
        }
    }
    
    val supportedLanguages = listOf("en", "vi", "fr", "ru")
    val supportedCurrencies = listOf("USD", "VND", "EUR", "RUB")
}

package com.example.myapplication.intent

sealed class MainIntent {
    object GetNews:MainIntent()
    object Refresh:MainIntent()
}
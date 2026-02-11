package com.example.examen_android_jesusmarquezruiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.examen_android_jesusmarquezruiz.plantilla.PlantillaApp
import com.example.examen_android_jesusmarquezruiz.ui.theme.Examen_android_JesusMarquezRuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Examen_android_JesusMarquezRuizTheme {
                PlantillaApp()
                }
            }
        }
    }
}


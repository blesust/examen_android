package com.example.examen_android_jesusmarquezruiz

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.examen_android_jesusmarquezruiz", appContext.packageName)
    }
}

/*GitHub
# Inicializa el repositorio local
git init

# Agrega todos los archivos
git add .

# Crea tu primer commit (esta será la única versión visible)
git commit -m "Entrega Examen Android"

git remote add origin https://github.com/blesust/examen_android.git

git push -u origin main / si diera problemas usar este comando git push -u origin main --force

para modificaciones
 git add .

git commit --amend --no-edit

git push -f origin main
*/
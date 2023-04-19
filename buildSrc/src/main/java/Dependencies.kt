object Dependencies {
    val uiComponents = listOf(
        "androidx.core:core-ktx:1.9.0",
        "androidx.appcompat:appcompat:1.6.1",
        "com.google.android.material:material:1.8.0",
        "androidx.constraintlayout:constraintlayout:2.1.4"
    )

    object UIComponents {
        const val core = "androidx.core:core-ktx:1.9.0"
        const val appCompat = "androidx.appcompat:appcompat:1.6.1"
        const val material = "com.google.android.material:material:1.8.0"
        const val constraint = "androidx.constraintlayout:constraintlayout:2.1.4"
    }

    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gsonConvertor = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object Coroutines {
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
    }

    object Inject {
        const val inject = "javax.inject:javax.inject:1"
    }
}
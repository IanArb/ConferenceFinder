# Conferences Finder 

An example usage of kotlin multiplatform with Android, iOS and Web supported by a Ktor backend. 

The applications displays a list of conferences retrieved from the backend.

Inspired by [John Reilly's](https://github.com/joreilly) starter pack (https://github.com/joreilly/PeopleInSpace)

This repository accepts pull requests and all contributions are welcome!

## Setup instructions
This project can be used with IntelliJ Ultimate edition, Android Studio 4+ and XCode v11.3+ for iOS.

### Android
You can simply run the Android sample app by selecting app with your Run/Debug configurations deployed to emulator or physical device.

### iOS
You will need XCode v11.3+ installed and will need to target iOS 13. SwiftUI only supports iOS 13 and above.

### Web
You can simply run the following command to execute the Kotlin react/js app on localhost port 8080.
```gradle
./gradlew :web:browserDevelopmentRun --continuous
```
--continuous flag enables react hot reload for code changes. 

### Ktor
You need to add Run/Debug configuration to Edit Configurations targetting main class ```com.ianarbuckle.conferencesapi.ApplicationKt```

You can configure your port in the ``application.conf`` file 

```
ktor {
    deployment {
        port = 8080
    }
    application {
        modules = [ com.ianarbuckle.conferencesapi.ApplicationKt.module ]
    }
}
```

#### Languages, libraries and tools used
* [Kotlin](https://kotlinlang.org/)
* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization)
* [Ktor client library](https://github.com/ktorio/ktor)
* [Kotlin JS Wrappers](https://github.com/JetBrains/kotlin-wrappers)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
* [KMongo](https://github.com/Litote/kmongo)
* [Kubernetes](https://kubernetes.io/)
* [SwiftUI](https://developer.apple.com/documentation/swiftui)
* [Bootstrap](https://getbootstrap.com/)
* [MongoDB Atlas](https://www.mongodb.com/cloud/atlas)

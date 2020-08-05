# Conferences Finder 

An example usage of kotlin multiplatform with Android, iOS and Web supported by a Ktor backend. 

The applications displays a list of conferences retrieved from the backend (https://github.com/IanArb/conferencesKtor). 

Inspired by [John Reilly's](https://github.com/joreilly) starter pack (https://github.com/joreilly/PeopleInSpace)

This repository accepts pull requests and all contributions are welcome!

## Setup instructions
This project can be used with IntelliJ Ultimate edition, Android Studio 4+ and XCode v11.3+ for iOS.

### Android
You can run the Android sample app with your Run/Debug configurations deployed to emulator or physical device.

To enable the google maps view you'll need to add your own google maps API key in your local.properties file in the root of your project as follows:

```gradle
sdk.dir= YOUR_SDK_DIR
google.map.key = YOUR_API_KEY
```

Instructions to get an API key can be found [here](https://developers.google.com/maps/documentation/android-sdk/get-api-key)

Then re-sync the gradle project and build the project.

```gradle
./gradlew build
```

### iOS
You will need Xcode v11.3+ installed and will need to target iOS 13. SwiftUI only supports iOS 13 and above.

In Xcode open the iOSApp xcworkspace and build the app. The common library will be imported as a framework. 

### Web
You can simply run the following command to execute the Kotlin react/js app on localhost port 8080.
```gradle
./gradlew :web:browserDevelopmentRun --continuous
```
--continuous flag enables react hot reload for code changes. 

You can either setup your own MongoDB host or use a cloud host [MongoDB Altas](https://www.mongodb.com/cloud/atlas)

Once you get your URI you can export it on your local machine or via IntelliJ in your run configurations

```
export MONGO_URI=YOUR_URI_HERE
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

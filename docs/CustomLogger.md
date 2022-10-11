Custom Loggers
==============

To define a custom logger, subclass
AnalyticsLogger and override
```kotlin
fun logEvent(name: String, extras: Map<String, Any>)
```

Text Only Logging
-----------------

If you plan on logging everything as text, 
you can instead subclass TextLogger and override
```kotlin
fun logText(text: String)
```

This allows the text format to be defined 
in the library and standardized across 
implementations.

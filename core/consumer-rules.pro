##--------------- SQLCipher ---------------
# Keep all SQLCipher classes and interfaces, and suppress warnings.
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }
-keep class net.sqlcipher.** { *; }
-dontwarn net.sqlcipher.**

##--------------- Room ---------------
# Keep Room database, entities, converters, and annotated methods.
-keep class * extends androidx.room.RoomDatabase { *; }
-keep class * extends androidx.room.Entity { *; }
-keep class * extends androidx.room.TypeConverter { *; }
-keepclassmembers class * {
    @androidx.room.* <methods>;
}

##--------------- Gson ---------------
# Preserve generic type info and annotations required by Gson.
-keepattributes Signature
-keepattributes *Annotation*

# Keep Gson reflective types and custom adapters.
-keep class * extends com.google.gson.reflect.TypeToken { *; }
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Ensure serialized fields with @SerializedName are retained.
-keepclassmembers,allowobfuscation class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Keep all response model classes.
-keep class com.agungkusuma.core.data.source.remote.response.** { *; }

##--------------- Retrofit ---------------
# Preserve necessary annotations and generic signatures.
-keepattributes Signature, InnerClasses, EnclosingMethod, RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep all methods inside Retrofit interfaces annotated with HTTP methods.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Keep Retrofit and OkHttp classes and interfaces.
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

# Prevent Retrofit interfaces from being stripped when using proxies.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Suppress irrelevant warnings from external libs.
-dontwarn javax.annotation.**
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn kotlin.Unit
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*
-dontwarn kotlinx.**

##--------------- Retrofit + Coroutine Fix ---------------
# Keep Retrofit Call and Response for generic support with Kotlin coroutines.
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

##--------------- Glide ---------------
# Keep Glide modules and internal rewinder class used during image loading.
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
    <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
    *** rewind();
}

##--------------- Entity & DAO (Room) ---------------
# Keep local database entities and DAO interfaces.
-keep class com.agungkusuma.core.data.source.local.entity.** { *; }
-keep interface com.agungkusuma.core.data.source.local.room.** { *; }
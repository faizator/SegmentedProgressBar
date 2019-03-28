# SegmentedProgressBar
Simple SegmentedProgressBar widget for Android, written in Kotlin

![](/gifs/progressBar.gif)

### Add library

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

```groovy
dependencies {
    ...
    implementation 'com.github.faizator:SegmentedProgressBar:1.0'
}
```

### Declare in layout

```xml
<com.faz.widgets.segmentedprogressbarlibrary.SegmentedProgressBar
    android:id="@+id/progressBar"
    android:layout_width="match_parent"
    android:layout_height="5dp"
    app:maxValue="10"
    app:delimiterSize="2dp"
    app:activeColor="#00aec5"
    app:inactiveColor="#eaeaea"
    app:delimiterColor="#ffffff" />
```

### Use in code

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    progressBar.curValue = 4
}
```

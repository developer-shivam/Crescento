Crescento
=========

Android library that adds a curve at the below of image views and relative layouts. **CrescentoImageView** and **CrescentoContainer** are the image view and relative layout respectively. You can change the radius of curve with **attribute:curvature**.

![Sample Image 1][SampleOne] ![Sample Image 2][SampleTwo]

Overview
--------

**Crescento** provides following advantages:

* **Curve Bottom**: It adds a curve bottom as stated above. 
* **Tint**: It add tint on **CrescentoImageView** by using **attribute:tintColor**. It pick color automatically from the image if **tintMode** is set to **automatic**.
* **Alpha**: Add transparency in tint by using **attribute:tintAlpha**. Varies from 0 to 255.

Gradle Integration
------------------

If you are using gradle then add these lines in build.gradle file at project level.
```java
	allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
```

Add below lines in build.gradle at app level.
```java
	compile 'com.github.developer-shivam:crescento:1.0.0'
```

Basic Usage
-----------

*For a working implementation, see `/app` folder*

**CrescentoImageView**

```xml
<developer.shivam.library.CrescentoImageView android:id="@+id/crescentoImageView"
    android:layout_width="match_parent"
    android:layout_height="300dp"
    android:elevation="20dp"
    android:scaleType="centerCrop"
    attribute:curvature="50dp"
    attribute:tintMode="manual"
    attribute:tintColor="#FFFFFF"
    attribute:tintAlpha="50" />
```

**CrescentoContainer**

```xml
<developer.shivam.library.CrescentoContainer android:id="@+id/crescentoContainer"
    android:layout_width="match_parent"
    android:layout_height="300dp"
    android:elevation="20dp"
    attribute:curvature="50dp">

    <!-- Your code here -->

</developer.shivam.library.CrescentoContainer>
```

Question / Contact Me
---------------------
Please feel free to ping me at **dr.droid27@gmail.com**

[SampleOne]:   /art/sample1_resize.png
[SampleTwo]:   /art/sample3_resize.png

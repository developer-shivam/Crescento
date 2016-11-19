Crescento
=========

Android library that adds a curve at the below of image views and relative layouts. **CrescentoImageView** and **CrescentoContainer** are the image view and relative layout respectively. You can change the radius of curve with **attribute:curvature**.	

![Sample Image 2][SampleOneNexus]

Overview
--------

**Crescento** provides following advantages:

* **Curve Bottom**: It adds a curve bottom as stated above. 
* **Tint**: It add tint on **CrescentoImageView** by using **attribute:tintColor**. It pick color automatically from the image if **tintMode** is set to **automatic**.
* **Alpha**: Add transparency in tint by using **attribute:tintAlpha**. Varies from 0 to 255.

Use with **[KenBurnsView]**
---------------------------------------

![Sample Image 1][GifSample]

```java
<developer.shivam.library.CrescentoContainer android:id="@+id/crescentoContainer"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:elevation="20dp"
        android:scaleType="centerCrop"
        attribute:curvature="50dp">

        <com.flaviofaria.kenburnsview.KenBurnsView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:src="@drawable/wallpaper2" />
</developer.shivam.library.CrescentoContainer>
```

Donations
---------

This project needs you! If you would like to support this project's further development, the creator of this project or the continuous maintenance of this project, feel free to donate. Your donation is highly appreciated (and I love food, coffee and beer). Thank you!

**PayPal**

* **[Donate $5]**: Thank's for creating this project, here's a coffee (or some beer) for you!
* **[Donate $10]**: Wow, I am stunned. Let me take you to the movies!
* **[Donate $15]**: I really appreciate your work, let's grab some lunch!
* **[Donate $25]**: That's some awesome stuff you did right there, dinner is on me!
* **[Donate $50]**: I really really want to support this project, great job!
* **[Donate $100]**: You are the man! This project saved me hours (if not days) of struggle and hard work, simply awesome!
* **[Donate $2799]**: Go buddy, buy Macbook Pro for yourself!
Of course, you can also choose what you want to donate, all donations are awesome!

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

Attributes
----------

* **curvature**: To change the size of curve.
* **tintColor**: To add tint on image view.
* **tintMode**: To add tint **manually** or **automatically**. If **automatically** it will pick color from image you have set.
* **tintAlpha**: To set the amount of tint. 0 for 100% transparent and 255 for opaque.

Question / Contact Me / Hire Me
---------------------
Please feel free to ping me at **dr.droid27@gmail.com**. Expected package would be 6 lpa.

[SampleOne]:		 	/art/sample1_resize.png
[SampleOneNexus]: 	 	/art/sample1_nexus4_angle1.png
[GifSample]:         	/art/sample2.gif
[GifSample2]:		 	/art/sample3.gif
[Donate $5]: 	 	https://www.paypal.me/developerShivam/5
[Donate $10]:  	https://www.paypal.me/developerShivam/10
[Donate $15]:  	https://www.paypal.me/developerShivam/15
[Donate $25]:  	https://www.paypal.me/developerShivam/25
[Donate $50]: 	https://www.paypal.me/developerShivam/50
[Donate $100]: 	https://www.paypal.me/developerShivam/100
[Donate $2799]: https://www.paypal.me/developerShivam/2799
[KenBurnsView]:			https://github.com/flavioarfaria/KenBurnsView

<p align="center">
<img alt="EditDrawableText" src="https://github.com/MindorksOpenSource/EditDrawableText/blob/master/app/src/main/assets/EditDrawableText.png?raw=true" />
</p>

# EditDrawableText - An EditText which makes your Drawable Clickable
[![Mindorks](https://img.shields.io/badge/mindorks-opensource-blue.svg)](https://mindorks.com/open-source-projects)
[![Mindorks Community](https://img.shields.io/badge/join-community-blue.svg)](https://mindorks.com/join-community)
[![Mindorks Android Store](https://img.shields.io/badge/Mindorks%20Android%20Store-EditDrawableText-blue.svg?style=flat)](https://mindorks.com/android/store)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Preview of EditDrawableText
<img src="https://github.com/MindorksOpenSource/EditDrawableText/blob/master/app/src/main/assets/sample1.jpg?raw=true" height="300em" />&nbsp;<img src="https://github.com/MindorksOpenSource/EditDrawableText/blob/master/app/src/main/assets/sample2.jpg?raw=true" height="300em" />&nbsp;<img src="https://github.com/pranaypatel512/EditDrawableText/blob/development/app/src/main/assets/sample3.png?raw=true" height="300em" />&nbsp;<img src="https://github.com/pranaypatel512/EditDrawableText/blob/development/app/src/main/assets/sample4.png?raw=true" height="300em" />


### Overview of EditDrawableText library
* EditDrawableText can be used to Show/Hide Password
* Left/Right Drawables can be clicked to make custom events like Request OTP etc.
* All type of EditText Properties are possible in EditDrawableText


## Using EditDrawableText Library in your Android application

1. Add it in your root build.gradle at the end of repositories:

```groovy
    repositories {
      maven { url 'https://jitpack.io' }
    }
```
2. Add this in your app's build.gradle

```groovy
	 implementation 'com.github.MindorksOpenSource:EditDrawableText:1.2.0'
```
3. To use this in XML File, use 

```XML
  <com.mindorks.editdrawabletext.EditDrawableText
        android:id="@+id/drawableEditTextLeft"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:drawableLeft="@drawable/ic_remove_red_eye_black_24dp"
        android:hint="Click the Drawables"
        android:inputType="text"
        android:textAlignment="center"
        />
```
4. Make the drawable clickable in Activity file,
```kotlin
  drawableEditText.setDrawableClickListener(object : OnDrawableClickListener {
            override fun onClick(target: DrawablePosition) {
                when (target) {
                    DrawablePosition.RIGHT -> //YOUR_LOGIC
                    DrawablePosition.LEFT -> //YOUR_LOGIC
                    DrawablePosition.TOP -> //YOUR_LOGIC
                    DrawablePosition.BOTTOM -> //YOUR_LOGIC
                }
            }      
    })

```
### TODO
* More features related to EditText

## If this library helps you in anyway, show your love :heart: by putting a :star: on this project :v:

[Check out Mindorks awesome open source projects here](https://mindorks.com/open-source-projects)


#### Contributor
[Himanshu Singh](https://github.com/hi-manshu)

[Pranay Patel](https://github.com/pranaypatel512)

### License
```
   Copyright (C) 2018 MINDORKS NEXTGEN PRIVATE LIMITED

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

### Contributing to EditDrawableText
All pull requests are welcome, make sure to follow the [contribution guidelines](CONTRIBUTING.md) when you submit pull request.

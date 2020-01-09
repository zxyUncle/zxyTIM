# 腾讯即时通讯Im，多项目依赖同一个Module，IM依赖化
 1. 当你有连个项目需要用到同一个Im的功能，那么不可能项目1修改一遍，项目2页修改一遍，所有通过依赖库，修改一次依赖库，项目1跟项目2都已修改

[![](https://jitpack.io/v/zxyUncle/zxyTIM.svg)](https://jitpack.io/#zxyUncle/zxyTIM)

Gradle
-----

    Step 1
	 allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

      implementation 'com.github.zxyUncle:zxyTIM:Tag'













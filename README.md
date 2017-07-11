# LooperViewPager [![](https://ci.novoda.com/buildStatus/icon?job=bintray-release)](https://ci.novoda.com/job/bintray-release/lastBuild/console) [![Download](https://api.bintray.com/packages/wangjinggm/maven/recyclerview-drag/images/download.svg) ](https://bintray.com/wangjinggm/maven/recyclerview-drag/_latestVersion) [![license](http://img.shields.io/badge/license-Apache2.0-brightgreen.svg?style=flat)](https://github.com/Lee-Wang-Jing/ViewpagerMoreTabDemo/blob/master/LICENSE) 

技术交流群：598627802，加群前请务必阅读[群行为规范](https://github.com/Lee-Wang-Jing/GroupStandard)     
有问题或者某种需求欢迎加群或者提issues，Thanks
----
# Features
1. 添加ViewPager切换动画

# Dependencies
* Gradle
```groovy
compile 'com.wangjing:looperviewpager:1.0.1'
```
* Maven
```xml
<dependency>
  <groupId>com.wangjing</groupId>
  <artifactId>looperviewpager</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

* Eclipse ADT请放弃治疗

# Screenshot
gif有一些失真，且网页加载速度慢，建议下载demo运行后查看效果。  

# Usage
首先添加再依赖后Sync。

## xml中引用
在xml中引用SwipeRecyclerView：
```xml
    <com.wangjing.loopviewpager.LoopViewPager
        android:id="@+id/viewpager"
        android:layout_width="match_parent"
        android:background="@color/colorAccent"/>
```
## 设置自动滑动时间
滑动间隔时间默认是3000ms
```java
viewpager.setShowTime(2000); // 设置滑动间隔时间。
```
## 设置滑动方向
滑动方向默认向左滑动

```java
//设置滑动方向 向左
 viewpager.setDirection(LoopViewPager.Direction.LEFT);
```
## 开启自动滑动
```java
//开启自动滑动
 viewpager.start()
```
## 停止自动滑动
```java
//开启自动滑动
 viewpager.stop()
```


**由于LooperViewPager中post了一个Runnable，它持有当前Activity的实例，所以在LooperViewPager所在的当前Activity销毁时可能会发生内存泄漏，我们在View销毁的时候移除Runnable**
PS：LooperViewPager中已经处理，无需再次处理

```java
    @Override
    protected void onDetachedFromWindow() {
        removeCallbacks(player);
        super.onDetachedFromWindow();
    }
```

# License
```text
Copyright 2017 Wang Jing

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
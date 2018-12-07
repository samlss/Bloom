
![Bloom](https://github.com/samlss/Bloom/blob/master/screenshots/bloom.png)

 [![Download](https://api.bintray.com/packages/samlss/maven/bloom/images/download.svg)](https://bintray.com/samlss/maven/bloom/_latestVersion) [![Api reqeust](https://img.shields.io/badge/API-11+-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=11#l11)    [![Apache License 2.0](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/samlss/Bloom/blob/master/LICENSE)  [![Apk](https://img.shields.io/badge/apk-download-orange.svg)](https://github.com/samlss/Bloom/blob/master/apks/Bloom-V1.0.0.apk?raw=true)  


### 这个库做了什么?
它可以以粒子的形式显示任何view的粒子动画效果，你可以下载来查看效果

### 功能
- 支持任何view
- 可灵活配置粒子大小和形状
- 可灵活配置粒子动画

### 截图

![Bloom](https://github.com/samlss/Bloom/blob/master/screenshots/screenshot1.gif)

<br>

![Bloom](https://github.com/samlss/Bloom/blob/master/screenshots/screenshot2.gif)

<br>

![Bloom](https://github.com/samlss/Bloom/blob/master/screenshots/screenshot3.gif)

<br>

------
### 依赖

#### Gradle
在app目录下的build.gradle添加依赖使用：
  ```java
  dependencies {
      implementation 'me.samlss:bloom:1.0.0'
  }
  ```

#### Maven
```java
<dependency>
  <groupId>me.samlss</groupId>
  <artifactId>bloom</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

### 代码使用

```java
 Bloom.with('activity')
     .setParticleRadius(5)
     .setEffector(new BloomEffector.Builder()
         .setDuration(800)
         .setAnchor(view.getWidth() / 2, view.getHeight() / 2)
         .build())
     .boom(view);
```



更多描述可阅读 [wiki](https://github.com/samlss/Bloom/wiki) 

[下载](https://github.com/samlss/Bloom/blob/master/apks/Bloom-V1.0.0.apk?raw=true) apk查看demo效果

### License

```
Copyright 2018 samlss

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

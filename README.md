![Bloom](https://github.com/samlss/Bloom/blob/master/screenshots/bloom.png)

 [![Download](https://api.bintray.com/packages/samlss/maven/bloom/images/download.svg)](https://bintray.com/samlss/maven/bloom/_latestVersion) [![Api reqeust](https://img.shields.io/badge/API-11+-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=11#l11)    [![Apache License 2.0](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/samlss/Bloom/blob/master/LICENSE)  [![Apk](https://img.shields.io/badge/apk-download-orange.svg)](https://github.com/samlss/Bloom/blob/master/apks/Bloom-V1.0.0.apk?raw=true)  


### Features
- Support any view
- Flexible configuration the size, shape of the particle
- Flexible configuration of particle animation
- Configure fade out animation


### Screenshots

![Bloom](https://github.com/samlss/Bloom/blob/master/screenshots/screenshot1.gif)

<br>

![Bloom](https://github.com/samlss/Bloom/blob/master/screenshots/screenshot2.gif)

<br>

![Bloom](https://github.com/samlss/Bloom/blob/master/screenshots/screenshot3.gif)

<br>

------
### Dependency

#### Gradle
Add it in your module build.gradle at the end of repositories:
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

### Sample Usage

```java
 Bloom.with(this)
     .setParticleRadius(5)
     .setEffector(new BloomEffector.Builder()
     .setDuration(800)
     .setAnchor(view.getWidth() / 2, view.getHeight() / 2)
     .build())
     .boom(view);
```



Please read [wiki](https://github.com/samlss/Bloom/wiki) for more descriptions.

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
# MyArchitecture

MyArchitecture is a project that practise architecture design on Android application develop , anysise eache architecture advantages and disadvantagees.

## Update log

 - 2018-04-04 complete mvc-example
 - 2018-04-05 complete mvp-example

## 架构之谈

Android 从最初的MVC到后来的MVP，再到后来的MVVM，还有最近这两年的模块化、插件化及组件化（路由）架构，从其发展演进的历程来看都是本着降低代码耦合、提高代码的复用性和易维护性角度去进行发展的，确实这些架构的演进带给我们的是更高的开发及维护效率，降低了应用开发的难度，网上对于架构的文章更是不绝于耳，甚至是泛滥，对于架构的争论也更是从未停止过。对于每一种架构来说都有其优缺点，所以，每一种架构对于不同的项目来说发挥其作用也是不同的，因此，脱离实际的项目来谈架构实不可取的，每一个项目都有适合它自己的架构，要根据项目的实际情况（业务）去灵活技术选型一种架构或者选择一种架构并对其进行扩展及改进，以便来适用我们自己的项目，我们不要盲目的为了架构而去架构，避免过度设计，切不可生搬硬套！送给大家一句话：没有最好的，只有最适合自己的！哈哈~

下面给大家贴几个微信、美团、安居客架构的演进之路供大家阅读学习：

1. [微信架构演进之路](http://mp.weixin.qq.com/s/mkhCzeoLdev5TyO6DqHEdw)
2. [美图外卖架构演进](http://mp.weixin.qq.com/s/3cd8zmrFHn149hl-1wIAyQ)
3. [安居客Android架构演进](http://mp.weixin.qq.com/s/P785VmG06tXM79UPExsgWg)

## app

## common

基础公共组件库，包含一些常用工具类、基类抽取等。

 - utils
 - base
 - manager
 - ...

## http

联网请求的封装库。

 - okhttputils
 - okgo
 - retrofit
 - okhttp3.0
 - ...

## MVC

mvc-example

MVC即Model-View-Controller经典模式，最初是由Web端的MVC演化而来的，即分为三层设计，View对Model的依赖，导致View也包含了业务逻辑，Controller会变的臃肿、复杂，其实这种模式中，对于 **Activity** 严格来说是否属于 **Controller** 层一直以来存在一些争议，项目初期或者项目比较的小的时候用此模式的比较多。

## MVP

mvp-example

MVP即Model-View-Presenter,View被拆成了Presenter和View，真正实现了逻辑处理和View的分离。Model是具体业务的实现，例如访问数据或者网络请求数据等；View层是以接口的形式存在，不关心数据获取，不关心逻辑处理，只关心和用户的交互，即用户的输入和输出的显示；Presenter就是从View层获取用户的输入，传递到Model层进行处理，然后回调给View层，输出展示给用户。

优点：

- Activity 的职责更为明确；
- 代码结构清晰，易于维护；
- 方便进行单元测试；
- 可以有效解决Activity的内存泄漏；
- 可以进行多人协同、分层开发；

## MVVM

mvvm-example

## 组件化(Router)架构

架构图：

![image](https://github.com/xinpengfei520/MyArchitecture/blob/master/image/router_architecher.png)

声明：以上图片来自网络，仅供学习参考之用，如有侵权，请联系我删除，谢谢！

## TODO

 - mvvm-example
 - ...

## 更多关于MVP的文章

http://mp.weixin.qq.com/s/DuNbl3V4gZY-ZCETbhZGug

http://mp.weixin.qq.com/s/44caOYjLhCUhBdeR1qCRog

http://mp.weixin.qq.com/s/lJTSLcCtoZQkxzNb1OMzlQ

http://mp.weixin.qq.com/s/apNpcWsB4ujiuqbIdsF-jg

http://mp.weixin.qq.com/s/aRon6QtRuQwlqIZmhZIsOQ

http://lib.csdn.net/article/android/55568?knId=340

https://blog.csdn.net/zuo_er_lyf/article/details/79742169

https://github.com/googlesamples/android-architecture

https://blog.csdn.net/haha223545/article/details/76571703

https://blog.csdn.net/dd864140130/article/details/53645290

https://blog.csdn.net/guiying712/article/details/78057120

https://blog.csdn.net/guiying712/article/details/55213884

https://www.jianshu.com/p/578893cde5e1

https://www.jianshu.com/p/389c9ae1a82c

https://mp.weixin.qq.com/s?__biz=MzIyMjQ0MTU0NA==&mid=2247485288&idx=2&sn=7fbfdf5c7f63cb41f2c49b3c83860f8b&chksm=e82c3e4fdf5bb75904c1b70e5862fbca982860a7dbc8907d0222e134b1094561c5df6eaec2b4&scene=21#wechat_redirect

RecyclerView adapter封装：
https://blog.csdn.net/qq_22706515/article/details/50997446

TheMvp:
https://www.kymjs.com/code/2015/11/09/01/

androidmvp:
https://github.com/antoniolg/androidmvp

MvpApp:
https://github.com/Rukey7/MvpApp

MVPArms:
https://github.com/JessYanCoding/MVPArms

T-MVP:
https://github.com/north2016/T-MVP

MvpCleanArchitecture:
https://github.com/glomadrian/MvpCleanArchitecture

android-mvp-architecture:
https://github.com/MindorksOpenSource/android-mvp-architecture

XDroidMvp:
https://github.com/limedroid/XDroidMvp

KotlinMvp:
https://github.com/git-xuhao/KotlinMvp

AndroidMVPSample:
https://github.com/WuXiaolong/AndroidMVPSample

SuperMvp:
https://github.com/liuyanggithub/SuperMvp

MVPAndroidBootstrap:
https://github.com/richardradics/MVPAndroidBootstrap

MVP-RxJava-Hybride:
https://github.com/youxin11544/MVP-RxJava-Hybride

EasyMVP:
https://github.com/6thsolution/EasyMVP

android-mvp-pattern:
https://github.com/kaedea/android-mvp-pattern

MVPArt:
https://github.com/JessYanCoding/MVPArt

Beam:
https://github.com/Jude95/Beam

mosby：
https://github.com/sockeqwe/mosby

Moxy:
https://github.com/Arello-Mobile/Moxy

## MVP开源练习项目

### ZhiHuMVP

https://github.com/fangx/ZhiHuMVP

### ColorfulNews

https://github.com/kaku2015/ColorfulNews

### boxing

https://github.com/Bilibili/boxing

### MinimalistWeather

https://github.com/BaronZ88/MinimalistWeather

### TranslateApp

https://github.com/maoruibin/TranslateApp

### Ghost

https://github.com/GeekGhost/Ghost

### GeekNews

https://github.com/codeestX/GeekNews

### MovieGuide

https://github.com/esoxjem/MovieGuide

### SimpleNews

https://github.com/liuling07/SimpleNews

### Toutiao

https://github.com/iMeiji/Toutiao

### PhotoNoter

https://github.com/yydcdut/PhotoNoter
# APP项目组件化

[TOC]

## 一、概念

### 1.1 集成化和组件化

* 集成化： `module`不能独立运行；
* 组件化： `module`可以独立运行。

### 1.2 项目组件化的意义

* 开发需求：不相互依赖、可以相互交互、任意组合、高度解耦；
* 团队效率：分模块打包、测试，统一版本管理

### 1.3 组件化架构设计

组件化架构设计如下图所示：

![image](https://github.com/tianyalu/NeModularLearning/raw/master/show/modular_structure.png)

### 1.4 `Phone Module`和`Android Library`区别及切换

|      | `Phone Module`                            | `Android Library`                     |
| ---- | ----------------------------------------- | ------------------------------------- |
| 新建 | 新建出可以独立运行的模块，可以看成是`app` | 新建出`Android`库，不能独立运行       |
| 配置 | `apply plugin: 'com.android.application'` | `apply plugin: 'com.android.library'` |
| 注意 | `applicationId`（有）                     | `applicationId`（无）                 |
| 切换 | `'com.android.library'`                   | `'com.android.application'`           |

#### 1.4.1 `Phone Module`

`build.gradle`文件

```groovy
apply plugin:'com.android.application'

defalultConfig {
  //...
  applicationId "com.sty.xxx"
}
```

#### 1.4.2 `Library Module`

`build.gradle`文件

```groovy
apply plugin:'com.android.library'

defalultConfig {
  //...
}
```

### 1.5 组件化开发规范

* `order`: `order_`前缀（`src`类和`res`资源）;
* `personal`: `personal_`前缀（`src`类和`res`资源）；
* `app`: 可以不改，使用默认

### 1.6 组件化开发的临时代码，集成化打包时动态隔离

子模块的`build.gradle`文件`android`节点下：

```groovy
    //配置资源路径，方便测试环境，打包不集成到正式环境
    sourceSets {
        main {
            if(!isRelease) {
                //如果是组件化模式，需要单独运行时
                manifest.srcFile 'src/main/debug/AndroidManifest.xml'
            }else {
                //集成化模式，整个项目打包apk
                manifest.srcFile 'src/main/AndroidManifest.xml'
                java {
                    //release时debug目录下文件不需要合并到主工程
                    exclude '**/debug/**'
                }
            }
        }
    }
```

## 二、`Module`与`Module`之间的交互

### 2.1 `Module`之间的交互方式（跳转、传参等）

> 1. **`EventBus`**：`EventBean`非常多（一对一），一对多就会混乱不堪、难以维护；
> 2. **反射**：反射技术可以成功，维护成本较高且容易出现高版本`@hide`限制；
> 3. **隐式意图**：维护成本还好，只是比较麻烦，需要维护`Mainfest`中的`action`；
> 4. **`BroadCastReceiver`**：需要动态注册（7.0后），需求方发送广播；
> 5. **类加载**：需要准确的全类名路径，维护成本较高且容易出现人为失误。

#### 2.1.1 类加载技术交互

注意事项：需要准确的全类名路径。

```java
//类加载，可以成功，维护成本较高且容易出现人为失误
try {
  Class targetClass = Class.forName("com.sty.ne.modularlearning.order.Order_ManiActivity");
  Intent intent =new Intent(this, targetClass);
  intent.putExtra("params", "sty");
  startActivity(intent);
} catch (ClassNotFoundException e) {
  e.printStackTrace();
}
```

#### 2.1.2 全局`Map`记录信息

定义`PathBean`对象：

* `path`属性：跳转目标字符串缩写；
* `clazz`属性：跳转目标`Class`对象；
* 公共基础库，所有的子模块都可以调用。

```java
/**
 * 路径对象
 * 如：
 * path: "order/Order_MainActivity"
 * clazz: Order_MainActivity.class
 */
public class PathBean {
  private String path;
  private Class clazz;
  //...
}
```


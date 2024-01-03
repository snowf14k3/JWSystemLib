# JWSystem (W.I.P)
_**这是一个支持 广科师(强智)教务系统实现 | 评教 | 查课 | 抢课| 退课 的第三方库**_

## 支持功能
| 评教 | 查课 | 抢课 | 退课 |
| :--: | :--: | :--: | :--: |
|  ×   |  √   |  √   |  √   |

## 声明
1. 本项目使用 `MIT License`,根据协议允许您进行任意修改/发布/传播,且造成后果与本项目无关.
2. 特别感谢<a href="https://www.jetbrains.com"> JetBrains </a>  与 <a href="https://jsoup.org/"> JSoup </a>对这个项目的支持。
3. 本项目仅为实现教务系统一些功能,不提供编写好的程序,且本项目不推荐商用.
---

## 吐槽
1. 由于学校服务器登录系统没有配好分流的系统,导致全校师生,
   都只对一个服务器进行访问,出现了一站有难四站围观的
   牛逼情况,其他4个内网镜像服务器跟没开一样.


2. 这个教务系统我不做评价,在分析过程中遇到的变量名
   把我恶心坏了,这个变量名字就是狗屎,有谁会拿`简拼`当变量名的啊?

---

## 如何使用

本lib就不上传至maven仓库了,需要使用请前往
[releases](https://github.com/ciallo-dev/JWSystemLib/releases)
下载使用

### 代码引入

1. 你能够将此代码引入Android进行开发第三方的app

2. 甚至你能使用它编写小脚本在termux的模拟环境下实现
   
   登录教务系统和选课等操作

```java
public class Test{

   public static void main(String[] args) {
      // 使用内置内网第二条备用路线登录
      URLManager.useLocalNetServer(2);
      
      // 登录使用
      JWSystem system = new JWSystem().login("username", "password");
      // 直接通过搜索获取全部的网课
      ArrayList<Course> courses = system.getCourseSelectManager().getElectiveCourseByTeacher("网络课程");
      // 通过筛选获得course对象...
      system.getCourseSelectManager().selectCourse(courses.get(select));
      // 其他API...自行阅读代码
   }

   
   public static void setBaseURL(String baseURL){
      // 自个指定学校的jw系统地址
      // 也支持内网的url
      URLManager.BASE_URL = "http://jw.xxxx.edu.com";
   }
   
   /**
    * KCID 每年都一样
    * JXID 每年不固定
    * @param system 登录的instance
    */
   public static void selectCourseByCreate(JWSystem system){
       Course course = new Course("kcid","jxid");
       
       // 选择课程
       boolean statement = system.getCourseSelectManager().selectCourse(course);
       
       if (statement) {
          System.out.println("退课成功");
       } else {
          System.out.println("退课失败");
       }
       
   }
   
}
```

### 例子

1. [选课](https://github.com/ciallo-dev/JWSystemLib/blob/master/src/test/java/TestSelectCourse.java) 例子1
2. [查已选课程退课](https://github.com/ciallo-dev/JWSystemLib/blob/master/src/test/java/TestMyCourse.java) 例子2
3. [学生课程评价](https://github.com/ciallo-dev/JWSystemLib/blob/master/src/test/java/TestCourseReview.java) 例子3
4. [各种请求的URL详解](https://github.com/ciallo-dev/JWSystemLib/blob/master/src/main/java/moe/snowflake/jwSystem/manager/URLManager.java) 预览

---

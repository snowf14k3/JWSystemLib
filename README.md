# JWSystem (W.I.P)
_**这是一个支持 广科师教务系统实现 | 评教 | 查课 | 抢课| 退课的第三方库**_

## 支持功能
| 评教 | 查课 | 抢课 | 退课 |
| :--: | :--: | :--: | :--: |
|  ×   |  √   |  √   |  √   |

## 声明
1. 本项目使用 `MIT License`,根据协议允许您进行任意修改/发布/传播,且造成后果与本项目无关.
2. 特别感谢<a href="https://www.jetbrains.com"> JetBrains </a>  与 <a href="https://jsoup.org/"> JSoup </a>对这个项目的支持。
3. 本项目仅为实现教务系统一些功能,本项目不推荐商用.
---

## 由来和吐槽
1. 由于学校服务器响应时间太拉跨,会话失效,就写了一个这个东西.

    减少对服务器发数据,虽说也没啥用,不过总比无限套娃

    的登录页面好.因此干脆就直接写一套流程的API库

2. 这个教务系统我不做评价,在请求分析过程中遇到的变量名

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
      // 登录使用
      JWSystem system = new JWSystem().login("username", "password");
      // 直接通过搜索获取全部的网课
      ArrayList<Course> courses = system.getCourseSelectManager().getElectiveCourseByTeacher("网络课程");
      // 通过筛选获得course对象...
      system.getCourseSelectManager().selectElectiveCourse(courses.get(select));
      // 其他API...自行阅读代码
   }

   /**
    * KCID 每年都一样
    * JXID 每年不固定
    * @param core 登录的instance
    */
   public static void selectCourseByCreate(JWSystem core){
       Course course = new Course("kcid","jxid");
       
       // 选择选修课程
       system.getCourseSelectManager().selectElectiveCourse(course);
       
       // or
      
       // 选择必修课程
      system.getCourseSelectManager().selectRequiredCourse(course);
   }
   
}
```

### 例子

1. [选公共选修课程](https://github.com/ciallo-dev/JWSystemLib/blob/master/src/test/java/TestElectiveCourse.java) 例子1
2. [查已选课程,退课](https://github.com/ciallo-dev/JWSystemLib/blob/master/src/test/java/TestMyCourse.java) 例子2
3. [学生课程评价](https://github.com/ciallo-dev/JWSystemLib/blob/master/src/test/java/TestCourseReview.java) 例子3
4. [各种请求的URL详解](https://github.com/ciallo-dev/JWSystemLib/blob/master/src/main/java/moe/snowflake/courseSelect/utils/URLConstants.java) 预览

---

# (WIP) CourseSelect
**一个广科师抢课查课退课的第三方库**

## 声明
1. 本项目使用 `MIT License`,根据协议允许您进行任意修改/发布/传播,且造成后果与本项目无关.
2. 特别感谢<a href="https://www.jetbrains.com"> JetBrains </a>  与 <a href="https://jsoup.org/"> JSoup </a>对这个项目的支持。
3. 本项目仅为实现教务系统一些功能,本项目不推荐商用
---

## 由来和吐槽
1. 由于学校服务器响应时间太拉跨,会话失效,就写了一个这个东西.

    减少对服务器发数据,达到快速选课(虽说也没啥用),不过总比无限套娃

    的登录页面好.

2. 这个系统我不做评价

    这个变量名字就是狗屎,有谁会拿`简拼`当变量名的啊?

---

## 如何使用

### 代码引入

```java
public class Test{
   public static void main(String[] args) {
      // 登录使用
      CourseSelectCore core = new CourseSelectCore().login("username", "password");
      // 直接通过搜索获取全部的网课
      ArrayList<Course> courses = core.getElectiveCourseByTeacher("网络课程");
      // 通过筛选获得course对象...
      core.selectElectiveCourse(courses.get(select));
      // 其他API...自行阅读代码
   }
}
```

### 例子

1. [选公共选修课](https://github.com/ciallo-dev/CourseSelector/blob/master/src/test/java/TestElectiveCourse.java) 例子
2. [各种请求的URL详解](https://github.com/ciallo-dev/CourseSelector/blob/master/src/main/java/moe/select/utils/URLConstants.java) 预览

---
package moe.snowflake.courseSelect.utils;

public class URLConstants {
    // ################### URL ###################

    /* 登录加密密钥 */
    public static String LOGIN_DATA = "http://jw.gxstnu.edu.cn/Logon.do?method=logon&flag=sess";
    /**
     *  登录教务系统请求
     *
     */
    public static String LOGIN = "http://jw.gxstnu.edu.cn/Logon.do?method=logon";
    /**
     * 使用 BASE64的登录数据
     */
    public static String LOGIN2 = "http://jw.gxstnu.edu.cn/jsxsd/xk/LoginToXk";

    /**
     * 登录选课系统
     */
    public static String COURSE_LOGIN = "http://jw.gxstnu.edu.cn/jsxsd/xsxk/xsxk_index?jx0502zbid=F17F6D4A35AF4EDA8727F42C3BCAF124";

    /* 正式选课 */
    public static String XSXK_WEB = "http://jw.gxstnu.edu.cn/jsxsd/xsxk/xsxk_index?jx0502zbid=F17F6D4A35AF4EDA8727F42C3BCAF124";
    /* 预先选课 */
    public static String YXXSXK_WEB = "http://jw.gxstnu.edu.cn/jsxsd/xsxk/yxxsxk_index?jx0502zbid=F17F6D4A35AF4EDA8727F42C3BCAF124";

    /* 退课 */
    public static String EXIT_COURSE = "http://jw.gxstnu.edu.cn/jsxsd/xsxkjg/xstkOper?jx0404id=<jx0404id>&tkyy=<reason>";

    public static String EXIT_JWSYSTEM = "http://jw.gxstnu.edu.cn/jsxsd/xk/LoginToXk?method=exit&tktime="+System.currentTimeMillis();
    /* 退出选课系统 */
    public static String EXIT_COURSE_WEB = "http://jw.gxstnu.edu.cn/jsxsd/xsxk/xsxk_exit?jx0404id=1";

    /**
     * 参数
     *
     * kcxx 课程名称
     *      xx课 格式 url encode x2
     * skls 授课老师
     *      xx老师 格式 url encode x2
     * skjc 节次 (需要同时选择上课星期)
     *      1-2- 1-2节
     *      3--  3节
     *      4-5- 4-5节
     *      6-7- 6-7节
     *      8-9- 8-9节
     *      10-11-12 10-12杰
     * skxq 上课星期
     *     1-7 表示 星期一 ~ 星期天
     * sfym 过滤已满课程
     *      false 默认值
     * sfct 过滤冲突课程
     *      false 默认值
     * szjylb 类别索引
     *      17 德育教育类
     *      14 美育教育类
     *      13 教师教育类
     *      12 语言应用类
     *      10 英语应用
     *      9 其他
     *      8 汉语应用
     *      7 公共艺术
     *      6 综合素质
     *      5 四史教育类
     *      4 身心素质类
     *      3 社会素养类
     *      2 科学素养类
     *      1 人文素养类
     *    空白 显示全部课程
     * sfxx 过滤限选课程
     *      true 默认值
     *
     * =============================================
     *  表单数据
     *  xxx 默认值 解释
     * =============================================
     *  sEcho: 2
     *  iColumns: 13 列数
     *  sColumns: ""
     *  iDisplayStart: 0
     *  iDisplayLength: 15 一页显示15个
     *  mDataProp_0: kch 课程号
     *  mDataProp_2: kcmc 课程名称
     *  mDataProp_2: xf 学分
     *  mDataProp_3: skls 授课老师
     *  mDataProp_4 sksj 授课时间
     *  mDataProp_5 skdd 授课地点
     *  mDataProp_6 sqxq 上课校区
     *  mDataProp_7 xxrs 限选人数
     *  mDataProp_8 xkrs 选课人数
     *  mDataProp_9 syrs 剩余人数
     *  mDataProp_10 ctsm 时间冲突
     *  mDataProp_11 szkcflmc 类别
     *  mDataProp_12 czOper 选课操作的按钮
     *  =============================================
     *  return
     *  iTotalRecords 178 总记录
     *
     *
     *  =============================================
     *  backup
     *      kcxx=&skls=&skxq=&skjc=&sfym=false&sfct=false&szjylb=&sfxx=true
     */
    public static String ELECTIVE_COURSE_LIST = "http://jw.gxstnu.edu.cn/jsxsd/xsxkkc/yl_xsxkGgxxkxk";
    /**
     * 参数
     * skxq_xx0103
     *      1 北校区
     *      2 南校区
     *      3 来宾校区
     *  ==================================
     *  请参考选修LIST
     *      sEcho: 1
     *      iColumns: 11 列数
     *      sColumns:
     *      iDisplayStart: 0
     *      iDisplayLength: 15
     *      mDataProp_0: kch
     *      mDataProp_1: kcmc
     *      mDataProp_2: fzmc
     *      mDataProp_3: ktmc
     *      mDataProp_4: xf
     *      mDataProp_5: skls
     *      mDataProp_6: sksj
     *      mDataProp_7: skdd
     *      mDataProp_8: xqmc
     *      mDataProp_9: ctsm
     *      mDataProp_10: czOper
     */
    public static String REQUIRED_COURSE_LIST = "http://jw.gxstnu.edu.cn/jsxsd/xsxkkc/xsxkBxxk?skxq_xx0103=";

    /**
     * 选课操作
     * ------------------------
     * kcid(jx02id) 课程ID
     * jx0404id 不知道是什么id
     * ------------------------
     * replace以下两个参数
     *      <kcid>
     *      <jx0404id>
     * rsp :
     *  {"success":true,"message":"选课成功","jfViewStr":""}
     */
    public static String ELECTIVE_COURSE_SELECT = "http://jw.gxstnu.edu.cn/jsxsd/xsxkkc/ggxxkxkOper?kcid=<kcid>&cfbs=null&jx0404id=<jx0404id>&xkzy=&trjf=";

    /**
     *
     * ------------------------
     * kcid(jx02id) 课程ID
     * jx0404id 不知道是什么id
     * ------------------------
     * replace以下两个参数
     *      <kcid>
     *      <jx0404id>
     *
     */
    public static String REQUIRED_COURSE_SELECT = "http://jw.gxstnu.edu.cn/jsxsd/xsxkkc/bxxkOper?kcid=<kcid>&cfbs=null&jx0404id=<jx0404id>&xkzy=&trjf=";

    /**
     * GET
     * 现在当前课程的列表
     */
    public static String MY_COURSE_LIST = "http://jw.gxstnu.edu.cn/jsxsd/xsxkjg/comeXkjglb";

    /**
     * 查找有哪些课程可评价
     */
    public static String EVALUATE_COURSE_FIND = "http://jw.gxstnu.edu.cn/jsxsd/xspj/xspj_find.do";




}

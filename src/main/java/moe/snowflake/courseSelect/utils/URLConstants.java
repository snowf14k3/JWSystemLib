package moe.snowflake.courseSelect.utils;

public class URLConstants {
    // ################### URL ###################

    /**
     * METHOD:GET
     * <p>
     * 登录加密密钥
     */
    public static String LOGIN_DATA = "http://jw.gxstnu.edu.cn/Logon.do?method=logon&flag=sess";
    /**
     * METHOD:POST
     * <p>
     *  登录教务系统请求
     */
    public static String LOGIN = "http://jw.gxstnu.edu.cn/Logon.do?method=logon";
    /**
     * METHOD:POST
     * <p>
     * 使用 BASE64的登录数据
     */
    public static String LOGIN2 = "http://jw.gxstnu.edu.cn/jsxsd/xk/LoginToXk";

    /**
     * METHOD:GET
     * <p>
     * 登录选课系统
     */
    public static String COURSE_LOGIN = "http://jw.gxstnu.edu.cn/jsxsd/xsxk/xsxk_index?jx0502zbid=F17F6D4A35AF4EDA8727F42C3BCAF124";

    /**
     * METHOD:GET
     * <p>
     * 预先选课查询
     */
    public static String YXXSXK_WEB = "http://jw.gxstnu.edu.cn/jsxsd/xsxk/yxxsxk_index?jx0502zbid=F17F6D4A35AF4EDA8727F42C3BCAF124";

    /**
     * METHOD:GET
     * <p> 退课
     */
    public static String EXIT_COURSE = "http://jw.gxstnu.edu.cn/jsxsd/xsxkjg/xstkOper?jx0404id=<jx0404id>&tkyy=<reason>";

    /**
     * METHOD:GET
     * <p>
     * 退出教务系统
     */
    public static String EXIT_JWSYSTEM = "http://jw.gxstnu.edu.cn/jsxsd/xk/LoginToXk?method=exit&tktime="+System.currentTimeMillis();
    /**
     * METHOD:GET
     * <p>
     * 退出选课系统
     */
    public static String EXIT_COURSE_WEB = "http://jw.gxstnu.edu.cn/jsxsd/xsxk/xsxk_exit?jx0404id=1";

    /**
     * METHOD:POST
     * <p>
     * * 参数
     * <p>
     * kcxx 课程名称
     * <p>
     *      xx课 格式
     *      <p>
     * <p>
     * =============================================
     * skls 授课老师
     * <p>
     *      xx老师 格式 url encode x2
     * <p>
     * =============================================
     * skjc 节次 (需要同时选择上课星期)
     *      <p>
     *      1-2- 1-2节
     *      <p>
     *      3--  3节
     *      <p>
     *      4-5- 4-5节
     *      <p>
     *      6-7- 6-7节
     *      <p>
     *      8-9- 8-9节
     *      <p>
     *      10-11-12 10-12杰
     *      <p>
     * =============================================
     * skxq 上课星期
     * <p>
     *     1-7 表示 星期一 ~ 星期天
     * =============================================
     * sfym 过滤已满课程
     * <p>
     *      false 默认值
     * <p>
     * =============================================
     * sfct 过滤冲突课程
     *      <p>
     *      false 默认值
     *  <p>
     * =============================================
     * szjylb 类别索引
     *      <p>
     *      17 德育教育类
     *      <p>
     *      14 美育教育类
     *      <p>
     *      13 教师教育类
     *      <p>
     *      12 语言应用类
     *      <p>
     *      10 英语应用
     *      <p>
     *      9 其他
     *      <p>
     *      8 汉语应用
     *      <p>
     *      7 公共艺术
     *      <p>
     *      6 综合素质
     *      <p>
     *      5 四史教育类
     *      <p>
     *      4 身心素质类
     *      <p>
     *      3 社会素养类
     *      <p>
     *      2 科学素养类
     *      <p>
     *      1 人文素养类
     *      <p>
     *    空白 显示全部课程
     *    <p>
     * =============================================
     * sfxx 过滤限选课程
     * <p>
     *      true 默认值
     * =============================================
     *  表单数据
     *  xxx 默认值 解释
     * =============================================
     *  sEcho: 2
     *  <p>
     *  iColumns: 13 列数
     *  <p>
     *  sColumns: ""
     *  <p>
     *  iDisplayStart: 0
     *  <p>
     *  iDisplayLength: 15 一页显示15个
     *  <p>
     *  mDataProp_0: kch 课程号
     *  <p>
     *  mDataProp_2: kcmc 课程名称
     *  <p>
     *  mDataProp_2: xf 学分
     *  <p>
     *  mDataProp_3: skls 授课老师
     *  <p>
     *  mDataProp_4 sksj 授课时间
     *  <p>
     *  mDataProp_5 skdd 授课地点
     *  <p>
     *  mDataProp_6 sqxq 上课校区
     *  <p>
     *  mDataProp_7 xxrs 限选人数
     *  <p>
     *  mDataProp_8 xkrs 选课人数
     *  <p>
     *  mDataProp_9 syrs 剩余人数
     *  <p>
     *  mDataProp_10 ctsm 时间冲突
     *  <p>
     *  mDataProp_11 szkcflmc 类别
     *  <p>
     *  mDataProp_12 czOper 选课操作的按钮
     *  <p>
     *  iTotalRecords 178 总记录
     *  <p>
     *  =============================================
     *  backup
     *  kcxx=&skls=&skxq=&skjc=&sfym=false&sfct=false&szjylb=&sfxx=true
     */
    public static String ELECTIVE_COURSE_LIST = "http://jw.gxstnu.edu.cn/jsxsd/xsxkkc/yl_xsxkGgxxkxk";
    /**
     * METHOD:POST
     * <p>
     * * 参数
     * <p>skxq_xx0103
     *      <p>
     *      1 北校区
     *      <p>
     *      2 南校区
     *      <p>
     *      3 来宾校区
     *      <p>
     *  ==================================
     *  <p>
     *  请参考选修LIST
     *      <p>
     *      sEcho: 1
     *      <p>
     *      iColumns: 11 列数
     *      <p>
     *      sColumns:
     *      <p>
     *      iDisplayStart: 0
     *      <p>
     *      iDisplayLength: 15
     *      <p>
     *      mDataProp_0: kch
     *      <p>
     *      mDataProp_1: kcmc
     *      <p>
     *      mDataProp_2: fzmc
     *      <p>
     *      mDataProp_3: ktmc
     *      <p>
     *      mDataProp_4: xf
     *      <p>
     *      mDataProp_5: skls
     *      <p>
     *      mDataProp_6: sksj
     *      <p>
     *      mDataProp_7: skdd
     *      <p>
     *      mDataProp_8: xqmc
     *      <p>
     *      mDataProp_9: ctsm
     *      <p>
     *      mDataProp_10: czOper
     */
    public static String REQUIRED_COURSE_LIST = "http://jw.gxstnu.edu.cn/jsxsd/xsxkkc/xsxkBxxk?skxq_xx0103=";

    /**
     * METHOD:GET
     * <p>
     * * 选课操作
     * <p>
     * ==================================
     * <p>
     * kcid(jx02id) 课程ID
     * <p>
     * jx0404id 不知道是什么id
     * <p>
     * ==================================
     * <p>
     * replace以下两个参数
     * <p>
     *      <kcid>
     *          <p>
     *      <jx0404id>
     *          <p>
     * response :
     * <p>
     *  {"success":true,"message":"选课成功","jfViewStr":""}
     *  <p>
     */
    public static String ELECTIVE_COURSE_SELECT = "http://jw.gxstnu.edu.cn/jsxsd/xsxkkc/ggxxkxkOper?kcid=<kcid>&cfbs=null&jx0404id=<jx0404id>&xkzy=&trjf=";

    /**
     * METHOD:GET
     * <p>
     * 选课操作
     * <p>
     * ==================================
     * <p>
     * kcid(jx02id) 课程ID
     * <p>
     * jx0404id 不知道是什么id
     * <p>
     * ==================================
     * <p>
     * replace以下两个参数
     * <p>
     *      <kcid>
     *          <p>
     *      <jx0404id>
     *          <p>
     * response :
     * <p>
     *  {"success":true,"message":"选课成功","jfViewStr":""}
     *  <p>
     */
    public static String REQUIRED_COURSE_SELECT = "http://jw.gxstnu.edu.cn/jsxsd/xsxkkc/bxxkOper?kcid=<kcid>&cfbs=null&jx0404id=<jx0404id>&xkzy=&trjf=";

    /**
     * METHOD:GET
     * <p>
     * 现在当前课程的列表
     */
    public static String MY_COURSE_LIST = "http://jw.gxstnu.edu.cn/jsxsd/xsxkjg/comeXkjglb";

    /**
     * METHOD:GET
     * <p>
     * 查找有哪些课程可评价
     */
    public static String EVALUATE_COURSE_FIND = "http://jw.gxstnu.edu.cn/jsxsd/xspj/xspj_find.do";


}

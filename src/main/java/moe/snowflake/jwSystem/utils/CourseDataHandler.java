package moe.snowflake.jwSystem.utils;

import com.google.gson.*;
import moe.snowflake.jwSystem.course.Course;

import java.util.ArrayList;

/**
 *
 * TESTDATA
 * {"aaData":[{"kkdw":"26","kcxzm":"01","szkcflmc":null,"parentjx0404id":null,"fj_filename":null,"xnxq01id":"2023-2024-2","dwmc":"体育学院","ksfs":"3","pkrs":40,"ktmc":"智能制造[231-233]班,学前本科[232-233]班,汽服职教[231-233]班,机器人工程[231-233]班","tzdlb":"1","kcsx":"1","kch":"08DXT021","syrs":null,"kxh":null,"fzmc":"245五人制棒球","cfbs":null,"sksj":"1-2周 星期二 4-5节  <br>3-16周 星期二 4-5节  ","kcmc":"大学体育2","kkapList":[{"jssj":"12:10","jzwmc":"足球场","jgxm":"莫强光","skjcmc":"4-5","skzcList":["1","2"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"1-2","kssj":"10:45","jsmc":"足球场3","kkdlb":"1"},{"jssj":"12:10","jzwmc":"足球场","jgxm":"莫强光","skjcmc":"4-5","skzcList":["3","4","5","6","7","8","9","10","11","12","13","14","15","16"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"3-16","kssj":"10:45","jsmc":"足球场3","kkdlb":"1"}],"szkcfl":null,"dyrsbl":null,"ctsm":"与已选课程 “大学体育2” 冲突","sklsid":"5FE11BFE80894A15BE94F3DAD765636E","skls":"莫强光","kcjj":null,"xqid":"3","sfkfxk":"1","skdd":"足球场3<br>足球场3","xf":1,"txsfkxq":null,"kcxzmc":"公共必修课","zxs":32,"jx0404id":"202320242005140","xqmc":"来宾校区","jxdg_filename":null,"jx02id":"2AE48F62886D4ECFB69527AB88158CE4","xbyq":null,"xbyqmc":null,"sftk":null},{"kkdw":"26","kcxzm":"01","szkcflmc":null,"parentjx0404id":null,"fj_filename":null,"xnxq01id":"2023-2024-2","dwmc":"体育学院","ksfs":"3","pkrs":40,"ktmc":"智能制造[231-233]班,学前本科[232-233]班,汽服职教[231-233]班,机器人工程[231-233]班","tzdlb":"1","kcsx":"1","kch":"08DXT021","syrs":null,"kxh":null,"fzmc":"245乒乓球","cfbs":null,"sksj":"1-2周 星期二 4-5节  <br>3-16周 星期二 4-5节  ","kcmc":"大学体育2","kkapList":[{"jssj":"12:10","jzwmc":"综合体育馆","jgxm":"伍方佳","skjcmc":"4-5","skzcList":["1","2"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"1-2","kssj":"10:45","jsmc":"T118-乒乓球室","kkdlb":"1"},{"jssj":"12:10","jzwmc":"综合体育馆","jgxm":"伍方佳","skjcmc":"4-5","skzcList":["3","4","5","6","7","8","9","10","11","12","13","14","15","16"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"3-16","kssj":"10:45","jsmc":"T118-乒乓球室","kkdlb":"1"}],"szkcfl":null,"dyrsbl":null,"ctsm":"与已选课程 “大学体育2” 冲突","sklsid":"5095F17C9CDE4A7D9BEE446EA44EB9CD","skls":"伍方佳","kcjj":null,"xqid":"3","sfkfxk":"1","skdd":"T118-乒乓球室<br>T118-乒乓球室","xf":1,"txsfkxq":null,"kcxzmc":"公共必修课","zxs":32,"jx0404id":"202320242005141","xqmc":"来宾校区","jxdg_filename":null,"jx02id":"2AE48F62886D4ECFB69527AB88158CE4","xbyq":null,"xbyqmc":null,"sftk":null},{"kkdw":"26","kcxzm":"01","szkcflmc":null,"parentjx0404id":null,"fj_filename":null,"xnxq01id":"2023-2024-2","dwmc":"体育学院","ksfs":"3","pkrs":40,"ktmc":"智能制造[231-233]班,学前本科[232-233]班,汽服职教[231-233]班,机器人工程[231-233]班","tzdlb":"1","kcsx":"1","kch":"08DXT021","syrs":null,"kxh":null,"fzmc":"245篮球","cfbs":null,"sksj":"1-2周 星期二 4-5节  <br>3-16周 星期二 4-5节  ","kcmc":"大学体育2","kkapList":[{"jssj":"12:10","jzwmc":"风雨球馆","jgxm":"何林铭","skjcmc":"4-5","skzcList":["1","2"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"1-2","kssj":"10:45","jsmc":"运动场所2（来）","kkdlb":"1"},{"jssj":"12:10","jzwmc":"风雨球馆","jgxm":"何林铭","skjcmc":"4-5","skzcList":["3","4","5","6","7","8","9","10","11","12","13","14","15","16"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"3-16","kssj":"10:45","jsmc":"运动场所2（来）","kkdlb":"1"}],"szkcfl":null,"dyrsbl":null,"ctsm":"与已选课程 “大学体育2” 冲突","sklsid":"1EB6A9E5E93B4A9CA947091B406897C6","skls":"何林铭","kcjj":null,"xqid":"3","sfkfxk":"1","skdd":"运动场所2（来）<br>运动场所2（来）","xf":1,"txsfkxq":null,"kcxzmc":"公共必修课","zxs":32,"jx0404id":"202320242005142","xqmc":"来宾校区","jxdg_filename":null,"jx02id":"2AE48F62886D4ECFB69527AB88158CE4","xbyq":null,"xbyqmc":null,"sftk":null},{"kkdw":"26","kcxzm":"01","szkcflmc":null,"parentjx0404id":null,"fj_filename":null,"xnxq01id":"2023-2024-2","dwmc":"体育学院","ksfs":"3","pkrs":40,"ktmc":"智能制造[231-233]班,学前本科[232-233]班,汽服职教[231-233]班,机器人工程[231-233]班","tzdlb":"1","kcsx":"1","kch":"08DXT021","syrs":null,"kxh":null,"fzmc":"245网球","cfbs":null,"sksj":"1-2周 星期二 4-5节  <br>3-16周 星期二 4-5节  ","kcmc":"大学体育2","kkapList":[{"jssj":"12:10","jzwmc":"网球场","jgxm":"龙凤潜","skjcmc":"4-5","skzcList":["1","2"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"1-2","kssj":"10:45","jsmc":"网球场4","kkdlb":"1"},{"jssj":"12:10","jzwmc":"网球场","jgxm":"龙凤潜","skjcmc":"4-5","skzcList":["3","4","5","6","7","8","9","10","11","12","13","14","15","16"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"3-16","kssj":"10:45","jsmc":"网球场4","kkdlb":"1"}],"szkcfl":null,"dyrsbl":null,"ctsm":"与已选课程 “大学体育2” 冲突","sklsid":"0AC846BAE8644EB1883B293BD42AB789","skls":"龙凤潜","kcjj":null,"xqid":"3","sfkfxk":"1","skdd":"网球场4<br>网球场4","xf":1,"txsfkxq":null,"kcxzmc":"公共必修课","zxs":32,"jx0404id":"202320242005143","xqmc":"来宾校区","jxdg_filename":null,"jx02id":"2AE48F62886D4ECFB69527AB88158CE4","xbyq":null,"xbyqmc":null,"sftk":null},{"kkdw":"26","kcxzm":"01","szkcflmc":null,"parentjx0404id":null,"fj_filename":null,"xnxq01id":"2023-2024-2","dwmc":"体育学院","ksfs":"3","pkrs":40,"ktmc":"智能制造[231-233]班,学前本科[232-233]班,汽服职教[231-233]班,机器人工程[231-233]班","tzdlb":"1","kcsx":"1","kch":"08DXT021","syrs":null,"kxh":null,"fzmc":"245羽毛球","cfbs":null,"sksj":"1-2周 星期二 4-5节  <br>3-16周 星期二 4-5节  ","kcmc":"大学体育2","kkapList":[{"jssj":"12:10","jzwmc":"风雨球馆","jgxm":"王载文","skjcmc":"4-5","skzcList":["1","2"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"1-2","kssj":"10:45","jsmc":"羽毛球场1（来）","kkdlb":"1"},{"jssj":"12:10","jzwmc":"风雨球馆","jgxm":"王载文","skjcmc":"4-5","skzcList":["3","4","5","6","7","8","9","10","11","12","13","14","15","16"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"3-16","kssj":"10:45","jsmc":"羽毛球场1（来）","kkdlb":"1"}],"szkcfl":null,"dyrsbl":null,"ctsm":"与已选课程 “大学体育2” 冲突","sklsid":"3BBCE78C1188450783F7B3095B181205","skls":"王载文","kcjj":null,"xqid":"3","sfkfxk":"1","skdd":"羽毛球场1（来）<br>羽毛球场1（来）","xf":1,"txsfkxq":null,"kcxzmc":"公共必修课","zxs":32,"jx0404id":"202320242005144","xqmc":"来宾校区","jxdg_filename":null,"jx02id":"2AE48F62886D4ECFB69527AB88158CE4","xbyq":null,"xbyqmc":null,"sftk":null},{"kkdw":"26","kcxzm":"01","szkcflmc":null,"parentjx0404id":null,"fj_filename":null,"xnxq01id":"2023-2024-2","dwmc":"体育学院","ksfs":"3","pkrs":40,"ktmc":"智能制造[231-233]班,学前本科[232-233]班,汽服职教[231-233]班,机器人工程[231-233]班","tzdlb":"1","kcsx":"1","kch":"08DXT021","syrs":null,"kxh":null,"fzmc":"245极限飞盘","cfbs":null,"sksj":"1-2周 星期二 4-5节  <br>3-16周 星期二 4-5节  ","kcmc":"大学体育2","kkapList":[{"jssj":"12:10","jzwmc":"田径场（来宾）","jgxm":"蔡立健","skjcmc":"4-5","skzcList":["1","2"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"1-2","kssj":"10:45","jsmc":"田径运动场8（来）","kkdlb":"1"},{"jssj":"12:10","jzwmc":"田径场（来宾）","jgxm":"蔡立健","skjcmc":"4-5","skzcList":["3","4","5","6","7","8","9","10","11","12","13","14","15","16"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"3-16","kssj":"10:45","jsmc":"田径运动场8（来）","kkdlb":"1"}],"szkcfl":null,"dyrsbl":null,"ctsm":"与已选课程 “大学体育2” 冲突","sklsid":"0000372","skls":"蔡立健","kcjj":null,"xqid":"3","sfkfxk":"1","skdd":"田径运动场8（来）<br>田径运动场8（来）","xf":1,"txsfkxq":null,"kcxzmc":"公共必修课","zxs":32,"jx0404id":"202320242005145","xqmc":"来宾校区","jxdg_filename":null,"jx02id":"2AE48F62886D4ECFB69527AB88158CE4","xbyq":null,"xbyqmc":null,"sftk":null},{"kkdw":"26","kcxzm":"01","szkcflmc":null,"parentjx0404id":null,"fj_filename":null,"xnxq01id":"2023-2024-2","dwmc":"体育学院","ksfs":"3","pkrs":40,"ktmc":"智能制造[231-233]班,学前本科[232-233]班,汽服职教[231-233]班,机器人工程[231-233]班","tzdlb":"1","kcsx":"1","kch":"08DXT021","syrs":null,"kxh":null,"fzmc":"245篮球","cfbs":null,"sksj":"1-2周 星期二 4-5节  <br>3-16周 星期二 4-5节  ","kcmc":"大学体育2","kkapList":[{"jssj":"12:10","jzwmc":"综合体育馆","jgxm":"宋浩兵","skjcmc":"4-5","skzcList":["1","2"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"1-2","kssj":"10:45","jsmc":"综合体育馆后门","kkdlb":"1"},{"jssj":"12:10","jzwmc":"综合体育馆","jgxm":"宋浩兵","skjcmc":"4-5","skzcList":["3","4","5","6","7","8","9","10","11","12","13","14","15","16"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"3-16","kssj":"10:45","jsmc":"综合体育馆后门","kkdlb":"1"}],"szkcfl":null,"dyrsbl":null,"ctsm":"与已选课程 “大学体育2” 冲突","sklsid":"3DD5EA16E8E64902ADE61867A17D5403","skls":"宋浩兵","kcjj":null,"xqid":"3","sfkfxk":"1","skdd":"综合体育馆后门<br>综合体育馆后门","xf":1,"txsfkxq":null,"kcxzmc":"公共必修课","zxs":32,"jx0404id":"202320242005146","xqmc":"来宾校区","jxdg_filename":null,"jx02id":"2AE48F62886D4ECFB69527AB88158CE4","xbyq":null,"xbyqmc":null,"sftk":null},{"kkdw":"26","kcxzm":"01","szkcflmc":null,"parentjx0404id":null,"fj_filename":null,"xnxq01id":"2023-2024-2","dwmc":"体育学院","ksfs":"3","pkrs":40,"ktmc":"智能制造[231-233]班,学前本科[232-233]班,汽服职教[231-233]班,机器人工程[231-233]班","tzdlb":"1","kcsx":"1","kch":"08DXT021","syrs":null,"kxh":null,"fzmc":"245轮滑","cfbs":null,"sksj":"1-2周 星期二 4-5节  <br>3-16周 星期二 4-5节  ","kcmc":"大学体育2","kkapList":[{"jssj":"12:10","jzwmc":"陀螺场","jgxm":"胡定来","skjcmc":"4-5","skzcList":["1","2"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"1-2","kssj":"10:45","jsmc":"陀螺场","kkdlb":"1"},{"jssj":"12:10","jzwmc":"陀螺场","jgxm":"胡定来","skjcmc":"4-5","skzcList":["3","4","5","6","7","8","9","10","11","12","13","14","15","16"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"3-16","kssj":"10:45","jsmc":"陀螺场","kkdlb":"1"}],"szkcfl":null,"dyrsbl":null,"ctsm":"与已选课程 “大学体育2” 冲突","sklsid":"0000377","skls":"胡定来","kcjj":null,"xqid":"3","sfkfxk":"1","skdd":"陀螺场<br>陀螺场","xf":1,"txsfkxq":null,"kcxzmc":"公共必修课","zxs":32,"jx0404id":"202320242005147","xqmc":"来宾校区","jxdg_filename":null,"jx02id":"2AE48F62886D4ECFB69527AB88158CE4","xbyq":null,"xbyqmc":null,"sftk":null},{"kkdw":"26","kcxzm":"01","szkcflmc":null,"parentjx0404id":null,"fj_filename":null,"xnxq01id":"2023-2024-2","dwmc":"体育学院","ksfs":"3","pkrs":40,"ktmc":"智能制造[231-233]班,学前本科[232-233]班,汽服职教[231-233]班,机器人工程[231-233]班","tzdlb":"1","kcsx":"1","kch":"08DXT021","syrs":null,"kxh":null,"fzmc":"245啦啦操","cfbs":null,"sksj":"1-2周 星期二 4-5节  <br>3-16周 星期二 4-5节  ","kcmc":"大学体育2","kkapList":[{"jssj":"12:10","jzwmc":"综合体育馆","jgxm":"杨清华","skjcmc":"4-5","skzcList":["1","2"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"1-2","kssj":"10:45","jsmc":"T119-健美操室（2）","kkdlb":"1"},{"jssj":"12:10","jzwmc":"综合体育馆","jgxm":"杨清华","skjcmc":"4-5","skzcList":["3","4","5","6","7","8","9","10","11","12","13","14","15","16"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"3-16","kssj":"10:45","jsmc":"T119-健美操室（2）","kkdlb":"1"}],"szkcfl":null,"dyrsbl":null,"ctsm":"与已选课程 “大学体育2” 冲突","sklsid":"0000144","skls":"杨清华","kcjj":null,"xqid":"3","sfkfxk":"1","skdd":"T119-健美操室（2）<br>T119-健美操室（2）","xf":1,"txsfkxq":null,"kcxzmc":"公共必修课","zxs":32,"jx0404id":"202320242005148","xqmc":"来宾校区","jxdg_filename":null,"jx02id":"2AE48F62886D4ECFB69527AB88158CE4","xbyq":null,"xbyqmc":null,"sftk":null},{"kkdw":"26","kcxzm":"01","szkcflmc":null,"parentjx0404id":null,"fj_filename":null,"xnxq01id":"2023-2024-2","dwmc":"体育学院","ksfs":"3","pkrs":40,"ktmc":"智能制造[231-233]班,学前本科[232-233]班,汽服职教[231-233]班,机器人工程[231-233]班","tzdlb":"1","kcsx":"1","kch":"08DXT021","syrs":null,"kxh":null,"fzmc":"245花样跳绳","cfbs":null,"sksj":"1-2周 星期二 4-5节  <br>3-16周 星期二 4-5节  ","kcmc":"大学体育2","kkapList":[{"jssj":"12:10","jzwmc":"综合体育馆","jgxm":"周美玲","skjcmc":"4-5","skzcList":["1","2"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"1-2","kssj":"10:45","jsmc":"综合体育馆西侧","kkdlb":"1"},{"jssj":"12:10","jzwmc":"综合体育馆","jgxm":"周美玲","skjcmc":"4-5","skzcList":["3","4","5","6","7","8","9","10","11","12","13","14","15","16"],"xq":"2","kbjcmsid":"957E00F501F36400E0530100007F025A","kkzc":"3-16","kssj":"10:45","jsmc":"综合体育馆西侧","kkdlb":"1"}],"szkcfl":null,"dyrsbl":null,"ctsm":"与已选课程 “大学体育2” 冲突","sklsid":"CD286BB6F9C44B26A1DF8C434E3EBD61","skls":"周美玲","kcjj":null,"xqid":"3","sfkfxk":"1","skdd":"综合体育馆西侧<br>综合体育馆西侧","xf":1,"txsfkxq":null,"kcxzmc":"公共必修课","zxs":32,"jx0404id":"202320242005149","xqmc":"来宾校区","jxdg_filename":null,"jx02id":"2AE48F62886D4ECFB69527AB88158CE4","xbyq":null,"xbyqmc":null,"sftk":null}],"sEcho":"3","iTotalRecords":12,"iTotalDisplayRecords":12,"jfViewStr":""}
 *
 *
 */
public class CourseDataHandler {

    // 新建json
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * 获取课程的全部个数
     * @param json 服务器返回json数据
     * @return
     */
    @Deprecated()
    public static String getTotalRecords(String json) {
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        return jsonObject.get("iTotalRecords").getAsString();
    }

    /**
     *
     * @param json 服务器返回json数据
     * @return 实例化为course列表
     */
    public static ArrayList<Course> getCourses(String json) {
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        ArrayList<Course> list = new ArrayList<>();

        if (!jsonObject.has("aaData")) {
            return list;
        }

        JsonArray jsonArray = jsonObject.get("aaData").getAsJsonArray();
        for (JsonElement jo : jsonArray.asList()) {
            JsonObject object = jo.getAsJsonObject();

            Course course = new Course();
            // KCID
            course.setKCID(object.get("jx02id").getAsString());
            // KCID2
            course.setJxID(object.get("jx0404id").getAsString());
            // 上课校区
            course.setArea(object.get("xqmc").getAsString());
            // 课程名
            course.setName(object.get("kcmc").getAsString());
            // 老师
            course.setTeacher(object.get("skls").getAsString());

            JsonElement remain = object.get("syrs");
            if (remain.isJsonNull()) {
                course.setRemain("9999");
            } else {
                course.setRemain(remain.getAsString());
            }
            // 剩余人数

            // 学分
            course.setScore(object.get("xf").getAsString());
            // 类型
            JsonElement type = object.get("szkcflmc");
            if (type.isJsonNull()) {
                course.setType("Required");
                course.setRequiredCourse(true);
            } else {
                course.setType(type.getAsString());
                course.setRequiredCourse(false);
            }
            // 添加进list里
            list.add(course);
        }
        return list;
    }


}

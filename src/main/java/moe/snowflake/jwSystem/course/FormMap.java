package moe.snowflake.jwSystem.course;

import java.util.LinkedHashMap;

/**
 * 自定义表单类
 */
public class FormMap extends LinkedHashMap<String,String> {

    public FormMap() {
    }

    /**
     * @param sEcho  不知道
     * @param start  开始的 索引值
     * @param size 显示课程的个数
     */
    public void putRequiredFormData(String sEcho, int start, int size) {
        /*不知道是啥玩意*/
        this.put("sEcho", sEcho);
        /* 必修固定11列*/
        this.put("iColumns", "11");
        this.put("sColumns", "");
        this.put("iDisplayStart", String.valueOf(start));
        this.put("iDisplayLength", String.valueOf(size));
        /* 下面对应每列的数据 */
        this.put("mDataProp_0", "kch");
        this.put("mDataProp_1", "kcmc");
        this.put("mDataProp_2", "fzmc");
        this.put("mDataProp_3", "ktmc");
        this.put("mDataProp_4", "xf");
        this.put("mDataProp_5", "skls");
        this.put("mDataProp_6", "sksj");
        this.put("mDataProp_7", "skdd");
        this.put("mDataProp_8", "xqmc");
        this.put("mDataProp_9", "ctsm");
        this.put("mDataProp_10", "czOper");
    }

    /**
     * @param sEcho  不知道
     * @param start  开始的 索引值
     * @param size 显示课程的个数
     */
    public void putElectiveFormData(String sEcho, int start, int size) {
        /*不知道是啥玩意*/
        this.put("sEcho", sEcho);
        /* 选修固定13列*/
        this.put("iColumns", "13");
        this.put("sColumns", "");
        this.put("iDisplayStart", String.valueOf(start));
        this.put("iDisplayLength", String.valueOf(size));
        /* 下面对应每列的数据 */
        this.put("mDataProp_0", "kch");
        this.put("mDataProp_1", "kcmc");
        this.put("mDataProp_2", "xf");
        this.put("mDataProp_3", "skls");
        this.put("mDataProp_4", "sksj");
        this.put("mDataProp_5", "skdd");
        this.put("mDataProp_6", "xqmc");
        this.put("mDataProp_7", "xxrs");
        this.put("mDataProp_8", "xkrs");
        this.put("mDataProp_9", "syrs");
        this.put("mDataProp_10", "ctsm");
        this.put("mDataProp_11", "szkcflmc");
        this.put("mDataProp_12", "czOper");
    }

    public void putCourseReviewSave(){
        // WIP
    }


}

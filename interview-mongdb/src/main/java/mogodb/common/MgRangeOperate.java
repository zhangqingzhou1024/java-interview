package mogodb.common;

/**
 * 范围 正则 操作枚举类
 *
 * @author zhangqingzhou
 */
public enum MgRangeOperate {

    GT("$gt", ">"),
    GTE("$gte", ">="),        //>=
    EQ("$eq", "="),       // =
    NE("$ne", " !="),      //  !=
    LT("$lt", "<"),      //  <
    LTE("$lte", "<="),      //  <=
    IN("$in", "in"),       // in(后面的值为bson对象数组)
    NIN("$nin", "not i"),       // not in(后面的值为bson对象数组)
    REGEX("$regex", " 正则表达式"),   //  正则表达式
    OR("$or", "or");
    String value;
    String remark;

    private MgRangeOperate(String value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public String getVal() {

        return this.value;
    }

    public String getRemark() {

        return this.remark;
    }
}

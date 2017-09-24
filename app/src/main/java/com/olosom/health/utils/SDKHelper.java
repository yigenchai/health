package com.olosom.health.utils;

import com.olosom.health.entity.DiagnosisParams;
import com.olosom.health.entity.DiagnosisResult;

/**
 * 心功能 SDK 帮助类
 * <p>
 * Android 不直接控制设备，所有控制设备指令都交由 SDK PCB_SENDDATA() 函数来完成。
 * Android 只负责接收 设备 数据传递给 SDK 或者接收 SDK 回调命令传递给 设备。
 * <p>
 * 具体流程如下
 * Android 通过蓝牙接通设备，
 * 调用 SDK 初始化及配置一些参数(可先可后)，
 * SDK 初始化依次调用 cvfdInit(), setParameter(), setHumanParams()
 * <p>
 * 调用 SDK start() 函数，如果成功，此时 SDK 已经准备好处理数据，
 * 同时会回调 PCB_SENDDATA()，
 * Android 接到命令后通过蓝牙传递给 设备，此时设备启动检测。
 * <p>
 * 设备启动检测后会有数据通过蓝牙返回，Android 接到后直接调用 SDK addWavePoints() 函数
 * 将数据传递给 SDK 分析数据
 * <p>
 * SDK 每分析一次数据都会有相应回调返回，
 * 可能是放大／缩小等检测指令，走 PCB_SENDDATA() 回调
 * 或者处理结果，走 PCB_DATAPROC() 回调
 * <p>
 * 有处理结果回调会额外调用 receiveResult()，在此方法中处理了结果数据
 */

public class SDKHelper {

    /**
     * **************************** SDK 回调 Android 方法 *****************************
     *
     * 如无特殊说明，所有 String 都要转换成 byte[] 数组传递
     * 返回值为 0 代表成功
     */

    /**
     * SDK 状态回调，不同状态码会有不同数据返回，详情可见开发文档
     * 诊断成功状态也会走此回调，同时会走 receiveResult 方法
     * 所以建议此方法只负责显示状态码所代表含义，因为其他数据暂时对 Android 意义不大
     */
    public void PCB_DATAPROC(int dataType, byte[] pdata) {

    }

    /**
     * SDK 回调 Java 方法，基本上是控制设备指令
     * 此时 SDK 要控制设备，Android 接收到回调直接通过蓝牙将数据传递给设备
     *
     * @param pdata SDK 控制命令
     */
    public void PCB_SENDDATA(byte[] pdata) {
        
    }

    /**
     * SDK 诊断成功回调
     * 都是数组类型
     * <p>
     * 诊断参数数据
     *
     * @param paramsNameArr     参数名字
     * @param paramsValueArr    实际测量值
     * @param paramsTvArr       实际测量值字符串
     * @param paramsSvArr       标准值范围字符串
     * @param paramsPromptArr   参数分析后提示，”-“; “--"; “+”; “++”
     * @param paramsGradeArr    参数重要性等级，值越大临床意义越大
     * @param paramsUnitArr     参数单位
     * @param paramsTipsArr     参数解释
     * @param paramsFullNameArr 参数全名
     *                          <p>
     *                          诊断结果数据
     * @param name              结果名字
     * @param result            结果代码
     * @param resultex          结果解释
     */
    public void receiveResult(String[] paramsNameArr, float[] paramsValueArr,
                              String[] paramsTvArr, String[] paramsSvArr,
                              String[] paramsPromptArr, String[] paramsGradeArr,
                              String[] paramsUnitArr, String[] paramsTipsArr,
                              String[] paramsFullNameArr, String[] name, String[] result,
                              String[] resultex) {
        // 诊断参数数据模型
        DiagnosisParams paramsResult = new DiagnosisParams(
                paramsNameArr, paramsValueArr, paramsTvArr,
                paramsSvArr, paramsPromptArr, paramsGradeArr,
                paramsUnitArr, paramsTipsArr, paramsFullNameArr);
        // 诊断结果数据模型
        DiagnosisResult diagnosisResult = new DiagnosisResult(name,
                result, resultex);
    }

    /**
     * **************************** native 方法 *****************************
     *
     * 如无特殊说明，所有 String 都要转换成 byte[] 数组传递
     * 返回值为 0 代表成功
     */

    /**
     * SDK 初始化
     *
     * @return 成功返回 0
     */
    public native int cvfdInit();

    /**
     * 设置术语库路径
     *
     * @param path 路径值 String 转换成 byte数组
     * @param len  路径 byte 数组长度
     */
    public native void setParameter(byte[] path, int len);

    /**
     * 设置人体参数，详见 SDK 开发文档 HumanParams 结构体
     *
     * @param id          用户 id，SDK 初始化使用，Android 不需要
     * @param idLen       id 长度
     * @param szName      姓名
     * @param nameLen     姓名长度
     * @param nSex        性别
     * @param nAge        年龄
     * @param nHeight     身高
     * @param nWeight     体重
     * @param SP          收缩压
     * @param DP          舒张压
     * @param nMemberType 会员类型，无意义，默认传 3
     * @param reserved    无意义，默认传 0
     */
    public native int setHumanParams(byte[] id, int idLen, byte[] szName,
                                     int nameLen, int nSex, int nAge, int nHeight, int nWeight, int SP,
                                     int DP, int nMemberType, int reserved);

    /**
     * 添加测量波形数据
     */
    public native int addWavePoints(byte[] pdata, int len);

    /**
     * SDK 开始检测
     *
     * @param option 默认设置 0，具体含义查看 SDK 开发文档
     * @return 0 代表成功
     */
    public native int start(int option);

    /**
     * SDK 停止检测
     *
     * @param option 默认设置 0，具体含义查看 SDK 开发文档
     * @return 0 代表成功
     */
    public native int stop(int option);
}

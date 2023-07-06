package com.stickpoint.ddmusic.common.utils;

/**
 * @author fntp
 * @since 2023/5/26
 */
public class NetEasyUtil {

    /** wangyiyunyinyuebofang
     * 时间算法 时间分割算法 将网易云的隐藏歌曲时间进行提取然后计算时分秒
     * 最大单位是时 其次是分 其次是秒
     * @param sourceData 传入原来的时间
     * @return 返回
     */
    public static String getTimes(String sourceData) {
        long playTime = Long.parseLong(sourceData);
        long second = playTime / 1000;
        long hour=second/3600;
        long minite=second%3600/60;
        long sec=second%60;
        StringBuilder timeResult = new StringBuilder();
        if (hour>0){
            timeResult.append(hour).append("：").append(minite).append("：").append(sec);
        }else if (minite>0){
            if (minite>=10){
                if (sec>=10){
                    timeResult.append(minite).append("：").append(sec);
                }else {
                    timeResult.append(minite).append("：0").append(sec);
                }
            }else {
                if (sec>=10){
                    timeResult.append("0").append(minite).append("：").append(sec);
                }else {
                    timeResult.append("0").append(minite).append("：0").append(sec);
                }
            }
        }else if (sec>0){
            if (sec>=10) {
                timeResult.append("00：").append(sec);
            }else {
                timeResult.append("00：0").append(sec);
            }
        }
        return timeResult.toString();
    }

}

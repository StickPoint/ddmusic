package com.stickpoint.ddmusic.common.utils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

/**
 * description: CaptchaCodeUtil
 * 验证码工具类
 * @ClassName : CaptchaCodeUtil
 * @Date 2022/10/10 10:44
 * @Author puye(0303)
 * @PackageName com.stickpoint.ddmusic.common.utils
 */
public class CaptchaCodeUtil {
    /**
     * 验证码个数
     */
    private static final int CAPTCHA_CODE_SIZE = 4;
    /**
     * 干扰线条个数
     */
    private static final int CAPTCHA_CODE_LINE_SIZE = 8;
    /**
     * 干扰点个数
     */
    private static final int CAPTCHA_CODE_POINT_SIZE = 20;
    /**
     * 验证码内容
     */
    private static StringBuilder captchaCodeValue;

    /**
     * 获得验证码内容
     * @return 返回一个验证码内容
     */
    protected static String getCaptchaCodeValue(){
        if (Objects.isNull(captchaCodeValue)) {
            // TODO 系统弹窗告知一下，生成验证码出现问题
            return null;
        }
        return captchaCodeValue.toString();
    }

    /**
     * 生成随机验证码
     */
    protected static Canvas createRandomCaptchaImage(){
        Canvas canvas = new Canvas(120,40);
        captchaCodeValue = new StringBuilder();
        // 获得2d绘制图形环境
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        // 绘制一个矩形
        graphicsContext2D.setFill(Color.TRANSPARENT);
        graphicsContext2D.strokeRect(0,0,120,40);
        // 准备数据 定义一个数组存放验证码的随机数，我设置了数字和小写字母
        Object[] array = {0,1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        // 设置干扰线条
        for(var i = 0; i < CAPTCHA_CODE_LINE_SIZE; i ++ ){
            // 设置验证码随机数的随机颜色
            int randomNumberInRange = getRandomNumberInRange();
            graphicsContext2D.setStroke(Color.rgb(randomNumberInRange,randomNumberInRange,randomNumberInRange));
            graphicsContext2D.beginPath();
            graphicsContext2D.moveTo(Math.random()*120,Math.random()*40);
            graphicsContext2D.lineTo(Math.random()*120,Math.random()*40);
            graphicsContext2D.stroke();
        }
        // 绘制随机干扰点
        for(var i = 0;i <CAPTCHA_CODE_POINT_SIZE ;i ++){
            var x = Math.random() *120;
            var y = Math.random() *40;
            graphicsContext2D.setStroke(Color.rgb(getRandomNumberInRange(),getRandomNumberInRange(),getRandomNumberInRange()));
            graphicsContext2D.beginPath();
            graphicsContext2D.moveTo( x,y);
            graphicsContext2D.lineTo(x+1,y+1);
            graphicsContext2D.stroke();
        }
        //产生随机数
        for(var i = 0; i < CAPTCHA_CODE_SIZE; i ++){
            var deg = Math.random() * 180 * Math.PI / 180;
            var x = 20 + i * 20;
            var y = 20 + Math.random() * 10;
            //获取到一个随机的索引值
            var index = Math.floor(Math.random()*array.length);
            //获取到数组里面的随机的内容
            var txt = array[(int) index];
            //设置文字样式 使用默认字体
            graphicsContext2D.setFont(Font.font(18.0));
            graphicsContext2D.setFill(Color.rgb(getRandomNumberInRange(),getRandomNumberInRange(),getRandomNumberInRange()));
            graphicsContext2D.translate(x,y);
            graphicsContext2D.rotate(deg);
            String captchaStr = txt.toString();
            captchaCodeValue.append(captchaStr);
            graphicsContext2D.fillText(captchaStr,0,0);
            graphicsContext2D.rotate(-deg);
            graphicsContext2D.translate(-x,-y);
        }
        return canvas;
    }

    /**
     * 获得随机颜色RGB
     * @return 返回一个RGB范围内的数字 0-255
     */
    private static int getRandomNumberInRange() {
        try {
            Random r = SecureRandom.getInstanceStrong();
            return r.ints(0, (255 + 1)).limit(1).findFirst().orElse(0);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

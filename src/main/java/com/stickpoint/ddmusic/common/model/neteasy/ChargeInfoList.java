package com.stickpoint.ddmusic.common.model.neteasy;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Administrator
 */
public class ChargeInfoList implements Serializable {

    @Serial
    private static final long serialVersionUID = -8116195702382746945L;
    private long rate;
    private String chargeUrl;
    private String chargeMessage;
    private int chargeType;
    public void setRate(long rate) {
         this.rate = rate;
     }
     public long getRate() {
         return rate;
     }

    public void setChargeUrl(String chargeUrl) {
         this.chargeUrl = chargeUrl;
     }
     public String getChargeUrl() {
         return chargeUrl;
     }

    public void setChargeMessage(String chargeMessage) {
         this.chargeMessage = chargeMessage;
     }
     public String getChargeMessage() {
         return chargeMessage;
     }

    public void setChargeType(int chargeType) {
         this.chargeType = chargeType;
     }
     public int getChargeType() {
         return chargeType;
     }

}
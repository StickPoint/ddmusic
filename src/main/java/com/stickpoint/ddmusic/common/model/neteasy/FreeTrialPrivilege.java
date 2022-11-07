package com.stickpoint.ddmusic.common.model.neteasy;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Administrator
 */
public class FreeTrialPrivilege implements Serializable {

    @Serial
    private static final long serialVersionUID = 8542790772478595109L;
    private boolean resConsumable;
    private boolean userConsumable;
    private String listenType;
    public void setResConsumable(boolean resConsumable) {
         this.resConsumable = resConsumable;
     }
     public boolean getResConsumable() {
         return resConsumable;
     }

    public void setUserConsumable(boolean userConsumable) {
         this.userConsumable = userConsumable;
     }
     public boolean getUserConsumable() {
         return userConsumable;
     }

    public void setListenType(String listenType) {
         this.listenType = listenType;
     }
     public String getListenType() {
         return listenType;
     }

}
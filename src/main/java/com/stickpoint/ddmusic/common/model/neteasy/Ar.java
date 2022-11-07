package com.stickpoint.ddmusic.common.model.neteasy;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
/**
 * @author Administrator
 */
public class Ar implements Serializable {

    @Serial
    private static final long serialVersionUID = 5303959665310195894L;
    private int id;
    private String name;
    private List<String> tns;
    private List<String> alias;
    private List<String> alia;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setTns(List<String> tns) {
         this.tns = tns;
     }
     public List<String> getTns() {
         return tns;
     }

    public void setAlias(List<String> alias) {
         this.alias = alias;
     }
     public List<String> getAlias() {
         return alias;
     }

    public void setAlia(List<String> alia) {
         this.alia = alia;
     }
     public List<String> getAlia() {
         return alia;
     }

}
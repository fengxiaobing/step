package com.example.step.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by RF
 * on 2018/1/11.
 */
@Entity
public class StepEntity {
    @Id(autoincrement = true)
    private Long id;
    private String CurDate;
    private String Steps;
    public String getSteps() {
        return this.Steps;
    }
    public void setSteps(String Steps) {
        this.Steps = Steps;
    }
    public String getCurDate() {
        return this.CurDate;
    }
    public void setCurDate(String CurDate) {
        this.CurDate = CurDate;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1451528673)
    public StepEntity(Long id, String CurDate, String Steps) {
        this.id = id;
        this.CurDate = CurDate;
        this.Steps = Steps;
    }
    @Generated(hash = 1646539754)
    public StepEntity() {
    }

  

}

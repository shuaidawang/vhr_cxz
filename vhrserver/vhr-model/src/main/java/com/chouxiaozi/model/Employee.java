package com.chouxiaozi.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.chouxiaozi.vo.CustomConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.poi.ss.usermodel.FillPatternType;

import java.io.Serializable;
import java.util.Date;

// 头背景设置成红色 IndexedColors.RED.getIndex()
@HeadStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 17)
public class Employee implements Serializable {
    @ExcelIgnore
    private Integer id;

    @ExcelProperty(value = "姓名", index = 0)
    private String name;

    @ExcelProperty(value = "性别", index = 1)
    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "生日", index = 3)
    private Date birthday;
    @ExcelProperty(value = "身份证号", index = 4)
    private String idCard;
    @ExcelProperty(value = "婚姻状况", index = 5)
    private String wedlock;
    @ExcelIgnore
    private Integer nationId;

    @ExcelProperty(value = "籍贯", index = 7)
    private String nativePlace;
    @ExcelIgnore
    private Integer politicId;

    @ExcelProperty(value = "邮箱", index = 9)
    private String email;

    @ExcelProperty(value = "电话号码", index = 10)
    private String phone;

    @ExcelProperty(value = "联系地址", index = 11)
    private String address;
    @ExcelIgnore
    private Integer departmentId;
    @ExcelIgnore
    private Integer jobLevelId;
    @ExcelIgnore
    private Integer posId;

    @ExcelProperty(value = "聘用形式", index = 15)
    private String engageForm;

    @ExcelProperty(value = "最高学历", index = 16)
    private String tiptopDegree;

    @ExcelProperty(value = "所属专业", index = 17)
    private String specialty;

    @ExcelProperty(value = "毕业院校", index = 18)
    private String school;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "入职日期", index = 19)
    private Date beginDate;

    @ExcelProperty(value = "在职状态", index = 21)
    private String workState;

    @ExcelProperty(value = "工号", index = 2)
    private String workId;

    @ExcelProperty(value = "合同期限", index = 25)
    private Double contractTerm;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "转正日期", index = 22)
    private Date conversionTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "离职日期", index = 20)
    private Date notworkDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "合同起始日期", index = 23)
    private Date beginContract;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "合同终止日期", index = 24)
    private Date endContract;

    @ExcelIgnore
    @ExcelProperty(value = "工龄", index = 26)
    private Integer workAge;

    @ExcelProperty(value = "民族", converter = CustomConverter.class, index = 6)
    private Nation nation;

    @ExcelProperty(value = "面貌", converter = CustomConverter.class, index = 8)
    private Politicsstatus politicsstatus;

    @ExcelProperty(value = "部门", converter = CustomConverter.class, index = 12)
    private Department department;

    @ExcelProperty(value = "职级", converter = CustomConverter.class, index = 13)
    private JobLevel jobLevel;

    @ExcelProperty(value = "职位", converter = CustomConverter.class, index = 14)
    private Position position;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getWedlock() {
        return wedlock;
    }

    public void setWedlock(String wedlock) {
        this.wedlock = wedlock == null ? null : wedlock.trim();
    }

    public Integer getNationId() {
        return nationId;
    }

    public void setNationId(Integer nationId) {
        this.nationId = nationId;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace == null ? null : nativePlace.trim();
    }

    public Integer getPoliticId() {
        return politicId;
    }

    public void setPoliticId(Integer politicId) {
        this.politicId = politicId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(Integer jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public String getEngageForm() {
        return engageForm;
    }

    public void setEngageForm(String engageForm) {
        this.engageForm = engageForm == null ? null : engageForm.trim();
    }

    public String getTiptopDegree() {
        return tiptopDegree;
    }

    public void setTiptopDegree(String tiptopDegree) {
        this.tiptopDegree = tiptopDegree == null ? null : tiptopDegree.trim();
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty == null ? null : specialty.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public String getWorkState() {
        return workState;
    }

    public void setWorkState(String workState) {
        this.workState = workState == null ? null : workState.trim();
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId == null ? null : workId.trim();
    }

    public Double getContractTerm() {
        return contractTerm;
    }

    public void setContractTerm(Double contractTerm) {
        this.contractTerm = contractTerm;
    }

    public Date getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(Date conversionTime) {
        this.conversionTime = conversionTime;
    }

    public Date getNotworkDate() {
        return notworkDate;
    }

    public void setNotworkDate(Date notworkDate) {
        this.notworkDate = notworkDate;
    }

    public Date getBeginContract() {
        return beginContract;
    }

    public void setBeginContract(Date beginContract) {
        this.beginContract = beginContract;
    }

    public Date getEndContract() {
        return endContract;
    }

    public void setEndContract(Date endContract) {
        this.endContract = endContract;
    }

    public Integer getWorkAge() {
        return workAge;
    }

    public void setWorkAge(Integer workAge) {
        this.workAge = workAge;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public Politicsstatus getPoliticsstatus() {
        return politicsstatus;
    }

    public void setPoliticsstatus(Politicsstatus politicsstatus) {
        this.politicsstatus = politicsstatus;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public JobLevel getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(JobLevel jobLevel) {
        this.jobLevel = jobLevel;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
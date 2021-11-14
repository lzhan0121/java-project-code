package com.codeant.team.service;

import com.codeant.team.domain.Architect;
import com.codeant.team.domain.Designer;
import com.codeant.team.domain.Employee;
import com.codeant.team.domain.Programmer;

public class TeamService {
    private static int counter = 1; //给memberid赋值使用
    private final int MAX_MEMBER = 5;
    private Programmer[] team = new Programmer[MAX_MEMBER];  //保存开发团队成员
    private int total; //记录团队成员的实际人数

    public Programmer[] getTeam() {
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < team.length; i++) {
            team[i] = this.team[i];
        }
        return team;
    }

    public void addMember(Employee e) throws TeamException {
        //成员已满，无法添加
        if (total >= MAX_MEMBER) {
            throw new TeamException("成员已满，无法添加");
        }
        // 该成员不是开发人员，无法添加
        if (!(e instanceof Programmer)) {
            throw new TeamException("该成员不是开发人员，无法添加");
        }
        // 该员工已在本开发团队中
        if (isExist(e)) {
            throw new TeamException("该员工已在本开发团队中");
        }
        // 该员工已是某团队成员或正在休假
        Programmer p = (Programmer) e;
        if ("BUSY".equals(p.getStatus().getNAME())) {
            throw new TeamException("该员工已是某团队成员");
        } else if ("VACATON".equals(p.getStatus().getNAME())) {
            throw new TeamException("该员工正在休假");
        }

        // 获取team中成员架构师，设计师，程序员的人数
        int numOfArch = 0;
        int numOfDes = 0;
        int numOfPro = 0;
        for (int i = 0; i < total; i++) {
            if (e instanceof Architect) {
                numOfArch++;
            } else if (e instanceof Designer) {
                numOfDes++;
            } else if (e instanceof Programmer) {
                numOfPro++;
            }
        }
        // 团队职位类别限制问题
        if (p instanceof Architect) {
            if (numOfArch >= 1) {
                throw new TeamException("团队中至多只能有一名架构师");
            } else if (p instanceof Designer) {
                if (numOfDes >= 2) {
                    throw new TeamException("团队中至多只能有两名设计师");
                }
            } else if (p instanceof Programmer) {
                if (numOfPro >= 3) {
                    throw new TeamException("团队中至多只能有三名程序员");
                }
            }
        }

        //将p（e）添加到现有的team中
        team[total++] = p;
        //p的属性赋值
        p.setStatus(Status.BUSY);
        p.setMemberid(counter++);
    }

    /*判断指定员工是否已经存在于现有的开发团队中*/
    private boolean isExist(Employee e) {
        for (int i = 0; i < total; i++) {
            if (e.getId() == team[i].getId()) {
                return true;
            }
        }
        return false;
    }

    /*从团队中删除成员*/
    public void removeMember(int memberId) throws TeamException {
        int i = 0;

        //依次把成员状态改成FREE
        for (; i < total; i++) {
            if (team[i].getMemberid() == memberId) {
                team[i].setStatus(Status.FREE);
                break;
            }
        }

        //如果未找到指定的memberId
        if (i == total) {
            throw new TeamException("未找到指定的memberId成员，删除失败");
        }

        //后一个元素覆盖前一个元素，实现删除操作
        for (int j = i + 1; j < total; j++) {
            team[j - 1] = team[j];
        }
        team[--total] = null;

    }

}

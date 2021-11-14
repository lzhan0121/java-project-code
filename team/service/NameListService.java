package com.codeant.team.service;
/*
负责将Data中的数据封装到Employee[]中，同时提供相关操作Employee[]的方法
 */

import com.codeant.team.domain.*;

public class NameListService {
    private Employee[] employees;

    public NameListService() {
        //根据Data类构建不同的对象
        employees = new Employee[Data.EMPLOYEES.length];

        for (int i = 0; i < employees.length; i++) {
            //获取员工的类型
            int type = Integer.parseInt(Data.EMPLOYEES[i][0]);
            //获取Employee的四个基本信息
            int id = Integer.parseInt(Data.EMPLOYEES[i][1]);
            String name = Data.EMPLOYEES[i][2];
            int age = Integer.parseInt(Data.EMPLOYEES[i][3]);
            double salary = Integer.parseInt(Data.EMPLOYEES[i][4]);
            //Employee的额外信息
            Equipment equipment;
            double bonus;
            int stock;

            switch (type) {
                case Data.EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;

                case Data.PROGRAMMER:
                    equipment = createEquipment(i);
                    employees[i] = new Programmer(id, name, age, salary, equipment);
                    break;

                case Data.DESIGNER:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;

                case Data.ARCHITECT:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
                    stock = Integer.parseInt(Data.EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
                    break;
            }
        }
    }

    //获取指定的index上员工的设备
    private Equipment createEquipment(int index) {
        int type = Integer.parseInt(Data.EQUIPMENTS[index][0]);
        switch (type) {
            case Data.PC://21
                return new PC(Data.EQUIPMENTS[index][1], Data.EQUIPMENTS[index][2]);
            case Data.NOTEBOOK://22
                return new NoteBook(Data.EQUIPMENTS[index][1], Integer.parseInt(Data.EQUIPMENTS[index][2]));
            case Data.PRINTER://23
                return new Printer(Data.EQUIPMENTS[index][1], Data.EQUIPMENTS[index][2]);
        }
        return null;
    }

    public Employee[] getAllEmployees() {
        return employees;
    }

    public Employee getEmployee(int id) throws TeamException {
        for (int i=0;i<employees.length;i++){
            if(employees[i].getId() == id){
                return employees[i];
            }
        }

        throw new TeamException("找不到指定的员工");
    }
}

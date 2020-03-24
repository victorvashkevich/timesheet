package vvv.timesheet.service;

import vvv.timesheet.model.Enterprise;

import java.util.List;

public interface EnterpriseService
{
        List<Enterprise> allEnterprises();
        void add(Enterprise enterprise);
        void delete(Enterprise enterprise);
        void edit(Enterprise enterprise);
        Enterprise getByCode(String code);
}

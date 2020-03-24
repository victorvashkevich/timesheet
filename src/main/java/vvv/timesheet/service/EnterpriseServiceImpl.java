package vvv.timesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vvv.timesheet.dao.EnterpriseDAO;
import vvv.timesheet.dao.EnterpriseDAOImpl;
import vvv.timesheet.model.Enterprise;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    private EnterpriseDAO enterpriseDAO;

    @Autowired
    public void setEnterpriseDAO(EnterpriseDAO enterpriseDAO) {
        this.enterpriseDAO = enterpriseDAO;
    }

    @Override
    @Transactional
    public List<Enterprise> allEnterprises() {
        return enterpriseDAO.allEnterprises();
    }

    @Override
    @Transactional
    public void add(Enterprise enterprise) {
        enterpriseDAO.add(enterprise);
    }

    @Override
    @Transactional
    public void delete(Enterprise enterprise) {
        enterpriseDAO.delete(enterprise);
    }

    @Override
    @Transactional
    public void edit(Enterprise enterprise) {
        enterpriseDAO.edit(enterprise);
    }

    @Override
    @Transactional
    public Enterprise getByCode(String code) {
        return enterpriseDAO.getByCode(code);
    }
}

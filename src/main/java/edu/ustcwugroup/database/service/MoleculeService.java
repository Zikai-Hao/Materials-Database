package edu.ustcwugroup.database.service;

import edu.ustcwugroup.database.dao.MoleculeDAO;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Haozk on 2019/12/12
 */
@Service
public class MoleculeService {

    @Autowired
    MoleculeDAO moleculeDAO;

    public int updateMolecule(int id,String elements,String formula){
        return moleculeDAO.updateMolucule(elements,formula,id);
    }
}

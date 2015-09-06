package by.dak.cutting.doors.mech.db;

import by.dak.cutting.doors.mech.DoorMechDef;
import by.dak.cutting.doors.mech.DoorMechType;
import by.dak.persistence.dao.impl.GenericDaoImpl;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vishutinov
 * Date: 26.08.2009
 * Time: 16:04:52
 * To change this template use File | Settings | File Templates.
 */
public class DoorMechTypeDaoImpl extends GenericDaoImpl<DoorMechType> implements DoorMechTypeDao
{
    @Override
    public List<DoorMechType> findAllBy(DoorMechDef doorMechDef)
    {
        return findByCriteria(Restrictions.eq("doorMechDef", doorMechDef));
    }
}

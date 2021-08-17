package service.impl;

import dao.AddressDao;
import dao.impl.AddressDaoImpl;
import entity.Address;
import service.AddressService;

import java.sql.SQLException;
import java.util.List;

public class AddressServiceImpl implements AddressService {
    @Override
    public List<Address> findAddressByUid(int uid) throws SQLException {
        AddressDao addressDao = new AddressDaoImpl();
        List<Address> addressList = addressDao.selectAddressByUid(uid);
        return addressList;

    }

    @Override
    public void saveAddress(Address address) throws SQLException {
        AddressDao addressDao = new AddressDaoImpl();
        addressDao.insertAddress(address);
    }

    @Override
    public void deleteAddress(String aid) throws SQLException {
        AddressDao addressDao = new AddressDaoImpl();
        addressDao.deleteAddress(aid);
    }

    @Override
    public void setAddressToDefault(String aid, int uid) throws SQLException {
        AddressDao addressDao = new AddressDaoImpl();
        addressDao.setAddressToDefault(aid);
        addressDao.setAddressToCommons(aid,uid);
    }

    @Override
    public void updateByAid(Address address) throws SQLException {
        AddressDao addressDao = new AddressDaoImpl();
        addressDao.upadteByAid(address);
    }
}

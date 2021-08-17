package dao;

import entity.Address;

import java.sql.SQLException;
import java.util.List;

public interface AddressDao {
    List<Address> selectAddressByUid(int uid) throws SQLException;

    void insertAddress(Address address) throws SQLException;

    void deleteAddress(String aid) throws SQLException;

    void setAddressToDefault(String aid) throws SQLException;

    void setAddressToCommons(String aid, int uid) throws SQLException;

    void upadteByAid(Address address) throws SQLException;
}

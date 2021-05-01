package org.jzz.spbootDemo.Service;

import org.jzz.spbootDemo.model.Address;
import org.jzz.spbootDemo.model.AddressRepository;
import org.jzz.spbootDemo.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("addressService")
@Transactional(readOnly = false)
public class AddressService {
	@Autowired
	private AddressRepository addressDao;
	@Autowired
	private UserRepository userDao;

	public void deleteById(int id) {
		addressDao.deleteById(id);
	}
	
	public void deleteByEntity(Address address) {
		address.getUser().getAddresss().remove(address);
		addressDao.delete(address);
	}
}

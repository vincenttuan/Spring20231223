package spring.core.session4.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookThreeServiceImpl implements BookThreeService {
	
	@Autowired
	private BookOneService bookOneService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void buyThree(String username, Integer bookId) {
		bookOneService.buyOne(username, bookId);
		bookOneService.buyOne(username, bookId);
		bookOneService.buyOne(username, bookId);
	}

}

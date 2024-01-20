package spring.core.session4.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookThreeServiceImpl implements BookThreeService {
	
	@Autowired
	private BookOneService bookOneService;
	
	@Override
	public void buyThree(String username, Integer bookId) {
		bookOneService.buyOne(username, bookId);
		bookOneService.buyOne(username, bookId);
		bookOneService.buyOne(username, bookId);
	}

}

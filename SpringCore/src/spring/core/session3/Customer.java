package spring.core.session3;

import java.util.List;

import lombok.Data;

@Data
public class Customer {
	private Integer id;
	private String name;
	private List<Product> products;
}

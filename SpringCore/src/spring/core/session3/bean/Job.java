package spring.core.session3.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Job {
	@Value("301")
	private Integer id;
	@Value("Programming")
	private String content;
}

package spring.core.session3.bean;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component(value="work") // @Component("work"), @Named("work")
@Scope("singleton") // prototype
public class Job {
	@Value("301")
	private Integer id;
	@Value("Programming")
	private String content;
}

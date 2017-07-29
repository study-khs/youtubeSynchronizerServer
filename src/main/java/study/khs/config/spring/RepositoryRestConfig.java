package study.khs.config.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import study.khs.api.channel.domain.Channel;
import study.khs.api.channel.domain.Video;

@Configuration
public class RepositoryRestConfig extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Channel.class, Video.class);
	}
}

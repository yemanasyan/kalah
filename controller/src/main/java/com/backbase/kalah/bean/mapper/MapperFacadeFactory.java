package com.backbase.kalah.bean.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Yengibar Manasyan
 */
@Component
public class MapperFacadeFactory {

	@Bean(name = "mapperFacade")
	public MapperFacade getObject() {
		return new DefaultMapperFactory.Builder().build().getMapperFacade();
	}
}

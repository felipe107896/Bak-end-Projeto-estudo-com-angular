package com.pessoa.converter.vo;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {
	
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	
	public static <O , D> D parseObject(O origin, Class<D> destinitaion) {
		return mapper.map(origin, destinitaion);
	}
	
	public static <O , D> List<D> parseListObjects(List<O> origin, Class<D> destinitaion) {
		List<D> destinationObjects = new ArrayList<>();
		for (Object o : origin) {
			destinationObjects.add(mapper.map(o, destinitaion));
		}
		
		return destinationObjects;
	}
}

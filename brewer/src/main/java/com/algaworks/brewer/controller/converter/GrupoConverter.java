package com.algaworks.brewer.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Grupo;

public class GrupoConverter implements Converter<String, Grupo>{

	@Override
	public Grupo convert(String id) {
		if (!StringUtils.isEmpty(id)) {
			Grupo grupo = new Grupo();
			grupo.setCodigo(Long.valueOf(id));
			return grupo;
		}
		return null;
	}

}

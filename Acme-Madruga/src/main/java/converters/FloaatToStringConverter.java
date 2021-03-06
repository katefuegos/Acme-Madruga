/*
 * CurriculaToStringConverter.java
 * 
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Procession;

@Component
@Transactional
public class FloaatToStringConverter implements Converter<Procession, String> {

	@Override
	public String convert(final Procession procession) {
		String result;

		if (procession == null)
			result = null;
		else
			result = String.valueOf(procession.getId());

		return result;
	}

}

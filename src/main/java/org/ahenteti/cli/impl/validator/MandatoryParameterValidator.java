package org.ahenteti.cli.impl.validator;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import org.apache.commons.lang3.StringUtils;

public class MandatoryParameterValidator implements IParameterValidator {

    public void validate(String name, String value) {
        if (StringUtils.isBlank(value)) {
            throw new ParameterException("The parameter " + name + " is mandatory");
        }
    }
}

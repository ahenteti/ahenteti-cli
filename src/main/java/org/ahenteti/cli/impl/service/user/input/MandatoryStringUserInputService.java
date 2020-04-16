package org.ahenteti.cli.impl.service.user.input;

import com.beust.jcommander.converters.StringConverter;
import org.ahenteti.cli.api.service.IUserInputService;
import org.ahenteti.cli.impl.validator.MandatoryParameterValidator;

public class MandatoryStringUserInputService implements IUserInputService<String> {

    private final AbstractUserInputService<String> userInputService;

    public MandatoryStringUserInputService() {
        UserInputServiceFactory<String> factory = new UserInputServiceFactory<>();
        userInputService = factory.create(new StringConverter(), new MandatoryParameterValidator());
    }

    @Override
    public String convert(String input) {
        return userInputService.convert(input);
    }

    @Override
    public boolean isValid(String input) {
        return userInputService.isValid(input);
    }

    @Override
    public String getValidUserInput(String question) {
        return userInputService.getValidUserInput(question);
    }
}

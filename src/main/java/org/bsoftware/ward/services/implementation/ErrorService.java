package org.bsoftware.ward.services.implementation;

import org.bsoftware.ward.dto.Dto;
import org.bsoftware.ward.dto.implementation.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;

@Service
public class ErrorService implements org.bsoftware.ward.services.Service
{
    @Override
    @SuppressWarnings(value = {"unchecked", "ConstantConditions"})
    public <T extends Dto> T get(HttpServletResponse httpServletResponse)
    {
        ErrorDto errorDto = new ErrorDto();
        final HttpStatus httpStatus = HttpStatus.resolve(httpServletResponse.getStatus());

        switch (httpStatus)
        {
            case NOT_FOUND:
            {
                errorDto.setCode(httpServletResponse.getStatus());
                errorDto.setTitle("Oops! You're lost...");
                errorDto.setExplanation("<div>The page you are looking for does not exist.</div><div>How you got here is a mystery.</div>");
                errorDto.setAdvice("You're drunk, <a href = \"/\">go home</a>");
                break;
            }
            case METHOD_NOT_ALLOWED:
            {
                errorDto.setCode(httpServletResponse.getStatus());
                errorDto.setTitle("How to say it...");
                errorDto.setExplanation("<div>This type of method is not allowed here.</div><div>Do not try to shove a square into a round hole.</div>");
                errorDto.setAdvice("You're drunk, <a href = \"/\">go home</a>");
                break;
            }
            default:
            {
                errorDto.setCode(500);
                errorDto.setTitle("We have a problem...");
                errorDto.setExplanation("<div>While we go down this rabbit hole.</div><div>Please go outside and enjoy the sunshine.</div>");
                errorDto.setAdvice("Open an issue on <a href = \"https://github.com/B-Software/Ward\">Github</a>");
            }
        }

        return (T) errorDto;
    }
}
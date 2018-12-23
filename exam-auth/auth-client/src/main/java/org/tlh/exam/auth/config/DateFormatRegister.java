package org.tlh.exam.auth.config;

import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.util.StringUtils;

/**
 * @author huping
 * @desc
 * @date 18/10/14
 */
public class DateFormatRegister implements FeignFormatterRegistrar {

    private static final String DEFAULT_PATTERN="yyyy-MM-dd HH:mm:ss";

    private String pattern;

    @Override
    public void registerFormatters(FormatterRegistry registry) {
        if(StringUtils.isEmpty(pattern)){
            this.pattern=DEFAULT_PATTERN;
        }
        registry.addFormatter(new DateFormatter(this.pattern));
    }
}

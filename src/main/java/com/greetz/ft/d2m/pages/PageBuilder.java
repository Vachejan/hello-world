package com.greetz.ft.d2m.pages;

import com.greetz.ft.d2m.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Optional;

/**
 * Created by hocks on 11-4-2017.
 */
public class PageBuilder {

    private static final Configuration configuration = Configuration.instance();

    public static <T extends Page> Builder<T> page(Class<T> pageClass, WebDriver webDriver) {
        return new Builder<>(pageClass, webDriver);
    }

    public static class Builder<T extends Page> {
        private final Class<T> pageClass;
        private final WebDriver webDriver;
        private String url;
        private Integer maxDelay = 5000;

        public Builder(Class<T> pageClass, WebDriver webDriver) {
            this.pageClass = pageClass;
            this.webDriver = webDriver;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder maxDelay(int delay) {
            this.maxDelay = delay;
            return this;
        }

        public T build() {
            try {
                final T instance = pageClass.newInstance();
                instance.setWebDriver(webDriver);
                instance.setSiteUrl(Optional.ofNullable(url).orElse(configuration.getSiteUrl()));
                instance.setMaxDelayTime(maxDelay);
                PageFactory.initElements(webDriver, instance);
                return instance;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}

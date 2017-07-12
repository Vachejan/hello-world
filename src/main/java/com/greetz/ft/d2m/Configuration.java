package com.greetz.ft.d2m;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by hocks on 11-4-2017.
 */
public class Configuration {

    private static final String DEFAULT_BROWSER = "chrome";
    private static final String DEFAULT_BROWSER_VERSION = "latest";
    private static final String DEFAULT_SITE_URL = "https://www.ucom.am/";

    private String username;

    private String accessKey;

    private Integer implicitlyWait;

    private Integer idleTimeout;

    private Integer maxDelayTime;

    private String browser;

    private String browserVersion;

    private String platform;

    private Boolean isLocal;

    private String siteUrl;
    private String build;

    @Override
    public String toString() {
        return "Configuration{" +
                "username='" + username + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", implicitlyWait=" + implicitlyWait +
                ", idleTimeout=" + idleTimeout +
                ", maxDelayTime=" + maxDelayTime +
                ", browser='" + browser + '\'' +
                ", browserVersion='" + browserVersion + '\'' +
                ", platform='" + platform + '\'' +
                ", isLocal=" + isLocal +
                ", siteUrl='" + siteUrl + '\'' +
                ", build='" + build + '\'' +
                '}';
    }

    public static Configuration instance() {

        final Configuration configuration = new Configuration();
        configuration.setUsername(System.getenv("SAUCE_USER_NAME"));
        configuration.setAccessKey(System.getenv("SAUCE_ACCESS_KEY"));
        configuration.setImplicitlyWait(Integer.parseInt(Optional.ofNullable(System.getProperty("implicit-wait")).orElse("15")));
        configuration.setIdleTimeout(Integer.parseInt(Optional.ofNullable(System.getProperty("idle-timeout")).orElse("125")));
        configuration.setMaxDelayTime(Integer.parseInt(Optional.ofNullable(System.getProperty("delay")).orElse("15")));
        configuration.setBrowser(Optional.ofNullable(System.getenv("SELENIUM_BROWSER")).orElse(DEFAULT_BROWSER));
        configuration.setBrowserVersion(Optional.ofNullable(System.getenv("SELENIUM_VERSION")).orElse(DEFAULT_BROWSER_VERSION));
        configuration.setPlatform(Optional.ofNullable(System.getenv("SELENIUM_PLATFORM")).orElse(System.getProperty("os.name")));
        configuration.setSiteUrl(Optional.ofNullable(System.getenv("SELENIUM_STARTING_URL")).orElse(DEFAULT_SITE_URL));
        configuration.setBuild(Optional.ofNullable(System.getenv("SAUCE_BAMBOO_BUILDNUMBER")).orElse("na"));
        configuration.setIsLocal(Objects.isNull(System.getenv("SAUCE_ACCESS_KEY")));
        System.out.println(configuration);
        return configuration;
    }


    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getAccessKey() {
        return accessKey;
    }

    private void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public Integer getImplicitlyWait() {
        return implicitlyWait;
    }

    private void setImplicitlyWait(Integer implicitlyWait) {
        this.implicitlyWait = implicitlyWait;
    }

    public Integer getIdleTimeout() {
        return idleTimeout;
    }

    private void setIdleTimeout(Integer idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public String getBrowser() {
        return browser;
    }

    private void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    private void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getPlatform() {
        return platform;
    }

    private void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getMaxDelayTime() {
        return maxDelayTime;
    }

    private void setMaxDelayTime(Integer maxDelayTime) {
        this.maxDelayTime = maxDelayTime;
    }

    public Boolean getIsLocal() {
        return isLocal;
    }

    private void setIsLocal(boolean isLocal) {
        this.isLocal = isLocal;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    private void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getBuild() {

        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }
}

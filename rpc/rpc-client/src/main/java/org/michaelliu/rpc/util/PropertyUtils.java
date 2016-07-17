package org.michaelliu.rpc.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ResourceBundle;

/**
 * Created by Michael on 6/28/16.
 */
public class PropertyUtils {

    private static final Log log = LogFactory.getLog(PropertyUtils.class);

    private static ResourceBundle statusInfoBundle = null;

    static {
        log.info("==初始化配置文件==");
        initPropertyContext();
    }

    /**
     * Initialization of containers of property file systems
     */
    private static void initPropertyContext() {
        statusInfoBundle = ResourceBundle.getBundle("property/statusinfo");
    }

    public static String getStatusInfo(String key) {
        return getValue(statusInfoBundle, key);
    }

    private static String getValue(ResourceBundle resourceBundle, String key) {
        String value = "";
        if (null != resourceBundle && StringUtils.isNotEmpty(key)) {
            value = resourceBundle.getString(key);
        }
        return value;
    }

}

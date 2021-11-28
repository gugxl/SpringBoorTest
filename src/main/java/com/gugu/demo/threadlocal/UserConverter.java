package com.gugu.demo.threadlocal;

import com.gugu.demo.util.StringUtils;

/**
 * @author Administrator
 * @Classname UserConverter
 * @Description TODO
 * @Date 2021/11/28 12:41
 */
public class UserConverter {
    public static ResultDO toDo(User user){
        ResultDO resultDO = new ResultDO();
        resultDO.setUser(user);
        BaseSignatureRequest baseSignatureRequest = ThreadLocalCache.baseSignatureRequestThreadLocal.get();
        String device = baseSignatureRequest.getDevice();
        if (StringUtils.pathEquals(device, "ios")){
            resultDO.setLink("https://itunes.apple.com/us/app/**");
        }else {
            resultDO.setLink("https://play.google.com/store/apps/details?id=***");
        }
        return resultDO;
    }
}

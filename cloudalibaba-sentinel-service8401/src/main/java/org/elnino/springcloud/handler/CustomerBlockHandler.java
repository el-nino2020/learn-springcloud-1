package org.elnino.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.elnino.springcloud.entity.CommonResult;

public class CustomerBlockHandler {
    // 必须是静态方法，且返回值需要和业务方法一致
    public static CommonResult handleException(BlockException exception) {
        return new CommonResult(2020, "自定义的限流处理信息......CustomerBlockHandler");
    }
}
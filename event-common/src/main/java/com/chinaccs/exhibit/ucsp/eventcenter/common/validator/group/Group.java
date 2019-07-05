/**
 * Zhu Jiawei zhujiawei@sunseaaiot.com
 *
 *
 *
 *
 */

package com.chinaccs.exhibit.ucsp.eventcenter.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 *
 * @author Zhu Jiawei zhujiawei@sunseaaiot.com
 * @since 1.0.0
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}

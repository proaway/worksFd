/**
 * AbstractClass.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion;

import com.fd.bean.AbstractBean;

/**
 * @CreateDate:  2013-4-1 下午2:39:32 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public abstract class AbstractClass<T extends AbstractBean>  {
	  public <T> void foo(Class<T> clazz) {
		        // DO SOMETHING
		   }
		   public void bar() {
	      foo(String.class);
		   }
}

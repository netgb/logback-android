/**
 * Logback: the generic, reliable, fast and flexible logging framework.
 * 
 * Copyright (C) 2000-2009, QOS.ch
 * 
 * This library is free software, you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation.
 */
package ch.qos.logback.classic.spi;

import java.io.Serializable;
import java.util.Arrays;

public class ThrowableProxyVO implements IThrowableProxy, Serializable {

  private static final long serialVersionUID = 685387990886325422L;
  
  private String className;
  private String message;
  private int commonFramesCount;
  private ThrowableDataPoint[] throwableDataPointArray;
  private IThrowableProxy cause;


  public String getMessage() {
    return message;
  }
  
  public String getClassName() {
    return className;
  }

  public int getCommonFrames() {
    return commonFramesCount;
  }

  public IThrowableProxy getCause() {
    return cause;
  }
  
  public ThrowableDataPoint[] getThrowableDataPointArray() {
    return throwableDataPointArray;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((className == null) ? 0 : className.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final ThrowableProxyVO other = (ThrowableProxyVO) obj;

    if (className == null) {
      if (other.className != null)
        return false;
    } else if (!className.equals(other.className))
      return false;

    if (!Arrays.equals(throwableDataPointArray, other.throwableDataPointArray))
      return false;
    
    if (cause == null) {
      if (other.cause != null)
        return false;
    } else if (!cause.equals(other.cause))
      return false;
    
    return true;
  }

  public static ThrowableProxyVO build(IThrowableProxy throwableProxy) {
    if(throwableProxy == null) {
      return null;
    }
    ThrowableProxyVO tpvo = new ThrowableProxyVO();
    tpvo.className = throwableProxy.getClassName();
    tpvo.message = throwableProxy.getMessage();
    tpvo.commonFramesCount = throwableProxy.getCommonFrames();
    tpvo.throwableDataPointArray = throwableProxy.getThrowableDataPointArray();
    if(throwableProxy.getCause() != null) {
      tpvo.cause = ThrowableProxyVO.build(throwableProxy.getCause());
    }
    return tpvo;
  }



}
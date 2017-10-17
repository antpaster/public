/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ru.netris.posixsocket;

public class SenderSocket {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SenderSocket(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SenderSocket obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        senderJNI.delete_SenderSocket(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public SenderSocket() {
    this(senderJNI.new_SenderSocket(), true);
  }

  public boolean connectToHost(String host, int port) {
    return senderJNI.SenderSocket_connectToHost(swigCPtr, this, host, port);
  }

  public boolean closeSocket() {
    return senderJNI.SenderSocket_closeSocket(swigCPtr, this);
  }

  public int sendToHost(byte[] data) {
    return senderJNI.SenderSocket_sendToHost(swigCPtr, this, data);
  }

  public int readFromHost(byte[] data) {
    return senderJNI.SenderSocket_readFromHost(swigCPtr, this, data);
  }

}
